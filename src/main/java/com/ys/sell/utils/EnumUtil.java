package com.ys.sell.utils;

import com.ys.sell.enums.CodeEnum;

/**
 * @author HD
 * @date 2018/10/14 20:29
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
