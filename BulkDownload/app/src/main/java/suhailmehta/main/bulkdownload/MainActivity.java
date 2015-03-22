package suhailmehta.main.bulkdownload;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import suhailmehta.main.downloadinglibrary.CallbackListener;
import suhailmehta.main.downloadinglibrary.DownloaderCallable;
import suhailmehta.main.downloadinglibrary.Futures;
import suhailmehta.main.downloadinglibrary.annotation.ThreadType;
import suhailmehta.main.downloadinglibrary.constant.ThreadConstant;
import suhailmehta.main.downloadinglibrary.model.MakeRequestModel;


public class MainActivity extends ActionBarActivity {

    private ExecutorService mService ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Change to fix pool executor when there
        // are specific number (n) of tasks to perform
        mService = Executors.newCachedThreadPool();

        // Thread to be formed to avoid UI blocking in the formation
        // of Request model .
        // Best needed when there are more the one asynchronous tasks to execute.
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Make Request Model { MakeRequestModel.java } first
                MakeRequestModel model = new MakeRequestModel();

                // TODO : Set request model properties and stringify the body part

                // TODO : fetch storage location from {StorageUtils.java}

                Future<MakeRequestModel> future = mService.submit(new DownloaderCallable(model));

                Futures.addCallbackListener(future, new CallbackListener<MakeRequestModel>() {

                    // mode = ThreadConstant.UI to get the callback on UI THREAD , DEFAULT IS UI
                    @ThreadType(mode = ThreadConstant.BACKGROUND)
                    public void onSuccess(MakeRequestModel result) {

                        if (result.isDownloaded()) {

                            // Success
                            // TODO : Use JsonReader to parse the json response here from file

                        } else {
                           // TODO : Handle error here
                        }
                    }

                    public void onFailure(Throwable throwable) {

                        // TODO : shut down the executor service and how error log

                    }

                    // TODO : In later stages add download progress callback here.
                });
            }
        }).start();

    }

}
