package suhailmehta.main.downloadinglibrary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Callable;

import suhailmehta.main.downloadinglibrary.constant.Encoding;
import suhailmehta.main.downloadinglibrary.model.MakeRequestModel;

/**
 * Created by suhailmehta on 22/03/15.
 */
public class DownloaderCallable implements Callable<MakeRequestModel> {

    private MakeRequestModel model ;
    public static final int BUFFER_SIZE = 4096;

    public DownloaderCallable(MakeRequestModel model){
        this. model = model ;
    }

    @Override
    public MakeRequestModel call() throws Exception {

        OutputStream fileOutput = null;
        int bufferLength;

        File file = null;

        try {

            URL url = new URL(model.getmUrl());

            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();

            urlConnection.setRequestMethod(model.getmMethod());
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + Encoding.UTF_8);

            if(model.getmHeader() != null)
                for (Map.Entry<String, String> entry : model.getmHeader().entrySet()) {
                    urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }

            if(model.getmBody() != null){
                urlConnection.setDoOutput(true);

                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, Encoding.UTF_8));
                os.write(model.stingfyBody().getBytes(Encoding.UTF_8));
                writer.flush();
                writer.close();
                os.close();
            }

            urlConnection.connect();

            InputStream inputStream = new BufferedInputStream(
                    urlConnection.getInputStream());

            file = new File(model.getmFilePath());

            fileOutput = new BufferedOutputStream(
                    new FileOutputStream(file));

            byte[] buffer = new byte[BUFFER_SIZE];

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
            }

            fileOutput.flush();

        } catch (Exception e) {

            // TODO: handle resume case

            model.setDownloaded(false);


        } finally {


            if (fileOutput != null) {
                try {
                    fileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

//            if (file != null) {
//                boolean isDeleted = file.delete();
//
//                if (isDeleted) {
//                }
//            } else {
//                // TODO : failure check update
//            }
            model.setDownloaded(true); // Success for downloaded and no exception
        }

        return model;
    }


}
