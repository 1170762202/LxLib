package com.zlx.lxlib.api;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019\1\7 0007.
 */

public class MapSet {

    public static Map<String, Object> getLogin(int type, String wxCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("wxCode", wxCode);
        return map;
    }

}
