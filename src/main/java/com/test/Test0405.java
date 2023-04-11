package com.test;


public class Test0405 {

    public static void main(String[] args) {
        StringBuffer a=new StringBuffer("a");
        StringBuffer b=new StringBuffer("b");
        function(a,b);
        System.out.println(a+" "+b);
    }

    private static void function(StringBuffer x, StringBuffer y) {
        x=x.append(y);
        y=x;
    }
}
