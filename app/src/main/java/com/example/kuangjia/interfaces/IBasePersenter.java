package com.example.kuangjia.interfaces;



//定义一个关联V层的接口
//定义一个V层接口的泛型类
public interface IBasePersenter<V extends IBaseView> {
    void attachView(V view);
    void detachView();
}
