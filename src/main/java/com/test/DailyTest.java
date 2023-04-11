package com.test;

//i++和++i运算
public class DailyTest {

    public static void main(String[] args) {
        int i=6;
        i=i++;//i=6
        int j=i++;//j=6    i=7
        j=j--;//j=6
        int k=i++ - j-- + ++i - --j + i*j - j++ - i-- - ++j - --i;//i=7  j=6    k=16  7-6+9-4+9*4-4-9-6-7

        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }
}
