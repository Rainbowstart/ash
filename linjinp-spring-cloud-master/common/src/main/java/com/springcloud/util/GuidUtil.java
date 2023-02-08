package com.springcloud.util;

import java.util.UUID;

/**
 * 生成 Guid 作为 Id
 * @author: linjinp
 * @create: 2019-04-22 10:47
 * @throw
 **/
public class GuidUtil {

    /**
     * UUID类来生成GUID
     * @return
     */
    public static String guid() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        System.out.println(guid());
    }
}
