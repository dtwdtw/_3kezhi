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
 * Created by Administrator on 2017/5/4 0004.
 */

public class LicaiRecycleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<LicaiBean.ClassfiedBean> classfiedBeenList;
    private OnRecycleViewItemClickListener onRecycleViewItemClickListener;
    private View headView;
    private List<Integer> realTitlePositionList;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_TITLE = 1;
    public static final int TYPE_NORMAL = 2;

    public LicaiRecycleListAdapter(Context context, List<LicaiBean.ClassfiedBean> classfiedBeenList) {
        this.context = context;
        this.classfiedBeenList = classfiedBeenList;
        realTitlePositionList = getRealTitlePosition(classfiedBeenList);
    }

    private List<Integer> getRealTitlePosition(List<LicaiBean.ClassfiedBean> classfiedListBeens) {
        List<Integer> realTitlePositionList = new ArrayList<>();
        int titlePosition = 0;
        for (int i = 0; i < classfiedListBeens.size(); i++) {
            if (!TextUtils.isEmpty(classfiedListBeens.get(i).getTitleName())) {
                realTitlePositionList.add(titlePosition);
                titlePosition++;
            }
            titlePosition += classfiedListBeens.get(i).getItemList().size();
        }
        return realTitlePositionList;
    }

    @Override
    public int getItemViewType(int position) {
        if (headView == null) {
            for (int i = 0; i < realTitlePositionList.size(); i++) {
                if(realTitlePositionList.get(i)==position){
                    return TYPE_TITLE;
                }
            }
            return TYPE_NORMAL;
        } else {
            if (position == 0) {
                return TYPE_HEADER;
            } else {
                for (int i = 0; i < realTitlePositionList.size(); i++) {
                    if(realTitlePositionList.get(i)==position-1){
                        return TYPE_TITLE;
                    }
                }
                return TYPE_NORMAL;
            }
        }
    }

    public int getRealPosition(int position) {
        if (headView != null) {
            return position - 1;
        } else {
            return position;
        }
    }

    private int getItemPostion(int realPosition){
        int headPosition=0;
        for (int i = 0; i < classfiedBeenList.size(); i++) {
            if(!TextUtils.isEmpty(classfiedBeenList.get(i).getTitleName())){
                headPosition++;
            }
            if(realPosition-headPosition< classfiedBeenList.get(i).getItemList().size()){
                return realPosition-headPosition;
            }else{
                headPosition+= classfiedBeenList.get(i).getItemList().size();
            }
        }
        return -1;
    }

    private int getListPostion(int realPosition){
        int tempHeadPosition=0;
        for (int i = 0; i < classfiedBeenList.size(); i++) {
            if(!TextUtils.isEmpty(classfiedBeenList.get(i).getTitleName())){
                tempHeadPosition++;
            }
            if(realPosition-tempHeadPosition< classfiedBeenList.get(i).getItemList().size()){
                return i;
            }else{
                tempHeadPosition+= classfiedBeenList.get(i).getItemList().size();
            }
        }
        return -1;
    }

    private int getTitlePosition(int realPosition){
        int tempHeadPosition=0;
        for (int i = 0; i < classfiedBeenList.size(); i++) {
            if(!TextUtils.isEmpty(classfiedBeenList.get(i).getTitleName())){
                if(realPosition==tempHeadPosition){
                    return i;
                }
                tempHeadPosition++;
            }
            tempHeadPosition+= classfiedBeenList.get(i).getItemList().size();
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new ShouyeItemHolder(headView);
        }else  if(viewType ==TYPE_TITLE) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.licai_title_item,parent,false);
            return new ShouyeTitleHolder(itemView);
        }
        else {
            View itemView = LayoutInflater.from(context).inflate(R.layout.licai_recyclelist_item, parent,false);
            return new ShouyeItemHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }else if(getItemViewType(position)==TYPE_TITLE){
            ((ShouyeTitleHolder)holder).nameText.setText(classfiedBeenList.get(getTitlePosition(getRealPosition(position))).getTitleName());
            ((ShouyeTitleHolder)holder).destribeText.setText(classfiedBeenList.get(getTitlePosition(getRealPosition(position))).getDescripe());
            if(!TextUtils.isEmpty(classfiedBeenList.get(getTitlePosition(getRealPosition(position))).getIcon())){
                Glide.with(context)
                        .load(classfiedBeenList.get(getTitlePosition(getRealPosition(position))).getIcon())
                        .into(((ShouyeTitleHolder)holder).iconImage);
            }
        } else{
            ((ShouyeItemHolder)holder).name.setText(classfiedBeenList.get(getListPostion(getRealPosition(position))).getItemList().get(getItemPostion(getRealPosition(position))).getName());
            ((ShouyeItemHolder)holder).rateOfReturn.setText(classfiedBeenList.get(getListPostion(getRealPosition(position))).getItemList().get(getItemPostion(getRealPosition(position))).getRateOfReturn()+"%");
            ((ShouyeItemHolder)holder).longs.setText(classfiedBeenList.get(getListPostion(getRealPosition(position))).getItemList().get(getItemPostion(getRealPosition(position))).getLongth());
            ((ShouyeItemHolder)holder).info.setText(classfiedBeenList.get(getListPostion(getRealPosition(position))).getItemList().get(getItemPostion(getRealPosition(position))).getDescripe());
            ((ShouyeItemHolder)holder).longthDestribeTextView.setText(classfiedBeenList.get(getListPostion(getRealPosition(position))).getItemList().get(getItemPostion(getRealPosition(position))).getLongthDescripe());
        }
    }

    @Override
    public int getItemCount() {
        int realCount=0;
        for (int i = 0; i < classfiedBeenList.size(); i++) {
            realCount+= classfiedBeenList.get(i).getItemList().size();
        }
        realCount+= realTitlePositionList.size();
        realCount+=(headView != null ? 1 : 0);
        return realCount;
    }

    public void setOnRecycleViewItemClickListener(OnRecycleViewItemClickListener onRecycleViewItemClickListener) {
        this.onRecycleViewItemClickListener = onRecycleViewItemClickListener;
    }

    public void setHeadView(View headView) {
        this.headView = headView;
    }

    class ShouyeTitleHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView nameText;
        TextView destribeText;
        public ShouyeTitleHolder(View itemView){
            super(itemView);
            iconImage= (ImageView) itemView.findViewById(R.id.licai_item_title_icon);
            nameText=(TextView) itemView.findViewById(R.id.licai_item_title_name);
            destribeText=(TextView) itemView.findViewById(R.id.licai_item_title_describe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecycleViewItemClickListener.onRecycleViewItemClick(v, LicaiRecycleListAdapter.class.getName(),TYPE_TITLE, -1,getTitlePosition(getRealPosition(getAdapterPosition())));
                }
            });
        }
    }

    class ShouyeItemHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView rateOfReturn;
        TextView longs;
        TextView longthDestribeTextView;
        TextView info;

        public ShouyeItemHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
            rateOfReturn = (TextView) itemView.findViewById(R.id.rate_of_return);
            longs = (TextView) itemView.findViewById(R.id.longs);
            info = (TextView) itemView.findViewById(R.id.info_text);
            longthDestribeTextView = (TextView) itemView.findViewById(R.id.longth_destribe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecycleViewItemClickListener.onRecycleViewItemClick(v, LicaiRecycleListAdapter.class.getName(),TYPE_NORMAL, getListPostion(getRealPosition(getAdapterPosition())), getItemPostion(getRealPosition(getAdapterPosition())));
                }
            });
        }
    }
}
