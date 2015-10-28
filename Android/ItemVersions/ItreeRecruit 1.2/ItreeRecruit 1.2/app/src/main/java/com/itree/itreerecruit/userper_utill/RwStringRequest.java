package com.itree.itreerecruit.userper_utill;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guanjiwei on 2015/10/15.
 */
public class RwStringRequest extends StringRequest {
    //加入头文件
    private Map<String,String> headers =new HashMap<String,String>();

    public RwStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }

    public void setHeaders(String  title,String content) {
    headers.put(title,content);
    }
}
