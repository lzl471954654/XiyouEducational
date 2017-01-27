package com.lzl.xiyoueducational;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2017/1/26 0026.
 */

public class MainMenu extends Activity{
    String name = null;
    String xh = null;
    TextView welcome;
    TextView search_score;
    TextView search_lessons;
    TextView exit_logon;
    String __EVENTTARGET = "";
    String __EVENTARGUMENT = "";
    String __VIEWSTATE = "dDwxMjg4MjkxNjE4Ozs+mC/tArt0u1jmP1rzm5PGO18pVC4=";
    String cookies;
    ImageView tiaoxingma;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);
        init();
        Intent intent = getIntent();
        if(intent!=null)
        {
            cookies = intent.getStringExtra("cookies");
            String name = intent.getStringExtra("name");
            String xh = intent.getStringExtra("xh");
            setXh(xh);
            setName(name);
            welcome.setText("欢迎"+getName()+"同学");
        }
        //getTiaoXingMa();
    }

    public String getCookies() {
        return cookies;
    }

    public void getTiaoXingMa()
    {
        String query = "http://222.24.62.120/excel/";
        query = query+getXh()+".jpg";
        ImageRequest imageRequest = new ImageRequest(query, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                tiaoxingma.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ;
            }
        });
        queue.add(imageRequest);
    }

    public void init()
    {
        queue = Volley.newRequestQueue(MainMenu.this);
        tiaoxingma = (ImageView)findViewById(R.id.tiaoxingma);
        welcome = (TextView)findViewById(R.id.student_name);
        search_score = (TextView)findViewById(R.id.search_score);
        search_lessons = (TextView)findViewById(R.id.search_lessons);
        exit_logon = (TextView)findViewById(R.id.exit_logon);

        exit_logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        search_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this,Scores_ac.class);
                intent.putExtra("xh",getXh());
                intent.putExtra("name",getName());
                intent.putExtra("cookies",getCookies());
                startActivity(intent);
            }
        });
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getXh() {
        return xh;
    }
}
