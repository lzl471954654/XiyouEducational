package com.lzl.xiyoueducational;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Login_Menu1 extends AppCompatActivity {
    TextView textView;
    final static String VIEWSTATE = "dDwtNTE2MjI4MTQ7Oz5O9kSeYykjfN0r53Yqhqckbvd83A==";
    String txtUserName = "04151018";
    String TextBox1 = "";
    String TextBox2 = "lzl471954654";
    String txtSecretCode;
    String RadioButtonList1 = "%D1%A7%C9%FA";
    String Button1;
    String lbLanguage;
    String hidPdrs;
    String hidsc;
    ImageView imageView;
    Button button;
    EditText editText;
    RequestQueue mqueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__menu);
        mqueue = Volley.newRequestQueue(this);
        init();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://222.24.62.120/", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("Response", s);
                textView.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Error",volleyError.getMessage());
                textView.setText(volleyError.getMessage());
            }
        });
        mqueue.add(stringRequest);
        getScretCodeImage(mqueue);
    }

    public void init()
    {
        textView = (TextView)findViewById(R.id.get_data);
        editText = (EditText)findViewById(R.id.edit_scretCode);
        button = (Button)findViewById(R.id.log_on);
        imageView = (ImageView) findViewById(R.id.imageCode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editText.getText().toString();
                System.out.println(code);
                internetPost(mqueue,code);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getScretCodeImage(mqueue);
            }
        });
    }
    public void getScretCodeImage(RequestQueue queue)
    {
        ImageRequest imageRequest = new ImageRequest("http://222.24.62.120/CheckCode.aspx", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                textView.setText("Image success");
                imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("ImageERROR",volleyError.getMessage());
            }
        });
        mqueue.add(imageRequest);
    }
    public void internetPost(RequestQueue mqueue, final String textSecretCode)
    {
        StringRequest stringPost = new StringRequest(Request.Method.POST, "http://222.24.62.120/default2.aspx", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String k ="";
                try{
                    k = new String(s.getBytes(),"gb2312");
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
                Log.d("post_request", k);
                textView.setText(k);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("post_ERROR",volleyError.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new LinkedHashMap<>();
                map.clear();
                map.put("__VIEWSTATE",VIEWSTATE);
                map.put("txtUserName",txtUserName);
                map.put("Textbox1",TextBox1);
                map.put("Textbox2",TextBox2);
                map.put("txtSecretCode",textSecretCode);
                map.put("RadioButtonList1",RadioButtonList1);
                map.put("Button1","");
                map.put("lbLanguage","");
                map.put("hidPdrs","");
                map.put("hidsc","");
                return map;
            }
        };
        System.out.println("POST\n\n"+stringPost.toString());
        System.out.println("POST\n"+stringPost.getBodyContentType());
        mqueue.add(stringPost);
    }
}
