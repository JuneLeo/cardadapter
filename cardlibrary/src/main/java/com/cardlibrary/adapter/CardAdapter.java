package com.cardlibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cardlibrary.base.BaseCard;
import com.cardlibrary.base.ItemViewProvider;
import com.cardlibrary.provider.CardMapProvider;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bighero on 2017/3/28.
 * E-mail:573471902@qq.com
 */
public class CardAdapter extends RecyclerView.Adapter {

    protected List<BaseCard> mData;
    protected Map<Integer, ItemViewProvider> mProviders;
    protected Map<String, String> mCard;
    public OnItemClick mOnItemClick;

    public CardAdapter() {
        this.mData = new ArrayList<>();
        mProviders = new HashMap<>();
        mCard = new HashMap<>();
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {

        BaseCard t = mData.get(position);
        //important init ItemViewProvider
        initItemViewProvider(t.type, position);

        return t.type;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ItemViewProvider provider = mProviders.get(viewType);

        return provider.onCreateViewHolder(inflater, parent);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ItemViewProvider provider = mProviders.get(mData.get(position).type);

        provider.onBindViewHolder(holder, mData.get(position), position);
    }

    /**
     * 填充数据
     *
     * @param list
     * @param needSort
     */
    public void addAll(List list, boolean needSort) {
        mData.addAll(list);
        if (needSort) {
            Collections.sort(mData);
        }
        notifyDataSetChanged();
    }

    /**
     * 提供了ViewProvider
     *
     * @param viewType 类型
     * @param position 位置
     */
    private void initItemViewProvider(int viewType, int position) {

        if (!mProviders.containsKey(viewType)) {
            ItemViewProvider provider = null;

            String providerName;
            //如果手动注册的，就从手动注册中获取provider，否则从注释中获取
            if (mCard.containsKey(mData.get(position).getClass().getName())) {
                providerName = mCard.get(mData.get(position).getClass().getName());
            } else {
                providerName = CardMapProvider.getProviderName(mData.get(position).getClass());
            }
            try {
                Constructor c = Class.forName(providerName).getConstructor(OnItemClick.class);
                provider = (ItemViewProvider) c.newInstance(mOnItemClick);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (provider == null) {
                throw new RuntimeException(String.format("%s must be have a ItemViewProvider", mData.get(position).getClass().getSimpleName()));
            }
            mProviders.put(viewType, provider);

        }
    }

    /**
     * 手动注册provider
     *
     * @param card
     * @param provider
     */
    public void registProvider(Class<? extends BaseCard> card, Class<? extends ItemViewProvider> provider) {
        mCard.put(card.getName(), provider.getName());
    }

    /**
     * 事件接口
     */
    public interface OnItemClick {

        void OnClick(BaseCard baseCard, int position);

        void OnLongClick(BaseCard baseCard, int position);
    }


    /**
     * 注册监听
     *
     * @param mOnItemClick
     */
    public void setOnItemClick(OnItemClick mOnItemClick) {
        this.mOnItemClick = mOnItemClick;
    }


}
