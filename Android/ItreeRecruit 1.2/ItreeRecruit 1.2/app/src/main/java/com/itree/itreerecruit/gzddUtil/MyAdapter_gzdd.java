package com.itree.itreerecruit.gzddUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import com.itree.itreerecruit.R;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Created by lenovo on 2015/10/18.
 * 实现工作地点列表的相关工作
 */
public class MyAdapter_gzdd extends BaseAdapter{
    //建hash表来存放被选中的按钮
    Map<Integer, Boolean> isCheckMap =  new HashMap<Integer, Boolean>();
    //记录复选框勾选的位置
    private Set<Integer> positionSet  = new HashSet<Integer>();
    private Context context;
    private String[] addresses;
    private boolean[] checks;//用于保存checkbox的选中状态

    public MyAdapter_gzdd(Context context, String[] s) {
        this.context=context;
        //将数据传给addresses
        this.addresses = s;
        checks = new boolean[s.length];
    }
    public MyAdapter_gzdd(){

    };

    public Set<Integer> getPositionSet() {
        return positionSet;
    }

    public void setPositionSet(Set<Integer> positionSet) {
        this.positionSet = positionSet;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return addresses.length;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return addresses[arg0];
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int arg0, View converView, ViewGroup arg2) {
        // TODO Auto-generated method stub

        ViewHolder vh;

        //可利用view为空，就是没有可用的，再去创建新的,减少了创建新的
        if(converView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            //实例化一个布局文件
            converView  = inflater.inflate(R.layout.list_item_gzdd, null);
            //搜索ID
            vh = new ViewHolder();
            vh.cb_text= (CheckBox) converView.findViewById(R.id.checkBox_gzdd);
            converView.setTag(vh);
        }else{
            //如果有则直接取得
            vh = (ViewHolder)converView.getTag();
        }
        //往多选中写入从数据库中得到的值
        vh.cb_text.setText(addresses[arg0]);

        vh.cb_text.setId(arg0);
        //解决多选框乱动
        final int pos  = arg0; //pos必须声明为final
        vh.cb_text.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //这是解决读出被选择的多选框
                if(isChecked) {        //记录或删除勾选的状态
                    positionSet.add(buttonView.getId());
                }else {
                    positionSet.remove(buttonView.getId());
                }
                setPositionSet(positionSet);
                System.out.println("输出");
                System.out.println(positionSet);
                //这是解决乱动
                checks[pos] = isChecked;
            }
        });
        vh.cb_text.setChecked(checks[pos]);

//        //为了获取到checkbox的选中事件，还需要实现checkbox的setOnCheckedChangeListener
//        vh.cb_text.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                int radiaoId = (Integer)buttonView.getTag();
//                        //Integer.parseInt(buttonView.getTag().toString());
//                if(isChecked)
//                {
//                    //将选中的放入hashmap中
//                    isCheckMap.put(radiaoId, isChecked);
//                }
//                else
//                {
//                    //取消选中的则剔除
//                    isCheckMap.remove(radiaoId);
//                }
//            }
//        });
        //111

//        CheckBox.OnCheckedChangeListener checkListener = new CheckBox.OnCheckedChangeListener(){
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,
//                                         boolean isChecked) {
//                // TODO Auto-generated method stub
//                if(isChecked) {        //记录或删除勾选的状态
//                    positionSet.add(buttonView.getId());
//
//                }else {
//                    positionSet.remove(buttonView.getId());
//                }
//                System.out.println("输出什么");
//                System.out.println(positionSet);
//            }
//
//        };

        return converView;
    }


    //能不封装就不封装了 ，为了性能，保存ID
    static class ViewHolder{
        CheckBox cb_text;
    }
}
