package com.standard.untils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Res extends HashMap<String,Object> {

    private static final long serialVersionUID = 1L;

    public Res(){
        put("code",200);
    }

    public static Res error(){
        return error(500,"未知异常，请联系管理员");
    }

    public static Res error(String msg){
        return error(500,msg);
    }

    public static Res error(int code,String msg){
        Res r = new Res();
        r.put("code",code);
        r.put("msg",msg);
        return r;
    }

    public static Res ok(String msg){
        Res r = new Res();
        r.put("msg",msg);
        return r;
    }

    public static Res ok(Map<String ,Object> map){
        Res r = new Res();
        r.putAll(map);
        return r;
    }

    public static Res ok(Object map){
        Res r = new Res();
        r.put("msg",map);
        return r;
    }



    public static Res ok(){
        return new Res();
    }

}
