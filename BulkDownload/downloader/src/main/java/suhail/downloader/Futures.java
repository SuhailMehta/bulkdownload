package suhail.downloader;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
                callback.onSuccess(value);
            }
        }).start();

    }

}
