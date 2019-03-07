package cn.benjamin.shop.utils;

import java.util.UUID;

/**
 * Created by Benjamin on 2018/12/18.
 */

public class UUIDUtils {
    /**
     * 获得随机的字符串
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
