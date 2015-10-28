package com.itree.itreerecruit.validations;

import android.content.Context;
import android.widget.Toast;

import com.cndemoz.avalidations.ValidationExecutor;

import java.util.regex.Pattern;

/**
 * Created by 苑雪元 on 2015/10/25.
 */
public class userNameValidation  extends ValidationExecutor{
    @Override
    public boolean doValidate(Context context, String s) {
        String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9]))\\d{8}$";
        boolean result = Pattern.compile(regex).matcher(s).find();
        if (!result) {
            Toast.makeText(context, "对不起，你输入的手机号不正确", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
