package com.cardadapter.sample;

import com.cardlibrary.annotation.CardMap;
import com.cardlibrary.base.BaseCard;

/**
 * Created by bighero on 2017/3/28.
 * E-mail:573471902@qq.com
 */
//@CardMap(HumorProvider.class)
public class HumorCard extends BaseCard {
    public String name;
    public HumorCard() {
        type = 2;
        sort = 2;
    }

    public HumorCard(String name) {
        this.name = name;
        type = 2;
        sort = 2;
    }
}
