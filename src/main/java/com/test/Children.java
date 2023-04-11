package com.test;

import java.nio.file.StandardWatchEventKinds;
import java.util.concurrent.Callable;

public class Children extends Father {

    private String name="children";

    public static void main(String[] args) {
        Children children=new Children();
        System.out.println(children.getName());
    }
}
