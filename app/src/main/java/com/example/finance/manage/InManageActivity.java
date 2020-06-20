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
import com.example.finance.activity.IncomeDetailActivity;
import com.example.finance.bean.IncomeBean;
import com.example.finance.db.MyDBHelper;

public class InManageActivity extends AppCompatActivity {
    //1 定义对象
    private EditText et_money,et_time,et_payer,et_remark;
    private Spinner sp_type;
    private Button btn_modify,btn_delete;
    private MyDBHelper mhelper;
    private SQLiteDatabase db;
    private IncomeBean incomeBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_manage);
        //2 绑定控件
        initView();
        //3 获取单击的那条数据并显示出来
        getDataDisplay();
        //4 修改按钮功能的实现
        btnModidfy();
        //5 删除按钮功能的实现
        btnDelete();
    }
    //2 绑定控件-----------------------代码
    private void initView() {
        et_money=findViewById(R.id.et_money_inmag);
        et_time=findViewById(R.id.et_time_inmag);
        sp_type=findViewById(R.id.sp_type_inmag);
        et_payer=findViewById(R.id.et_payer_inmag);
        et_remark=findViewById(R.id.et_remark_inmag);
        btn_modify=findViewById(R.id.bt_modify_inmag);
        btn_delete=findViewById(R.id.bt_delete_inmag);
        mhelper=new MyDBHelper(InManageActivity.this);
        db=mhelper.getWritableDatabase();
    }
    //3 获取单击的那条数据并显示出来
    private void getDataDisplay() {
        incomeBean= (IncomeBean) getIntent().getSerializableExtra("seri");
        et_money.setText(incomeBean.getMoney()+"");
        et_time.setText(incomeBean.getTime());
        //sp_type.setPrompt(incomeBean.getType());
        if (incomeBean.getType().equals("学习-奖金")){
            sp_type.setSelection(1);
        }else if (incomeBean.getType().equals("比赛-奖励")){
            sp_type.setSelection(2);
        }else if (incomeBean.getType().equals("业余-兼职")) {
            sp_type.setSelection(3);
        }else if(incomeBean.getType().equals("其他")){
            sp_type.setSelection(4);
        }else {
            sp_type.setSelection(0);
        }
        et_payer.setText(incomeBean.getPayer());
        et_remark.setText(incomeBean.getRemark());
    }
    //4 修改按钮功能
    private void btnModidfy() {
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个对象，封装一行数据
                ContentValues values=new ContentValues();
                values.put("inmoney",et_money.getText().toString());
                values.put("intime",et_time.getText().toString());
                values.put("intype",sp_type.getSelectedItem().toString());
                values.put("inpayer",et_payer.getText().toString());
                values.put("inremark",et_remark.getText().toString());
                //把该行数据更新到到收入表中
                db.update("in_come",values,"id=?",new
                        String[]{incomeBean.getId()+""});
                Toast.makeText(InManageActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        //关闭本页面，重新打开收入明细界面，即可查询修改后的结果
                        // 创建 Intent 对象
                        Intent intent=new Intent(InManageActivity.this,
                                IncomeDetailActivity.class);
                startActivity(intent);// 执行 Intent 操作
                finish();//退出当前程序，或关闭当前页面
            }
        });
    }
    //5 删除按钮功能的实现
    private void btnDelete() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从数据库中删除条记录即可
                db.delete("in_come","id=?",new
                        String[]{incomeBean.getId()+""});
                Toast.makeText(InManageActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                        //关闭本页面，重新打开收入明细界面，即可删除后的结果
                        // 创建 Intent 对象
                        Intent intent=new Intent(InManageActivity.this,
                                IncomeDetailActivity.class);
                startActivity(intent);// 执行 Intent 操作
                finish();//退出当前程序，或关闭当前页面
            }
        });
    }
}
