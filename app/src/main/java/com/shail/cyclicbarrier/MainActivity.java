package com.shail.cyclicbarrier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.cyclic_barrier).setOnClickListener(new View.OnClickListener() {
                                                                 @Override
                                                                 public void onClick(View view) {
                                                                     new CyclicBarrierTest().performTasks();
                                                                 }
                                                             }
        );

        findViewById(R.id.countdown_latch).setOnClickListener(new View.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(View view) {
                                                                      new CountDownLatchTest().startAllServices();
                                                                  }
                                                              }
        );

    }
}
