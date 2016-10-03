package com.shail.cyclicbarrier;

import java.util.concurrent.CountDownLatch;

/**
 * Created by iTexico Developer on 9/29/2016.
 */

public class CountDownLatchTest {

    public void startAllServices() {
        final CountDownLatch latch = new CountDownLatch(3);
        Thread cacheService = new Thread(new Service("CountDownLatch#CacheService", 1000, latch));
        Thread alertService = new Thread(new Service("CountDownLatch#AlertService", 1000, latch));
        Thread validationService = new Thread(new Service("CountDownLatch#ValidationService", 1000, latch));

        cacheService.start(); //separate thread will initialize CacheService
        alertService.start(); //another thread for AlertService initialization
        validationService.start();

        // application should not start processing any thread until all service is up
        // and ready to do there job.
        // Countdown latch is idle choice here, main thread will start with count 3
        // and wait until count reaches zero. each thread once up and read will do
        // a count down. this will ensure that main thread is not started processing
        // until all services is up.

        //count is 3 since we have 3 Threads (Services)

        try {
            latch.await();  //main thread is waiting on CountDownLatch to finish
            System.out.println("CountDownLatch# All services are up, Application is starting now");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
