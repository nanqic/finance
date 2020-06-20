package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finance.activity.DataAnalyseActivity;
import com.example.finance.activity.IncomeDetailActivity;
import com.example.finance.activity.NewIncomeActivity;
import com.example.finance.activity.NewPayActivity;
import com.example.finance.activity.PayDetailActivity;
import com.example.finance.activity.SysSettingActivity;

public class MainActivity extends AppCompatActivity {
    //定义对象
    Button
            bt_newincome,bt_incomedetail,btn_newpay,btn_paydetail,bt_dataanalyse
            ,btn_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 绑定控件
        initView();
        //按钮单击事件
        btnOnClick();
    }
    // 绑定控件
    private void initView() {
        bt_newincome=findViewById(R.id.bt_newincome_main);
        bt_incomedetail=findViewById(R.id.bt_incomedetail_main);
        btn_newpay=findViewById(R.id.bt_newpay_main);
        btn_paydetail=findViewById(R.id.bt_paydetail_main);
        bt_dataanalyse=findViewById(R.id.bt_dataanalyse_main);
        btn_setting=findViewById(R.id.bt_syssetting_main);
    }
    //按钮单击事件
    private void btnOnClick() {
        bt_newincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,
                        NewIncomeActivity.class);
                startActivity(intent);
            }
        });
        bt_incomedetail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,
                        IncomeDetailActivity.class);
                startActivity(intent);
            }
        });
        btn_newpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,
                        NewPayActivity.class);
                startActivity(intent);
            }
        });
        btn_paydetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,
                        PayDetailActivity.class);
                startActivity(intent);
            }
        });
        bt_dataanalyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,
                        DataAnalyseActivity.class);
                startActivity(intent);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,
                        SysSettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
