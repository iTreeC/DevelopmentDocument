package com.itree.itreerecruit.gzddUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.itree.itreerecruit.R;


/**
 * Created by lenovo on 2015/10/16.
 * 作用：来实现行业类别中列表的 实现
 */
public class MyAdapter_hyll extends BaseAdapter {

    private String[] chexkboxtext ;
    private Context context= null;
    private boolean[] checks;//用于保存checkbox的选中状态

    public MyAdapter_hyll(Context context, String[] s ) {
        this.chexkboxtext = s;
        this.context=context;
        checks = new boolean[s.length];
    }

    @Override
    public int getCount() {
        return chexkboxtext.length;
    }

    @Override
    public Object getItem(int position) {
        return chexkboxtext[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            //实例化一个布局文件
            convertView  = inflater.inflate(R.layout.list_item_hyll, null);
            //搜索ID
            vh = new ViewHolder();
            vh.cb_text= (CheckBox) convertView.findViewById(R.id.checkBox_hyll);
            convertView.setTag(vh);
        }else{
            //如果有则直接取得
            vh = (ViewHolder)convertView.getTag();
        }
        vh.cb_text.setText(chexkboxtext[position]);
        //解决多选框乱动
        final int pos  = position; //pos必须声明为final
        vh.cb_text.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                checks[pos] = isChecked;
            }});
        vh.cb_text.setChecked(checks[pos]);
        return convertView;
    }
    static class ViewHolder{
        CheckBox cb_text;
    }
}
