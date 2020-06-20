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
import com.example.finance.bean.IncomeBean;
import com.example.finance.manage.InManageActivity;

import java.util.List;

public class IncomeAdapter  extends RecyclerView.Adapter<IncomeAdapter.ViewHolder> {
    Context mcontext;
    List<IncomeBean> arr2;
    public IncomeAdapter(Context mcontext, List<IncomeBean> arr2) {
        this.mcontext = mcontext;
        this.arr2 = arr2;
    }
    //用于创建 ViewHolder 实例
    // RecyclerView: 安卓5推出的新UI控件 类似ListView
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.recy_item_in,parent,false);
        ViewHolder mholder=new ViewHolder(view);
        return mholder;
    }

    //对 RecyclerView 子项进行赋值的
    @Override
    public void onBindViewHolder(IncomeAdapter.ViewHolder mholder, int position) {
        final IncomeBean incomeBean=arr2.get(position);
        mholder.item_payer.setText("收款-来自"+incomeBean.getPayer());
        mholder.item_type.setText(incomeBean.getType());
        mholder.item_time.setText(incomeBean.getTime());
        mholder.item_remark.setText(incomeBean.getRemark());
        mholder.item_money.setText("+"+incomeBean.getMoney());
        //完善：单击某一个条目，跳转到收入管理页面
        mholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到收入管理页面的代码
                Intent intent=new Intent(mcontext, InManageActivity.class);
                intent.putExtra("seri",incomeBean);
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
        TextView item_payer,item_type,item_time,item_remark,item_money;
        public ViewHolder( View itemView) {
            super(itemView);
            item_payer=itemView.findViewById(R.id.item_payer_in);
            item_type=itemView.findViewById(R.id.item_type_in);
            item_time=itemView.findViewById(R.id.item_time_in);
            item_remark=itemView.findViewById(R.id.item_remark_in);
            item_money=itemView.findViewById(R.id.item_money_in);
        }
    }
}