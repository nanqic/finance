package com.example.finance.activity;

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
import com.example.finance.MainActivity;
import com.example.finance.R;
import com.example.finance.db.MyDBHelper;

public class NewIncomeActivity extends AppCompatActivity {

    //1 定义对象
    EditText et_money,et_time,et_payer,et_remark;
    Spinner sp_type;
    Button bt_sava,bt_cancel;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_income);
        //2 绑定控件
        initView();
        //3 保存按钮功能的实现
        btnSave();
        //4 取消按钮功能的实现
        btnCancel();
    }
    //2 绑定控件
    private void initView() {
        et_money=findViewById(R.id.et_money_newin);
        et_time=findViewById(R.id.et_time_newin);
        sp_type=findViewById(R.id.sp_type_newin);
        et_payer=findViewById(R.id.et_payer_newin);
        et_remark=findViewById(R.id.et_remark_newin);
        bt_sava=findViewById(R.id.bt_save_newin);
        bt_cancel=findViewById(R.id.bt_cancel_newin);
        mhelper=new MyDBHelper(NewIncomeActivity.this);
        db=mhelper.getWritableDatabase();
    }
    //3 保存按钮功能
    private void btnSave() {
        bt_sava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的内容保存到数据库的收入表中
                ContentValues values=new ContentValues();
                values.put("inmoney",et_money.getText().toString());
                values.put("intime",et_time.getText().toString());
                values.put("intype",sp_type.getSelectedItem().toString());
                values.put("inpayer",et_payer.getText().toString());
                values.put("inremark",et_remark.getText().toString());
                db.insert("in_come",null,values);
                Toast.makeText(NewIncomeActivity.this,"保存成功!",Toast.LENGTH_SHORT);
                //刷新本页面
                Intent intent = new
                        Intent(NewIncomeActivity.this,NewIncomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    //4 取消按钮功能的实现--------代码
    private void btnCancel() {
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewIncomeActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}