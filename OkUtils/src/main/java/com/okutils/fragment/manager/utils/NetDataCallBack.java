package com.okutils.fragment.manager.utils;

/**
 * Created by Administrator on 2017/9/21.
 */

public interface NetDataCallBack<T> {
    void success(T t);
    void field(int position,String str);
}

