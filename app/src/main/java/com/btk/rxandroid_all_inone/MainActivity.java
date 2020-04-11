package com.btk.rxandroid_all_inone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createEmitterObservable();
        createIteratorObservable();
        createJustObservable();
    }

    private void createJustObservable() {
        Observable<Integer> observable = Observable.just(1,2,3,4,5,6,7);

        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer item) throws Exception {
                Log.v("==>>","createJustObservable:"+item.intValue());
            }
        });
    }

    private void createIteratorObservable() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);

        Observable<Integer> observable = Observable.fromIterable(list);

        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.v("==>>","createIteratorObservable"+integer.intValue());

            }
        });
    }

    private void createEmitterObservable() {
        Observable observable = Observable.create(emitter -> {
            emitter.onNext("1");
            emitter.onNext("2");
            emitter.onNext("3");
            emitter.onNext("4");
            emitter.onNext("5");
            emitter.onNext("6");
            emitter.onNext("7");

        });

        observable.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.v("==>>","createEmitterObservable"+o.toString());
            }
        });




    }
}
