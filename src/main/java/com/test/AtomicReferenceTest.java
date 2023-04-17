package com.test;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
@ToString
class User{

    private String name;

    private int age;
}

//原子性对象引用包装类
public class AtomicReferenceTest {

    public static void main(String[] args) {
        User zs = new User("张三", 18);
        User ls = new User("李四", 20);
        java.util.concurrent.atomic.AtomicReference atomicReference=new java.util.concurrent.atomic.AtomicReference<User>();
        atomicReference.set(zs);
        System.out.println(atomicReference.compareAndSet(zs,ls)+"\t"+atomicReference.get());
        System.out.println(atomicReference.compareAndSet(zs,ls)+"\t"+atomicReference.get());
    }
}
