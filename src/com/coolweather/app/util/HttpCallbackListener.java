package com.coolweather.app.util;

/**
 * Created by zhenguo on 2016/1/1.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
