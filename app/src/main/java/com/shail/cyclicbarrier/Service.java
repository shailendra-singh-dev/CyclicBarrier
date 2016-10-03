package com.shail.cyclicbarrier;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by iTexico Developer on 9/29/2016.
 */

public class Service implements Runnable{

    private static final String TAG = Service.class.getSimpleName();
    private final String name;
    private final int timeToStart;
    private final CountDownLatch latch;

    public Service(String name, int timeToStart, CountDownLatch latch){
        this.name = name;
        this.timeToStart = timeToStart;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeToStart);
        } catch (InterruptedException ex) {
            Log.i(TAG,"Service got interuppted while sleeping..");
        }
        System.out.println( name + " is Up");
        latch.countDown(); //reduce count of CountDownLatch by 1
    }

}
