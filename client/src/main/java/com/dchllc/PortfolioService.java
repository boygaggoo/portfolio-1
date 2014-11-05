package com.dchllc;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class PortfolioService {
    private static final String BASE_URL = "http://192.168.0.3:4567/";  // TODO update
    public static final String API_VERSION = "1";

    private static PortfolioService instance;
    private RequestQueue requestQueue;

    private PortfolioService(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized PortfolioService getInstance(Context context) {
        if (instance == null) {
            instance = new PortfolioService(context);
        }

        return instance;
    }

    public <T> Request<T> add(Request<T> request) {
        return requestQueue.add(request);
    }

    public void getJsonObject(String url, JSONObject jsonObject, ServiceResponseListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, BASE_URL + url, jsonObject, listener, listener);
        request.setShouldCache(false);
        add(request);
    }
}
