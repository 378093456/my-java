package com.test;

//volatile关键字保证多线程对变量的可见性
public class VolatileTest {

    private int i;

    //保证多线程对变量的可见性，用volatile关键字修饰
//    volatile int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public static void main(String[] args) {
        VolatileTest volatileTest=new VolatileTest();
        for(int x=0;x<5;x++){
            new Thread(()->{
                while(volatileTest.getI()<10){

                }
                System.out.println("it's ok!");
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        volatileTest.setI(20);
    }
}
