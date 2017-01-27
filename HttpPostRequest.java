package com.lzl.xiyoueducational;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

class HttpPostRequest extends StringRequest {

    Map<String,String> params;
    String cookie;

    public HttpPostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener,Map<String,String> params,String cookie) {
        super(method, url, listener, errorListener);
        this.params = params;
        this.cookie = cookie;
    }

    public HttpPostRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener,Map<String,String> params,String cookie) {
        super(url, listener, errorListener);
        this.params = params;
        this.cookie = cookie;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return this.params;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        Map params = this.getParams();
        return params != null && params.size() > 0?this.encodeParameters(params, this.getParamsEncoding()):null;
    }


    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();

        try {
            Iterator var5 = params.entrySet().iterator();

            while(var5.hasNext()) {
                java.util.Map.Entry uee = (java.util.Map.Entry)var5.next();
                encodedParams.append(URLEncoder.encode((String)uee.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode((String)uee.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            String answer = encodedParams.toString().substring(0,encodedParams.toString().length()-1);
            return answer.getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException var6) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, var6);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.clear();
        //headers.put("Charset", "UTF-8");
        //headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Accept-Encoding", "gzip,deflate");
        headers.put("Accept-Language","zh-Hans-CN,zh-Hans;q=0.5");
        headers.put("Accept","text/html, application/xhtml+xml, image/jxr, */*");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
        headers.put("Cookie",cookie);
        headers.put("Referer","http://222.24.62.120/");
        headers.put("Pragma","no-cache");
        return headers;
    }
}
