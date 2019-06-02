package com.iluwatar.singleton.attempt;

public class App {


    public static void main(String[] args) {
        Runnable runnable=()->{
            System.out.println(Thread.currentThread().getName() + " start");
            new LoadClass();
            System.out.println(Thread.currentThread().getName() + " end");
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
