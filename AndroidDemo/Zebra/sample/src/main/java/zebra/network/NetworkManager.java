package zebra.network;

import android.content.Context;


import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;


import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpClient;
import zebra.json.Login;

/**
 * Created by multimedia on 2016-05-18.
 */
public class NetworkManager {

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
        client = new AsyncHttpClient();

        gson = new Gson();
        //PersistentCookieStore myCookieStore = new PersistentCookieStore(SampleApplication.getContext());
        //client.setCookieStore(myCookieStore);

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

    private static final String SERVER_URL = "http://113.198.84.85:8080/ZEBRA/";

    private static final String LOGIN_URL = SERVER_URL + "/adLogin";
    public void login(Context context, String id, String password, final OnResultResponseListener<Login> listener){
        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("password", password);
        client.post(context, LOGIN_URL, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFail(statusCode, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Login result = gson.fromJson(responseString, Login.class);
                listener.onSuccess(result);
            }
        });
    }
    /**
     * MainActivity에서 바코드가 스캔 되면 그 값을 변수로 잡아서 MainActivity에서
     * NetworkManager.getInstance().scan(MainActivity.this, idView.getText(), );
     * 으로 Tomcat 서버에 request를 보내자*/
}
