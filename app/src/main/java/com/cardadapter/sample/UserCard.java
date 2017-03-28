package com.cardadapter.sample;

import com.cardlibrary.annotation.CardMap;
import com.cardlibrary.base.BaseCard;

/**
 * Created by bighero on 2017/3/28.
 * E-mail:573471902@qq.com
 */
@CardMap(UserProvider.class)
public class UserCard extends BaseCard {
    public String name;

    public UserCard() {
        type = 1;
        sort = 1;
    }

    public UserCard(String name) {
        type = 1;
        sort = 1;
        this.name = name;
    }
}
