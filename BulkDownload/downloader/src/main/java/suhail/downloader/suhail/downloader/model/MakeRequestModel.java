package suhail.downloader.suhail.downloader.model;

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
}
