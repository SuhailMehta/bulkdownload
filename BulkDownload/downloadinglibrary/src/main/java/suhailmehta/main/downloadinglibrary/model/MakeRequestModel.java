package suhailmehta.main.downloadinglibrary.model;

import java.util.HashMap;

/**
 * Created by suhailmehta on 18/03/15.
 */
public class MakeRequestModel {

    private String mUrl ;
    private HashMap<String,String> mBody ;
    private HashMap<String,String> mHeader ;
    private String mEncoding ;
    private String mMethod ;
    private String mFilePath ;
    private boolean isDownloaded ;

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean isDownloaded) {
        this.isDownloaded = isDownloaded;
    }

    public String getmFilePath() {
        return mFilePath;
    }

    public void setmFilePath(String mFilePath) {
        this.mFilePath = mFilePath;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public HashMap<String, String> getmBody() {
        return mBody;
    }

    public void setmBody(HashMap<String, String> mBody) {
        this.mBody = mBody;
    }

    public HashMap<String, String> getmHeader() {
        return mHeader;
    }

    public void setmHeader(HashMap<String, String> mHeader) {
        this.mHeader = mHeader;
    }

    public String getmEncoding() {
        return mEncoding;
    }

    public void setmEncoding(String mEncoding) {
        this.mEncoding = mEncoding;
    }

    public String getmMethod() {
        return mMethod;
    }

    public void setmMethod(String mMethod) {
        this.mMethod = mMethod;
    }

    public String stingfyBody(){
        if(mBody != null){
            // TODO : make body in post form

            


        }
        return null ;
    }
}
