package suhailmehta.main.bulkdownload;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import suhail.downloader.CallbackListener;
import suhail.downloader.DownloaderCallable;
import suhail.downloader.Futures;
import suhailmehta.main.downloadinglibrary.annotation.ThreadType;
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

                // Make Request Model (# MakeRequestModel.class) first
                MakeRequestModel model = new MakeRequestModel();

                Future<MakeRequestModel> future = mService.submit(new DownloaderCallable(model));

                Futures.addCallbackListener(future, new CallbackListener<MakeRequestModel>() {

                    @ThreadType (mode = "Background")
                    public void onSuccess(MakeRequestModel result) {

                        if (result.isDownloaded()) {

                        } else{

                        }
                    }

                    public void onFailure(Throwable throwable) {


                    }
                });
            }
        }).start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
