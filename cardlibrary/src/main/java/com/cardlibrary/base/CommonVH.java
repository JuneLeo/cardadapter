package com.cardlibrary.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

/**
 * Created by bighero on 2017/3/28.
 * E-mail:573471902@qq.com
 */
public abstract class CommonVH<T extends BaseCard> extends RecyclerView.ViewHolder {

    public CommonVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(T t, int position);
}
