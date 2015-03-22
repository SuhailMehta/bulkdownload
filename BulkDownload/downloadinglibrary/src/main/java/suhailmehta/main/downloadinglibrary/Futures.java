package suhailmehta.main.downloadinglibrary;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import suhailmehta.main.downloadinglibrary.annotation.ReadThreadTypeAnnotation;
import suhailmehta.main.downloadinglibrary.constant.ThreadConstant;

/**
 * Created by suhailmehta on 22/03/15.
 */
public class Futures {

    private static <V> V getFututeUninterruptibly(Future<V> future)
            throws ExecutionException {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return future.get();
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static <V> void addCallbackListener(final Future<V> future,
                                               final CallbackListener<? super V> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final V value;
                try {
                    value = getFututeUninterruptibly(future);
                } catch (ExecutionException e) {
                    callback.onFailure(e.getCause());
                    return;
                } catch (RuntimeException e) {
                    callback.onFailure(e);
                    return;
                } catch (Error e) {
                    callback.onFailure(e);
                    return;
                }

                String mode =  ReadThreadTypeAnnotation.readAnnotationOn(callback.getClass());

                if(mode != null){
                    if(mode.equals(ThreadConstant.UI)){

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(value);
                            }
                        });

                    }else{
                        callback.onSuccess(value);
                    }
                }else{
                    callback.onSuccess(value);
                }

            }
        }).start();

    }

}
