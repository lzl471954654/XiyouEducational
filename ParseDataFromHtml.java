package com.lzl.xiyoueducational;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/21 0021.
 */
public class ParseDataFromHtml {

    public static List<Map<String,String>> getScoreList(String src)
    {
        //从源文件String中解析出成绩
        List<Map<String,String>> mapList = new LinkedList<>();
        Map<String,String> lesson_info;         //每门课程中分别对应了课程的属性，包含名称，成绩，绩点等
        Document document = Jsoup.parse(src);
        Elements tr = document.getElementsByTag("tr");
        for(int i = 5;i<tr.size()-7;i++)
        {
            lesson_info = parseNodeData(tr.get(i).getAllElements());
            mapList.add(lesson_info);
        }
        return mapList;
    }

    public static Map<String,String> parseNodeData(Elements elements)
    {
        Map<String,String> map = new LinkedHashMap<>();
        map.put("xn",elements.get(1).text());               //学年
        map.put("xq",elements.get(2).text());               //学期
        map.put("kcmc",elements.get(4).text());
        map.put("kcxz",elements.get(5).text());
        map.put("kcdm",elements.get(3).text());
        map.put("xf",elements.get(7).text());
        map.put("jd",elements.get(8).text());
        map.put("cj",elements.get(9).text());
        map.put("kkxy",elements.get(13).text());
        return map;
    }

    public static String getName(String src)
    {
        Document document = Jsoup.parse(src);
        String name = document.getElementById("xhxm").text();
        name = name.substring(0,name.length()-2);
        return name;
    }
}
