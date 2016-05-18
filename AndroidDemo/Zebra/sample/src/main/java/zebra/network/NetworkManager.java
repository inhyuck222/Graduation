package zebra.network;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceActivity;


import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;


import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpClient;
import example.zxing.SampleApplication;


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Created by multimedia on 2016-05-18.
 */
public class NetworkManager {
    /*
    private static NetworkManager instance;
    public static NetworkManager getInstance(){
        if(instance == null){
            instance = new NetworkManager();
        }
        return instance;
    }

    AsyncHttpClient client;
    Gson gson;

    private NetworkManager() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            client = new AsyncHttpClient();
            client.setCookieStore(new PersistentCookieStore(SampleApplication.getContext()));
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson = new Gson();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(SampleApplication.getContext());
        client.setCookieStore(myCookieStore);

    }

    public interface OnResultListener<T>{
        public void onSuccess(T result);
        public void onFail(int code);
    }
    public interface OnResultResponseListener<T>{
        public void onSuccess(T result);
        public void onFail(int code, String responseString);
    }

    public HttpClient getHttpClient(){
        return client.getHttpClient();
    }

    private static final String SERVIER_URL = "http://..";

    public void scan(Context context, int barcode, final OnResultResponseListener<>)
    */
    /**
     * MainActivity에서 바코드가 스캔 되면 그 값을 변수로 잡아서 MainActivity에서
     * NetworkManager.getInstance().scan(MainActivity.this, idView.getText(), );
     * 으로 Tomcat 서버에 request를 보내자*/
}
