package com.standard.untils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringUtil {

    public static boolean isEmpty(String str){

        if(str==null||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isNotEmpty(String str){
        if((str!=null)&&!"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    public static String formatLike(String str){
        if(isNotEmpty(str)){
            return "%"+str+"%";
        }else{
            return null;
        }
    }

    public static List<String> filterWriter(List<String> list){
        List<String> resultList = new ArrayList<>();
        for(String l: list){
            if(isNotEmpty(l)){
                resultList.add(l);
            }
        }
        return resultList;
    }

    public static String tripHtml(String content){
        content = content.replaceAll("<b .*?>","\r\n");
        content = content.replaceAll("<br\\s*/?>","\r\n");
        content = content.replaceAll("\\<.*?>","\r\n");
        content = content.replaceAll(" ","");
        return content;
    }

    public static String genSixRandomNum(){
        Random random = new Random();
        String result = "";
        for(int i=0;i<6;i++){
            result+=random.nextInt(10);
        }
        return result;
    }
}
