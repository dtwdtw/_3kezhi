package com.fenniao.a3kezhi.View;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public interface OnRecycleViewItemClickListener {
    /**
     *
     * @param view
     * @param adapterClassName
     * @param viewType 视图类型
     * @param listPosition 多种视图类型的情况下，传递视图种类的position
     * @param itemPosition 多视图情况下，传递每一种视图下的position
     */
    void onRecycleViewItemClick(View view, String adapterClassName, int viewType,int listPosition, int itemPosition);
}
