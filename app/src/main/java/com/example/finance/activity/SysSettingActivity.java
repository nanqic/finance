package com.example.finance.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finance.MainActivity;
import com.example.finance.R;
import com.example.finance.db.MyDBHelper;
import com.example.finance.welcome.LoginActivity;

public class SysSettingActivity extends AppCompatActivity {
    //1 定义对象
    TextView txt_user;//创建一个显示用户名的文本对象
    EditText et_ypwd, et_xpwd, et_zxpwd;// 创建三个 EditText 对象
    Button bt_modify, bt_cancel;// 创建两个 Button 对象
    MyDBHelper mhelper;
    SQLiteDatabase db;
    String name;
    String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_setting);
        //第二步：绑定控件
        initView();
        //第三步：显示当前登录的用户名
        displayInfo();
        //第四步：修改按钮功能
        btnModify();
        //第五步：取消按钮功能
        btncancel();
    }
    //第二步：绑定控件-----------------代码
    private void initView() {
        txt_user=findViewById(R.id.txt_name_sys);
        et_ypwd=findViewById(R.id.et_ypwd_sys);
        et_xpwd=findViewById(R.id.et_xpwd_sys);
        et_zxpwd=findViewById(R.id.et_zxpwd_sys);
        bt_modify=findViewById(R.id.bt_modify_sys);
        bt_cancel=findViewById(R.id.bt_cancel_sys);
        mhelper=new MyDBHelper(SysSettingActivity.this);
        db=mhelper.getWritableDatabase();
    }
    //第三步：显示当前登录的用户名
    private void displayInfo() {
        name=getSharedPreferences("userinfo",0).getString("username","");
        pwd=getSharedPreferences("userinfo",0).getString("userpwd","");
        txt_user.setText(name);
    }
    //第四步：修改按钮功能
    private void btnModify() {
        bt_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取三个输入框中的内容
                String ypwd = et_ypwd.getText().toString();//获取输入的原密码
                String xpwd = et_xpwd.getText().toString();//获取输入的新密 码
                String zxpwd = et_zxpwd.getText().toString();//获取第二次输入的新密码
                //对每个密码进行逻辑判断
                if(ypwd.equals("")){
                    Toast.makeText(SysSettingActivity.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                }else if(!ypwd.equalsIgnoreCase(pwd)){
                    Toast.makeText(SysSettingActivity.this, "输入的密码与原密码不一致", Toast.LENGTH_SHORT).show();
                }else if(xpwd.equals("")){
                    Toast.makeText(SysSettingActivity.this, "请输入新密码",
                            Toast.LENGTH_SHORT).show();
                }else if(xpwd.equalsIgnoreCase(ypwd)){
                    Toast.makeText(SysSettingActivity.this, "所输入的新密码与原密码不能相同", Toast.LENGTH_SHORT).show();
                }else if(zxpwd.equals("")){
                    Toast.makeText(SysSettingActivity.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                }else if(!zxpwd.equalsIgnoreCase(xpwd)){
                    Toast.makeText(SysSettingActivity.this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues values =new ContentValues();
                    values.put("pwd",xpwd);
                    db.update("tb_userinfo",values,"name=?",new String[]{name});
                    Toast.makeText(SysSettingActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SysSettingActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    //第五步：取消按钮功能
    private void btncancel() {
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSettingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}