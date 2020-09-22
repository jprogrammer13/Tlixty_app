package com.example.tlixty;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RequestTlixty {

    String BASE_URL;

    public RequestTlixty(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    void requestData(String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        String requestUrl = this.BASE_URL + "/" + url;
        try {


            client.get(requestUrl, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}