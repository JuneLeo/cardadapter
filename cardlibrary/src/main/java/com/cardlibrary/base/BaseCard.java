package com.cardlibrary.base;

/**
 * Created by bighero on 2017/3/28.
 * E-mail:573471902@qq.com
 */
public abstract class BaseCard implements Comparable<BaseCard> {
    public int sort = -1;
    public int type = -1;

    @Override
    public int compareTo(BaseCard another) {
        return sort - another.sort;
    }
}
