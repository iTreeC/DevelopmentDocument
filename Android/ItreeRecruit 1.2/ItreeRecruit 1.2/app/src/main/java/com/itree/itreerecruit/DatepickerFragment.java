package com.itree.itreerecruit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by dell1 on 2015/10/8.
 */
public class DatepickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private addedubgActivity adde;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        adde= (addedubgActivity) getActivity();
        super.onCreate(savedInstanceState);
    }
    //用于创建日期对话框
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
       int year= c.get(Calendar.YEAR);
       int month= c.get(Calendar.MONTH);
       int date= c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(getActivity(),this,year,month,date);
        return dialog;
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        adde.setdatevalue_ryxue(year,monthOfYear,dayOfMonth);

    }
}
