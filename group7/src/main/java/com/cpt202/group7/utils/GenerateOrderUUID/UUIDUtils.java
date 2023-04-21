package com.cpt202.group7.utils.GenerateOrderUUID;

import java.util.UUID;

public class UUIDUtils {
    public UUIDUtils() {
    }

    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}