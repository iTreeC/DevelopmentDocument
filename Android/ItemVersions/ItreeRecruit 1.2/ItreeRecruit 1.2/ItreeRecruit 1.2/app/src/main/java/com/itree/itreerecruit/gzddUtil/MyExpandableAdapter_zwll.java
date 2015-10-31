package com.itree.itreerecruit.gzddUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;

import com.itree.itreerecruit.R;

//import com.dazhouma.android.itree2.R;

/**
 * Created by lenovo on 2015/10/16.
 */
public class MyExpandableAdapter_zwll extends BaseExpandableListAdapter{
    private Context context = null;
    private String[] groups = null;
    private String[][] childs = null;
    private String[] groups1 = {"销售业务","销售管理","销售行政/商务","客服 /售前/售后技术支持","市场","公关/媒体","广告/会展"
            ,"财务/审计/税务","人力资源","行政/后勤/文秘"
            ,"项目管理/项目协调","质量管理 /安全防护","高级管理"
            ,"软件/互联网开发/系统 集成","硬件开发","互联网产品/运营管理","IT质量管理/测试/配置管理 ","IT运维/技术支持","IT管理/项目协调 ","电信/通信技术开发及应用"
            ,"房地产开发/经纪/中介","土木/建筑/装修 /市政工程","物业管理"
            ,"银行","证券/期货/投资管理/服务","保险","信托/担当/拍卖/典当"
            ,"采购/贸易","交通运输服务","物流/仓储"
            ,"生产管理/运营","电子/电器/仪器仪表","汽车制造","汽车销售与服务","机械设计/制造/维修","服装 /纺织/皮革设计/生产","技工 /操作工","生物/制药/医疗器械","化工"
            ,"影视/媒体/出版/印刷","艺术设计"
            ,"咨询/顾问/调研/数据分析","教育/培训","律师/法务/合规","翻译（口译与笔译）"
            ,"商超/酒店/娱乐管理/服务","旅游 /度假/出入境服务","烹饪/料理/食品研发","保健/美容/美发/健身","医院/医疗/护理","社区/居民/家政服务"
            ,"能源/矿产/地质勘查","环境科学/环保","农/林/牧/渔业","公务员/事业单位/科研机构"
            ,"实习生/培训生/储备干部","志愿者/社会工作者","兼职/临时","其他"};
    private String[][] childs1 = {
            {"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "}
            ,{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "}
            ,{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "}
            ,{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "}
            ,{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "}
            ,{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "}
            ,{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "},{"销售总监","销售经理 "}
            ,{"销售总监","销售经理 "},{"销售代表","客户代表 "},{"销售总监","销售经理 "}};
    public MyExpandableAdapter_zwll(Context context,String[] g,String[][] c ) {
        this.context=context;
        this.groups=g;
        this.childs=c;

    }
    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childs[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_zwll_groups, null);
        }
        CheckBox cb_text = (CheckBox) convertView.findViewById(R.id.checkBox_zwll_groups);
        cb_text.setText(groups[groupPosition]);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_zwll_child,null);
        }
        CheckBox cb_text = (CheckBox)convertView.findViewById(R.id.checkBox1_zwll_child);
        cb_text.setText(childs[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
