package com.cardadapter.sample;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cardlibrary.adapter.CardAdapter;
import com.cardlibrary.base.CommonVH;
import com.cardlibrary.base.ItemViewProvider;

import butterknife.Bind;

/**
 * Created by bighero on 2017/3/28.
 * E-mail:573471902@qq.com
 */
public class HumorProvider extends ItemViewProvider<HumorCard, HumorProvider.VHHumor> {

    public HumorProvider(CardAdapter.OnItemClick mOnItemClick) {
        super(mOnItemClick);
    }

    @NonNull
    @Override
    public VHHumor onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new VHHumor(inflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VHHumor holder, @NonNull HumorCard card, @NonNull int position) {
        if (holder instanceof VHHumor) {
            holder.bind(card, position);


        }
    }


    public class VHHumor extends CommonVH<HumorCard> {
        @Bind(R.id.tv_name)
        TextView mTvName;

        public VHHumor(View itemView) {
            super(itemView);


        }

        @Override
        public void bind(final HumorCard t, final int position) {
            mTvName.setText(t.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClick != null) {
                        mOnItemClick.OnClick(t, position);
                    }
                }
            });
        }


    }
}
