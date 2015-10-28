package com.itree.itreerecruit.validations;

import android.content.Context;
import android.widget.Toast;

import com.cndemoz.avalidations.ValidationExecutor;

import java.util.regex.Pattern;

/**
 * Created by 苑雪元 on 2015/10/25.
 */
public class passwordValidation extends ValidationExecutor {
    @Override
    public boolean doValidate(Context context, String s) {
        String regex = "^[A-Za-z0-9]{6,11}$";
        boolean result = Pattern.compile(regex).matcher(s).find();
        if (!result) {
            Toast.makeText(context,"输入的密码不正确",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
