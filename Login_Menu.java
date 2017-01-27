package com.lzl.xiyoueducational;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Login_Menu extends Activity {
    final static String VIEWSTATE = "dDwtNTE2MjI4MTQ7Oz5O9kSeYykjfN0r53Yqhqckbvd83A==";
    final static int Log_SUCCESS = 1;
    final static int Log_FAILED = 0;
    ImageView imageView;
    Button button;
    EditText edit_id;
    EditText edit_password;
    RequestQueue mqueue;
    EditText scretcode_input;
    ImageView scretcode_image;
    ImageRequest imageRequest;
    Map<String,String> responseHeaders;
    String cookies;
    String student_name;
    Map<String,String> nameHeaders;
    int log_flag;
    String xh = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logon_layout);
        init();
        getScretCodeImage();
    }


    public String getStudent_name() {
        return student_name;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXh() {
        return xh;
    }

    public void setLog_flag(int flag)
    {
        this.log_flag = flag;
    }
    public void setNameHeaders(Map<String, String> nameHeaders) {
        this.nameHeaders = nameHeaders;
    }

    public void setStudent_name(String name)
    {
        this.student_name = name;
    }
    public String getCookies()
    {
        if (responseHeaders==null)
            return null;
        else
            return responseHeaders.get("Set-Cookie").substring(0,responseHeaders.get("Set-Cookie").length()-6);
    }

    public void setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public Map<String,String> getResponseHeaders()
    {
        return responseHeaders;
    }

    public void getScretCodeImage()
    {
        imageRequest = new ImageRequest("http://222.24.62.120/CheckCode.aspx", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                scretcode_image.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                scretcode_image.setImageResource(R.mipmap.ic_launcher);
            }
        })
        {
            @Override
            protected Response<Bitmap> parseNetworkResponse(NetworkResponse response) {
                setResponseHeaders(response.headers);
                return super.parseNetworkResponse(response);
            }
        };
        mqueue.add(imageRequest);
    }

    public int getLog_flag()
    {
        return this.log_flag;
    }

    public void init()
    {
        mqueue = Volley.newRequestQueue(Login_Menu.this);
        scretcode_image = (ImageView)findViewById(R.id.scretCode_pic);
        scretcode_input = (EditText)findViewById(R.id.scretCode_input);
        imageView = (ImageView)findViewById(R.id.show_userIcon);
        button = (Button)findViewById(R.id.logon_button);
        edit_id = (EditText)findViewById(R.id.edit_username);
        edit_password = (EditText)findViewById(R.id.edit_password);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login_Menu.this);
        alertDialog.setTitle("登陆结果");
        alertDialog.setPositiveButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(getLog_flag()==1)
                {
                    Intent intent = new Intent(Login_Menu.this,MainMenu.class);
                    intent.putExtra("xh",getXh());
                    intent.putExtra("name",getStudent_name());
                    intent.putExtra("cookies",getCookies());
                    startActivity(intent);
                }
            }
        });
        alertDialog.setNegativeButton("取消",null);

        scretcode_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getScretCodeImage();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = edit_id.getText().toString();
                final String password = edit_password.getText().toString();
                final String srcetcode = scretcode_input.getText().toString();
                setXh(username);
                Map<String,String> params = createParams(username,password,srcetcode);
                HttpPostRequest logon = new HttpPostRequest(Request.Method.POST, "http://222.24.62.120/default2.aspx", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(getLog_flag()==1)
                        {
                            String name = ParseDataFromHtml.getName(s);
                            alertDialog.setMessage("登陆成功\n"+"欢迎"+name);
                            setStudent_name(name);
                        }
                        else
                            alertDialog.setMessage("请检查用户名密码验证码是否正确！");
                        Log.d("response  ",s);
                        alertDialog.create().show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        alertDialog.setMessage("错误请检查网络连接是否正常。");
                        if (volleyError!=null)
                            Log.d("ERROR  ",volleyError.toString());
                        else
                            Log.d("ERROR","\tNULL");
                        alertDialog.create().show();
                    }
                },params,getCookies())
                {
                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        setNameHeaders(response.headers);
                        printSize(response.data);
                        if(response.data.length>=9000)
                            setLog_flag(Log_SUCCESS);
                        else
                            setLog_flag(Log_FAILED);
                        return super.parseNetworkResponse(response);
                    }
                };
                logon.setShouldCache(false);
                mqueue.add(logon);
            }
        });
    }

    public void printSize(byte[] data)
    {
        System.out.println(data.length);
    }

    public Map<String,String> createParams(String username,String password,String srcetcode)
    {
        Map<String,String> map = new LinkedHashMap<>();
        map.clear();
        map.put("__VIEWSTATE",VIEWSTATE);
        map.put("txtUserName",username);
        map.put("Textbox1","");
        map.put("Textbox2",password);
        map.put("txtSecretCode",srcetcode);
        map.put("RadioButtonList1","学生");
        map.put("Button1","");
        map.put("lbLanguage","");
        map.put("hidPdrs","");
        map.put("hidsc","");
        return map;
    }

}
