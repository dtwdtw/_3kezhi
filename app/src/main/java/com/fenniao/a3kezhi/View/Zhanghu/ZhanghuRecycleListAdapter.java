package com.fenniao.a3kezhi.View.Zhanghu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fenniao.a3kezhi.Been.ZhanghuItemBean;
import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.OnRecycleViewItemClickListener;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class ZhanghuRecycleListAdapter extends RecyclerView.Adapter<ZhanghuRecycleListAdapter.ZhanghuItemHolder> {
    Context context;
    List<ZhanghuItemBean> zhanghuItemBeanList;
    OnRecycleViewItemClickListener onItemClickListener;
    public ZhanghuRecycleListAdapter(Context context, List<ZhanghuItemBean> zhanghuItemBeanList){
        this.context=context;
        this.zhanghuItemBeanList=zhanghuItemBeanList;
    }
    @Override
    public ZhanghuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.zhanghu_recyclelist_item,parent,false);
        return new ZhanghuItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ZhanghuItemHolder holder, int position) {
        holder.itemIcon.setImageResource(zhanghuItemBeanList.get(position).getItemIconResID());
        holder.itemName.setText(zhanghuItemBeanList.get(position).getItemName());
    }

    @Override
    public int getItemCount() {
        return zhanghuItemBeanList.size();
    }

    public void setOnItemClickListener(OnRecycleViewItemClickListener onItemClickListener){
        this.onItemClickListener =onItemClickListener;
    }

    class ZhanghuItemHolder extends RecyclerView.ViewHolder{
        ImageView itemIcon;
        TextView itemName;
        public ZhanghuItemHolder(View itemView) {
            super(itemView);
            itemIcon= (ImageView) itemView.findViewById(R.id.item_icon);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onRecycleViewItemClick(v,ZhanghuRecycleListAdapter.class.getName(),-1,-1,getAdapterPosition());
                }
            });
        }
    }
}
