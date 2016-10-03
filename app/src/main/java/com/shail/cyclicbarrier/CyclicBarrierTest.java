package com.shail.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by iTexico Developer on 9/29/2016.
 */

public class CyclicBarrierTest {
    List<Integer> list = new ArrayList<Integer>();

    CyclicBarrierTest() {
    }

    final private CyclicBarrier barrierTest = new CyclicBarrier(3, new Runnable() {
        public void run() {
            addListvalue();
        }
    });

    public void performTasks() {
        new Thread(new Task(1, 3)).start();
        new Thread(new Task(4, 6)).start();
        new Thread(new Task(7, 9)).start();
    }

    private void add(int start, int end) {
        int sum = 0;
        for (int s = start; s <= end; s++) {
            sum += s;
        }
        list.add(sum);
        System.out.println("Thread#" + Thread.currentThread().getName() + " Addition:" + sum);
    }

    private void addListvalue() {
        int total = 0;
        for (int j = 0; j < list.size(); j++) {
            total += list.get(j);
        }
        System.out.println("Total Addition:" + total);
    }

    private final class Task implements Runnable {
        int start = 0;
        int end = 0;

        Task(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            add(start, end);
            try {
                //Waits until all parties have invoked await on this barrier.
                barrierTest.await();
            } catch (InterruptedException ex) {
                return;
            } catch (BrokenBarrierException ex) {
                return;
            }
        }
    }
}
