package com.example.finance.manage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finance.R;
import com.example.finance.activity.PayDetailActivity;
import com.example.finance.bean.OutPayBean;
import com.example.finance.db.MyDBHelper;

public class PayManageActivity extends AppCompatActivity {
    //1 定义对象
    private EditText et_money,et_time,et_payer,et_remark;
    private Spinner sp_type;
    private Button btn_modify,btn_delete;
    private MyDBHelper mhelper;
    private SQLiteDatabase db;
    private OutPayBean outpayBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_manage);
        //2 绑定控件
        initView();
        //3 获取单击的那条数据并显示出来
        getDataDisplay();
        //4 修改按钮功能的实现
        btnModidfy();
        //5 删除按钮功能的实现
        btnDelete();
    }
    //2 绑定控件
    private void initView() {
        et_money=findViewById(R.id.et_money_outmag);
        et_time=findViewById(R.id.et_time_outmag);
        sp_type=findViewById(R.id.sp_type_outmag);
        et_payer=findViewById(R.id.et_payer_outmag);
        et_remark=findViewById(R.id.et_remark_outmag);
        btn_modify=findViewById(R.id.bt_modify_outmag);
        btn_delete=findViewById(R.id.bt_delete_outmag);
        mhelper=new MyDBHelper(PayManageActivity.this);
        db=mhelper.getWritableDatabase();
    }
    //3 获取单击的那条数据并显示
    private void getDataDisplay() {
        outpayBean= (OutPayBean) getIntent().getSerializableExtra("sero");
        et_money.setText(outpayBean.getMoney()+"");
        et_time.setText(outpayBean.getTime());
        //sp_type.setPrompt(incomeBean.getType());
        if (outpayBean.getType().equals("电影-娱乐")){
            sp_type.setSelection(1);
        }else if (outpayBean.getType().equals("美食-餐饮")) {
            sp_type.setSelection(2);
        }else if (outpayBean.getType().equals("手机-充值")){
            sp_type.setSelection(4);
        }else if(outpayBean.getType().equals("其他")){
            sp_type.setSelection(9);
        }else {
            sp_type.setSelection(0);
        }
        et_payer.setText(outpayBean.getPayee());
        et_remark.setText(outpayBean.getRemark());
    }
    //4 修改按钮功能
    private void btnModidfy() {
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个对象，封装一行数据
                ContentValues values=new ContentValues();
                values.put("outmoney",et_money.getText().toString());
                values.put("outtime",et_time.getText().toString());
                values.put("outtype",sp_type.getSelectedItem().toString());
                values.put("outpayee",et_payer.getText().toString());
                values.put("outremark",et_remark.getText().toString());
                //把该行数据更新到到支出表中
                db.update("pay_out",values,"id=?",new
                        String[]{outpayBean.getId()+""});
                Toast.makeText(PayManageActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        //关闭本页面，重新打开支出明细界面，即可查询修改后的结果
                        // 创建 Intent 对象
                        Intent intent=new Intent(PayManageActivity.this,
                                PayDetailActivity.class);
                startActivity(intent);// 执行 Intent 操作
                finish();//退出当前程序，或关闭当前页面
            }
        });
    }
    //5 删除按钮功能
    private void btnDelete() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从数据库中删除条记录即可
                db.delete("pay_out","id=?",new
                        String[]{outpayBean.getId()+""});
                Toast.makeText(PayManageActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                        //关闭本页面，重新打开支出明细界面，即可删除后的结果
                        // 创建 Intent 对象
                        Intent intent=new Intent(PayManageActivity.this,
                                PayDetailActivity.class);
                startActivity(intent);// 执行 Intent 操作
                finish();//退出当前程序，或关闭当前页面
            }
        });
    }
}
