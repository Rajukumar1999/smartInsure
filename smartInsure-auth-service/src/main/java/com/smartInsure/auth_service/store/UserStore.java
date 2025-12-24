package com.smartInsure.auth_service.store;

import java.util.concurrent.ConcurrentHashMap;

public class UserStore {
    public static final ConcurrentHashMap<String,String> USERS  = new ConcurrentHashMap<>();
}
