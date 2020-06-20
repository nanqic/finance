package com.example.finance.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finance.MainActivity;
import com.example.finance.R;
import com.example.finance.db.MyDBHelper;

public class LoginActivity extends AppCompatActivity {
    //1 定义对象
    EditText et_name,et_pwd;
    Button btn_newregister,btn_login;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //2 绑定控件
        initView();
        //3 登录按钮功能的实现
        btnLogin();
        //4 新用户注册按钮功能的实现
        btnNewRegister();
    }
    //2 绑定控件
    private void initView() {
        et_name=findViewById(R.id.et_name_lg);
        et_pwd=findViewById(R.id.et_pwd_lg);
        btn_newregister=findViewById(R.id.bt_newregister_lg);
        btn_login=findViewById(R.id.bt_login_lg);
        mhelper=new MyDBHelper(LoginActivity.this);
        db=mhelper.getWritableDatabase();
    }
    //3 登录按钮功能的实现
    private void btnLogin() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先：获取输入的用户名和密码
                String inputname=et_name.getText().toString();
                String inputpwd=et_pwd.getText().toString();
                //其次：对获取的用户名和密码进行判断
                if(inputname.equals("")||inputpwd.equals("")){//用户名 或密码为空
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }else{//用户名或密码不为空时，我们再对输入的正确性进行判断。
                    // 根据输入的用户名和密码从数据库中查询
                    Cursor cursor =db.rawQuery("select * from tb_userinfo where name=? and pwd=?",new String[]{inputname,inputpwd});
                    //根据查询到的结果进行判断
                    if (cursor.moveToNext()){//查询到时
                        String getname=cursor.getString(cursor.getColumnIndex("name"));
                        String getpwd=cursor.getString(cursor.getColumnIndex("pwd"));

                        if(inputname.equalsIgnoreCase(getname)&&inputpwd.equalsIgnoreCase(getpwd)){
                            // 将登录信息保存到xml中
                            SharedPreferences.Editor editor=getSharedPreferences("userinfo",0).edit();
                            editor.putString("username",inputname);
                            editor.putString("userpwd",inputpwd);
                            editor.commit();
                            Toast.makeText(LoginActivity.this,"用户名和密码正确，欢迎登陆",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }else{//没有查询到结果时
                        Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        et_name.setText("");
                        et_pwd.setText("");
                    }
                }
            }
        });
    }
    //4 新用户注册按钮功能的实现
    private void btnNewRegister() {
        btn_newregister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}