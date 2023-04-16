package com.test;

//饿汉单例
public class HungrySigleton {

    private HungrySigleton(){

    }

    private static final HungrySigleton INSTANCE=new HungrySigleton();
}
