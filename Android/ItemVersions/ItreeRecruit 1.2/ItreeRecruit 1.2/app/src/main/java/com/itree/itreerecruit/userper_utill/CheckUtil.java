package com.itree.itreerecruit.userper_utill;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guanjiwei on 2015/10/17.
 */
public class CheckUtil {
    /**
     * 检验手机号
     * @param phoneNum
     * @return boolean
     * @zuozhe 管纪伟
     * @time 20151017
     */
    public static boolean checkPhoneNum(String phoneNum){

        //建立审核规范
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(phoneNum);

        String a = ""+m.matches();
        Log.d("Tag", a);
        return m.matches();
    }
    /**
     *
     */
    public static boolean checkEmail(String email){

        //建立审核规范
        Pattern p = Pattern.compile("^\\\\s*\\\\w+(?:\\\\.{0,1}[\\\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\\\.[a-zA-Z]+\\\\s*$");

        Matcher m = p.matcher(email);

        String a = ""+m.matches();
        Log.d("Tag",a);
        return m.matches();

    }
}
