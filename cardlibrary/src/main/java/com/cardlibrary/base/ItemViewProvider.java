package com.cardlibrary.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cardlibrary.adapter.CardAdapter;

/**
 * Created by bighero on 2017/3/28.
 * E-mail:573471902@qq.com
 */
public abstract class ItemViewProvider<C extends BaseCard, V extends RecyclerView.ViewHolder> {

    public CardAdapter.OnItemClick mOnItemClick;

    public ItemViewProvider(CardAdapter.OnItemClick mOnItemClick) {
        this.mOnItemClick = mOnItemClick;
    }


    @NonNull
    public abstract V onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    public abstract void onBindViewHolder(@NonNull V holder, @NonNull C card, @NonNull int position);


}
