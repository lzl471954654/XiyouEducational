package com.lzl.xiyoueducational;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/26 0026.
 */

public class Scores_ac extends Activity {
    String __EVENTTARGET = "";
    String __EVENTARGUMENT = "";
    String __VIEWSTATE = "dDwtMTAwODg3NDc5ODt0PHA8bDxTb3J0RXhwcmVzO3NmZGNiaztkZzM7ZHlieXNjajtTb3J0RGlyZTt4aDtzdHJfdGFiX2JqZztjamN4X2xzYjt6eGNqY3h4czs+O2w8a2NtYztcZTtiamc7XGU7YXNjOzA0MTUxMDE4O3pmX2N4Y2p0al8wNDE1MTAxODs7MDs+PjtsPGk8MT47PjtsPHQ8O2w8aTw0PjtpPDEwPjtpPDE5PjtpPDI0PjtpPDMyPjtpPDM0PjtpPDM2PjtpPDM4PjtpPDQwPjtpPDQyPjtpPDQ0PjtpPDQ2PjtpPDQ4PjtpPDUyPjtpPDU0PjtpPDU2Pjs+O2w8dDx0PHA8cDxsPERhdGFUZXh0RmllbGQ7RGF0YVZhbHVlRmllbGQ7PjtsPFhOO1hOOz4+Oz47dDxpPDM+O0A8XGU7MjAxNi0yMDE3OzIwMTUtMjAxNjs+O0A8XGU7MjAxNi0yMDE3OzIwMTUtMjAxNjs+Pjs+Ozs+O3Q8dDxwPHA8bDxEYXRhVGV4dEZpZWxkO0RhdGFWYWx1ZUZpZWxkOz47bDxrY3h6bWM7a2N4emRtOz4+Oz47dDxpPDEyPjtAPOW/heS/ruivvjvpmZDpgInor7475Lu76YCJ6K++O+ivvuWkluWunui3teaVmeWtpjvovoXkv67or7476Leo5a2m56eRO+e0oOi0qOaLk+WxlTvlhazlhbHpgInkv67or7475Lq65paH57Sg6LSo6ZmQ6YCJO+mAieS/ruivvjvpgInkv67or74o57Sg6LSoKTtcZTs+O0A8MDE7MDI7MDM7MDQ7MDU7MDY7MDc7MDg7MDk7MTA7MTE7XGU7Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPFxlOz4+Oz47Oz47dDxwPHA8bDxUZXh0O1Zpc2libGU7PjtsPOWtpuWPt++8mjA0MTUxMDE4O288dD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w85aeT5ZCN77ya5YiY5pm65p6XO288dD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w85a2m6Zmi77ya6K6h566X5py65a2m6ZmiO288dD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w85LiT5Lia77yaO288dD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w86K6h566X5py656eR5a2m5LiO5oqA5pyvO288dD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOS4k+S4muaWueWQkTo7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w86KGM5pS/54+t77ya6K6h56eRMTUwMTtvPHQ+Oz4+Oz47Oz47dDxAMDxwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+Oz47Ozs7Ozs7Ozs7Pjs7Pjt0PDtsPGk8MT47aTwzPjtpPDU+O2k8Nz47aTw5PjtpPDEzPjtpPDE1PjtpPDE3PjtpPDIxPjtpPDIzPjtpPDI0PjtpPDI1PjtpPDI3PjtpPDI5PjtpPDMxPjtpPDMzPjtpPDM1PjtpPDQzPjtpPDQ5PjtpPDUxPjtpPDUyPjs+O2w8dDxwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+Oz47Oz47dDxAMDxwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+O3A8bDxzdHlsZTs+O2w8RElTUExBWTpub25lOz4+Pjs7Ozs7Ozs7Ozs+Ozs+O3Q8O2w8aTwxMz47PjtsPHQ8QDA8Ozs7Ozs7Ozs7Oz47Oz47Pj47dDxwPHA8bDxUZXh0O1Zpc2libGU7PjtsPOiHs+S7iuacqumAmui/h+ivvueoi+aIkOe7qe+8mjtvPHQ+Oz4+Oz47Oz47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE+O2k8ND47aTw0PjtsPD47Pj47cDxsPHN0eWxlOz47bDxESVNQTEFZOmJsb2NrOz4+Pjs7Ozs7Ozs7Ozs+O2w8aTwwPjs+O2w8dDw7bDxpPDE+O2k8Mj47aTwzPjtpPDQ+Oz47bDx0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+O2k8Nj47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8SlMxMDE0MTA7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOS7juS8oOe7n+aVsOaNruWIsOWkp+aVsOaNrjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w86YCJ5L+u6K++Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwwLjU7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOS4jeWQiOagvDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85bel56eR57G7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzntKDotKjmi5PlsZXnsbs7Pj47Pjs7Pjs+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+O2k8Nj47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8TFgxNDAxMDI7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOWkp+WtpueJqeeQhkI7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOW/heS/ruivvjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8NC4wOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwzOTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w86Zmi5YWs5YWx5Z+656GA6K++Oz4+Oz47Oz47Pj47dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM+O2k8ND47aTw1PjtpPDY+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPFdZMTAwMDIwOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlpKflraboi7Hor61JSTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85b+F5L+u6K++Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDw0LjA7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPDUzOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDwmbmJzcFw7Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzpmaLlhazlhbHln7rnoYDor747Pj47Pjs7Pjs+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+O2k8Nj47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8TFgxMTM1MDI7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOamgueOh+iuuuS4juaVsOeQhue7n+iuoUI7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOmAieS/ruivvjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8My4wOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDw0NDs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+Oz4+Oz4+Oz4+O3Q8QDA8cDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs+PjtwPGw8c3R5bGU7PjtsPERJU1BMQVk6bm9uZTs+Pj47Ozs7Ozs7Ozs7Pjs7Pjt0PEAwPDs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8cDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs+PjtwPGw8c3R5bGU7PjtsPERJU1BMQVk6bm9uZTs+Pj47Ozs7Ozs7Ozs7Pjs7Pjt0PEAwPDs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8cDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs+PjtwPGw8c3R5bGU7PjtsPERJU1BMQVk6bm9uZTs+Pj47Ozs7Ozs7Ozs7Pjs7Pjt0PEAwPHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47cDxsPHN0eWxlOz47bDxESVNQTEFZOm5vbmU7Pj4+Ozs7Ozs7Ozs7Oz47Oz47dDxAMDxwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+Oz47Ozs7Ozs7Ozs7Pjs7Pjt0PEAwPHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47cDxsPHN0eWxlOz47bDxESVNQTEFZOm5vbmU7Pj4+Ozs7Ozs7Ozs7Oz47Oz47dDxAMDxwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+O3A8bDxzdHlsZTs+O2w8RElTUExBWTpub25lOz4+Pjs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8O0AwPDs7QDA8cDxsPEhlYWRlclRleHQ7PjtsPOWIm+aWsOWGheWuuTs+Pjs7Ozs+O0AwPHA8bDxIZWFkZXJUZXh0Oz47bDzliJvmlrDlrabliIY7Pj47Ozs7PjtAMDxwPGw8SGVhZGVyVGV4dDs+O2w85Yib5paw5qyh5pWwOz4+Ozs7Oz47Ozs+Ozs7Ozs7Ozs7Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w85pys5LiT5Lia5YWxMjE05Lq6O288Zj47Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPFhJWU9VOz4+Oz47Oz47dDxwPHA8bDxJbWFnZVVybDs+O2w8Li9leGNlbC8wNDE1MTAxOC5qcGc7Pj47Pjs7Pjs+Pjt0PDtsPGk8Mz47PjtsPHQ8QDA8Ozs7Ozs7Ozs7Oz47Oz47Pj47Pj47Pj47PiwT9a2hN1yKtOWGc/msMNnd3wfn";
    String xh;
    String name;
    String query = "http://222.24.62.120/xscjcx.aspx?xh=04151018&xm=      &gnmkdm=N121605 ";
    String part1 = "http://222.24.62.120/xscjcx.aspx?xh=";
    String part2 = "&xm=";
    String part3 = "&gnmkdm=N121605";
    String cookies;
    List<Map<String,String>> scoresList ;
    ScrollView scrollView ;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scor_layout);
        scrollView = (ScrollView)findViewById(R.id.scores_Layout);
        requestQueue = Volley.newRequestQueue(Scores_ac.this);
        Intent intent = getIntent();
        xh = intent.getStringExtra("xh");
        name = intent.getStringExtra("name");
        cookies = intent.getStringExtra("cookies");
        getScores();
    }

    public void getScores()
    {
        String query = part1+getXh()+part2+getName()+part3;
        HttpPostRequest httpPostRequest = new HttpPostRequest(Request.Method.POST, query, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                System.out.println(s);
                List<Map<String,String>> listScores = ParseDataFromHtml.getScoreList(s);
                if(listScores.isEmpty()|listScores==null)
                    Toast.makeText(Scores_ac.this,"对不起解析失败",Toast.LENGTH_SHORT).show();
                else
                {
                    setScoresList(listScores);
                    addScoresOnView(listScores);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("ERROR",volleyError.toString());
            }
        },createParams(),cookies);
        System.out.println("Start http!!!");
        requestQueue.add(httpPostRequest);
    }

    public void addScoresOnView(List<Map<String,String>> scoresList)
    {
        LinearLayout linearLayout = new LinearLayout(Scores_ac.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);
        List<Map<String,String>> list = scoresList;
        for(int i = 0;i<list.size();i++)
        {
            Map<String,String> temp = list.get(i);
            String kcmc = temp.get("kcmc");
            String kcxz = temp.get("kcxz");
            String xf = temp.get("xf");
            String cj = temp.get("cj");
            TextView textView = new TextView(Scores_ac.this);
            textView.setText(kcmc+"\n"+kcxz+"\n学分："+xf+"\n成绩："+cj);
            textView.setPadding(10,20,10,20);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.textview_allline);
            textView.setTextSize(15);
            linearLayout.addView(textView);
        }
    }

    public void setScoresList(List<Map<String, String>> scoresList) {
        this.scoresList = scoresList;
    }

    public List<Map<String, String>> getScoresList() {
        return scoresList;
    }

    public Map<String,String> createParams()
    {
        Map<String,String> map = new LinkedHashMap<>();
        map.put("__EVENTTARGET",__EVENTTARGET);
        map.put("__EVENTARGUMENT",__EVENTARGUMENT);
        map.put("__VIEWSTATE",__VIEWSTATE);
        map.put("hidLanguage","");
        map.put("ddlXN","2016-2017");
        map.put("ddlXQ","1");
        map.put("ddl_kcxz","");
        map.put("btn_xq","学期成绩");
        return map;
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
