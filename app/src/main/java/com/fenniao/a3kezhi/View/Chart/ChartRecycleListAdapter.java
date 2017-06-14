package com.fenniao.a3kezhi.View.Chart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.OnRecycleViewItemClickListener;

import java.util.List;

import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Message;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class ChartRecycleListAdapter extends RecyclerView.Adapter<ChartRecycleListAdapter.ChartViewHolder> {
    OnRecycleViewItemClickListener onRecycleViewItemClickListener;
    List<Message> messageList;
    Context context;

    public ChartRecycleListAdapter(Context context,List<Message> messageList) {
        this.context=context;
        this.messageList = messageList;
    }

    public void setOnRecycleViewItemClickListener(OnRecycleViewItemClickListener onRecycleViewItemClickListener){
        this.onRecycleViewItemClickListener=onRecycleViewItemClickListener;
    }

    @Override
    public ChartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(context).inflate(R.layout.item_receive_text,parent,false);
        return new ChartViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public void onBindViewHolder(ChartViewHolder holder, int position) {
        //此处报空指针错误，说明JMessageClient未登陆
        Message message=messageList.get(position);
        TextContent textContent=(TextContent)message.getContent();
        String temp=textContent.getText();
        holder.messageText.setText(temp);
    }

    class ChartViewHolder extends RecyclerView.ViewHolder {
        public ImageView headImg;
        public TextView messageText;
        public ChartViewHolder(final View itemView) {
            super(itemView);
            headImg= (ImageView) itemView.findViewById(R.id.head_img);
            messageText= (TextView) itemView.findViewById(R.id.message_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecycleViewItemClickListener.onRecycleViewItemClick(itemView,ChartRecycleListAdapter.class.getName(),-1,-1,getAdapterPosition());
                }
            });
        }
    }
}
