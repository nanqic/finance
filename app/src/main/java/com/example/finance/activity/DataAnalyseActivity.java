package com.example.finance.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.example.finance.R;
import com.example.finance.db.MyDBHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataAnalyseActivity extends AppCompatActivity {
    //1 定义对象
    LineChart income_chart,outpay_chart;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    String[] indata={"学习-奖金","比赛-奖励","业余-兼职","其他"};
    //收入类型数据统计的初始值
    int xxjjmoney=0;
    int bsjlmoney=0;
    int yyjzmoney=0;
    int qtmoney=0;
    String[] outdata={"电影-娱乐","美食-餐饮","手机-充值","其他"};
    //收入类型数据统计的初始值
    int dyylmoney=0;
    int mscymoney=0;
    int sjczmoney=0;
    int othermoney=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_analyse);
        //2 绑定控件
        initView();
        //3 收入汇总分析
        inComeData();
        //4 支出汇总分析
        outComeData();
    }
    //2 绑定控件 
    private void initView() {
        income_chart=findViewById(R.id.income_chart_data);
        outpay_chart=findViewById(R.id.outpay_chart_data);
        mhelper=new MyDBHelper(DataAnalyseActivity.this);
        db=mhelper.getWritableDatabase();
    }
    //3 收入汇总分析
    private void inComeData() {
        //第一部分：获取数据
        Cursor cursor =db.rawQuery("select * from in_come",null);
        while(cursor.moveToNext()){
            Double
                    mymoney=cursor.getDouble(cursor.getColumnIndex("inmoney"));
            String
                    mytype=cursor.getString(cursor.getColumnIndex("intype"));
            if(mytype.equals("学习-奖金")){
                xxjjmoney+=mymoney;
            }else if(mytype.equals("比赛-奖励")){
                bsjlmoney+=mymoney;
            }else if(mytype.equals("业余-兼职")){
                yyjzmoney+=mymoney;
            }else if(mytype.equals("其他")){
                qtmoney+=mymoney;
            }
        }
        //第二部分：LineChart 图表初始化设置---Xy 轴的设置
        XAxis xAxis=income_chart.getXAxis();//获取此图表的 x 轴轴线
        YAxis yAxisleft =income_chart.getAxisLeft();//获取此图表的 Y 轴左侧轴线
        YAxis yAxisright =income_chart.getAxisRight();//获取此图表的 Y轴右侧轴线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置 X 轴线的位置为底部
        yAxisleft.setAxisMinimum(0f);//保证 Y 轴从 0 开始，不然会上移一点。
        yAxisright.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {//x 轴自定义标签的设置
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return indata[(int) v];
            }
        });
        //第三部分:LineDataSet 曲线初始化设置
        List<Entry> inentries=new ArrayList<>();//Y 轴的数据
        inentries.add(new Entry(0,xxjjmoney));
        inentries.add(new Entry(1,bsjlmoney));
        inentries.add(new Entry(2,yyjzmoney));
        inentries.add(new Entry(3,qtmoney));
        LineDataSet lineDataSet=new LineDataSet(inentries,"金额");//代表一条线,“金额”是曲线名称
        lineDataSet.setValueTextSize(25);//曲线上文字的大小
        lineDataSet.setValueTextColor(Color.WHITE);//曲线上文字的颜色
        lineDataSet.setDrawFilled(true);//设置折线图填充
        //第四部分：曲线展示
        LineData data=new LineData(lineDataSet);//创建 LineData 对象属于折线图的数据集合
        income_chart.setData(data);// 添加到图表中
    }
    //4 支出汇总分析
    private void outComeData() {
        //第一部分：获取数据
        Cursor cursor =db.rawQuery("select * from pay_out",null);
        while(cursor.moveToNext()){
            Double
                    mymoney=cursor.getDouble(cursor.getColumnIndex("outmoney"));
            String
                    mytype=cursor.getString(cursor.getColumnIndex("outtype"));
            if(mytype.equals("电影-娱乐")){
                dyylmoney+=mymoney;
            }else if(mytype.equals("美食-餐饮")){
                mscymoney+=mymoney;
            }else if(mytype.equals("手机-充值")){
                sjczmoney+=mymoney;
            }else if(mytype.equals("其他")){
                othermoney+=mymoney;
            }
        }
        //第二部分：LineChart 图表初始化设置---Xy 轴的设置
        XAxis xAxis=outpay_chart.getXAxis();//获取此图表的 x 轴轴线
        YAxis yAxisleft =outpay_chart.getAxisLeft();//获取此图表的 Y 轴左侧轴线
        YAxis yAxisright =outpay_chart.getAxisRight();//获取此图表的 Y 轴右侧轴线
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置 X 轴线的位置为底部
        yAxisleft.setAxisMinimum(0f);//保证 Y 轴从 0 开始，不然会上移一点。
        yAxisright.setAxisMinimum(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {//x 轴自定义标签的设置
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return outdata[(int) v];
            }
        });
        //第三部分:LineDataSet 曲线初始化设置
        List<Entry> outentries=new ArrayList<>();//Y 轴的数据
        outentries.add(new Entry(0,dyylmoney));
        outentries.add(new Entry(1,mscymoney));
        outentries.add(new Entry(2,sjczmoney));
        outentries.add(new Entry(3,othermoney));
        LineDataSet lineDataSet=new LineDataSet(outentries,"金额");//代表一条线,“金额”是曲线名称
        lineDataSet.setValueTextSize(25);//曲线上文字的大小
        lineDataSet.setValueTextColor(Color.WHITE);//曲线上文字的颜色
        lineDataSet.setDrawFilled(true);//设置折线图填充
        //第四部分：曲线展示
        LineData data=new LineData(lineDataSet);//创建 LineData对象属于LineChart 折线图的数据集合
        outpay_chart.setData(data);// 添加到图表中
    }
}