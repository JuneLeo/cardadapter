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
public class UserProvider extends ItemViewProvider<UserCard,UserProvider.VHUser> {


    public UserProvider(CardAdapter.OnItemClick mOnItemClick) {
        super(mOnItemClick);
    }

    @NonNull
    @Override
    public VHUser onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new VHUser(inflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VHUser holder, @NonNull UserCard card, @NonNull int position) {
        if (holder instanceof VHUser){
            holder.bind(card,position);
        }
    }


    public class VHUser extends CommonVH<UserCard>{

        @Bind(R.id.tv_name)
        TextView mTvName;

        public VHUser(View itemView) {
            super(itemView);


        }

        @Override
        public void bind(final UserCard t, final int position) {
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
