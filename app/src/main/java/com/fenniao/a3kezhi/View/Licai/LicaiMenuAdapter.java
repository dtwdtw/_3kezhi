package com.fenniao.a3kezhi.View.Licai;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fenniao.a3kezhi.Been.LicaiBean;
import com.fenniao.a3kezhi.R;
import com.fenniao.a3kezhi.View.OnRecycleViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class LicaiMenuAdapter extends RecyclerView.Adapter<LicaiMenuAdapter.MenuViewHolder> {
    Context context;
    List<LicaiBean.MenuItemBean> menuItems;
    OnRecycleViewItemClickListener onMenuItemClickListener;
    private List<Integer> defaultMenuIconResList=new ArrayList<>();

    public LicaiMenuAdapter(Context context, List<LicaiBean.MenuItemBean> menuItems){
        this.context=context;
        this.menuItems = menuItems;

        defaultMenuIconResList.add(R.drawable.ic_menu_share);
        defaultMenuIconResList.add(R.drawable.ic_menu_share);
        defaultMenuIconResList.add(R.drawable.ic_menu_share);
        defaultMenuIconResList.add(R.drawable.ic_menu_share);
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(context).inflate(R.layout.licai_menu_item,null);
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        if(!TextUtils.isEmpty(menuItems.get(position).getIcon())){
            Glide.with(context)
                    .load(menuItems.get(position).getIcon())
                    .placeholder(R.drawable.ic_menu_gallery)
                    .error(R.drawable.ic_menu_gallery)
                    .into(holder.iconView);
        }else if(defaultMenuIconResList.size()>position){
            holder.iconView.setImageResource(defaultMenuIconResList.get(position));
        }
        holder.textView.setText(menuItems.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }
    class MenuViewHolder extends RecyclerView.ViewHolder{
        ImageView iconView;
        TextView textView;

        public MenuViewHolder(View itemView) {
            super(itemView);
            iconView= (ImageView) itemView.findViewById(R.id.shouye_menu_icon);
            textView= (TextView) itemView.findViewById(R.id.shouye_menu_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMenuItemClickListener.onRecycleViewItemClick(v,LicaiMenuAdapter.class.getName(),LicaiRecycleListAdapter.TYPE_HEADER,-1,getAdapterPosition());
                }
            });
        }
    }
    public void OnRecycleViewItemClickListener(OnRecycleViewItemClickListener onRecycleViewItemClickListener){
        this.onMenuItemClickListener = onRecycleViewItemClickListener;
    }
}
