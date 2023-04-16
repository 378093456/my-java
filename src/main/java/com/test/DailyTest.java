package com.test;

//i++和++i运算
public class DailyTest {

    public static void main(String[] args) {
        int i=-1;
        i=i++;//i=-1
        int j=i++;//j=-1    i=0
        j=j--;//j=-1
        int k=i++ + j-- - ++i * --j / j++ + i-- - ++j * --i;//i=0  j=-1    k=-1  0+(-1)-2*(-3)/(-3)+2-(-1)*0

        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }
}
