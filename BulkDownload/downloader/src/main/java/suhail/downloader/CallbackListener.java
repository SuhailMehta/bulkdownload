package suhail.downloader;

/**
 * Created by suhailmehta on 22/03/15.
 */
public interface CallbackListener<V> {

    void onSuccess(V v) ;

    void onFailure(Throwable throwable) ;
}
