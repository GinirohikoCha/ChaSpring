package com.github.ginirohikocha.spring.vo;

import java.util.HashMap;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/22 22:04
 */
public class Json extends HashMap<String, Object> {

    private static final String KEY_CODE = "code";
    private static final String KEY_MSG = "msg";
    private static final String KEY_DATA = "data";

    public static final int DEFAULT_SUCC_CODE = 200;
    public static final int DEFAULT_FAIL_CODE = 500;
    public static final String DEFAULT_SUCC_MSG = "操作成功";
    public static final String DEFAULT_FAIL_MSG = "操作失败";

    public Json() {
        this.put(KEY_CODE, DEFAULT_SUCC_CODE);
        this.put(KEY_MSG, DEFAULT_SUCC_MSG);
    }

    public Json(int code, String msg, Object data) {
        this.put(KEY_CODE, code);
        if (msg != null && !msg.isEmpty()) {
            this.put(KEY_MSG, msg);
        }
        if (data != null){
            this.put(KEY_DATA, data);
        }
    }

    /*
    静态快速生成
     */

    /**
     * 操作成功
     * @return Json
     */
    public static Json succ() {
        return new Json();
    }

    /**
     * 操作失败
     * @return Json
     */
    public static Json fail() {
        return new Json(DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, null);
    }

    /*
    键值增改
     */

    public Json code(int code) {
        return this.put(KEY_CODE, code);
    }

    public Json msg(String msg) {
        return this.put(KEY_MSG, msg);
    }

    public Json data(Object data) {
        return this.put(KEY_DATA, data);
    }

    @Override
    public Json put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
