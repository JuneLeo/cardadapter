package com.cardlibrary.provider;

import com.cardlibrary.annotation.CardMap;

import java.util.HashMap;

/**
 * Created by bighero on 2017/3/28.
 * E-mail:573471902@qq.com
 */
public class CardMapProvider {
    private static HashMap<Class, String> cardMapHashMaps = new HashMap<>();

    public static String getProviderName(Class<?> clazz) {
        String providerName = cardMapHashMaps.get(clazz);

        if (providerName == null) {

            CardMap cardMap = clazz.getAnnotation(CardMap.class);

            if (cardMap == null) {
                throw new RuntimeException(String.format("%s must be is a annotation!", clazz.getSimpleName()));
            }
            providerName = cardMap.value().getName();

            cardMapHashMaps.put(clazz, providerName);


        }
        return providerName;
    }
}
