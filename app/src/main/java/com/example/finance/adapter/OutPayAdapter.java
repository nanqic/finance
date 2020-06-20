package com.example.finance.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finance.R;
import com.example.finance.bean.OutPayBean;
import com.example.finance.manage.PayManageActivity;

import java.util.List;

public class OutPayAdapter extends RecyclerView.Adapter<OutPayAdapter.ViewHolder> {
    Context mcontext;
    List<OutPayBean> arr2;
    public OutPayAdapter(Context mcontext, List<OutPayBean> arr2) {
        this.mcontext = mcontext;
        this.arr2 = arr2;
    }
    //用于创建 ViewHolder 实例
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.recy_item_out,parent,false);
        ViewHolder mholder=new ViewHolder(view);
        return mholder;
    }

    //对 RecyclerView 子项进行赋值
    @Override
    public void onBindViewHolder(OutPayAdapter.ViewHolder mholder, int position) {
        final OutPayBean outPayBean=arr2.get(position);
        mholder.item_payee.setText("付款-给"+outPayBean.getPayee());
        mholder.item_type.setText(outPayBean.getType());
        mholder.item_time.setText(outPayBean.getTime());
        mholder.item_remark.setText(outPayBean.getRemark());
        mholder.item_money.setText("-"+outPayBean.getMoney());
        //完善：单击某一个条目，跳转到收入管理页面
        mholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到收入管理页面的代码
                Intent intent=new Intent(mcontext, PayManageActivity.class);
                intent.putExtra("sero",outPayBean);
                mcontext.startActivity(intent);
                ((Activity)mcontext).finish();
            }
        });
    }
    //recyclerView 一共有多少子项
    @Override
    public int getItemCount() {
        return arr2.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_payee,item_type,item_time,item_remark,item_money;
        public ViewHolder( View itemView) {
            super(itemView);
            item_payee=itemView.findViewById(R.id.item_payee_out);
            item_type=itemView.findViewById(R.id.item_type_out);
            item_time=itemView.findViewById(R.id.item_time_out);
            item_remark=itemView.findViewById(R.id.item_remark_out);
            item_money=itemView.findViewById(R.id.item_money_out);
        }
    }
}