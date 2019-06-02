package com.iluwatar.singleton.attempt;

public class LoadClass {

    static {
        try {
            System.out.println(Thread.currentThread().getName() + "init LoadClass start");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "init LoadClass end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
