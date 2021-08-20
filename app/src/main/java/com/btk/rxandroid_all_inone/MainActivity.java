package com.btk.rxandroid_all_inone;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TransformOperators transformOperators = new TransformOperators();
        createIteratorObservable();
    }

    private void createJustObservable() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7);

        Observable<String> stringObservable = Observable.just("One", "two", "three");

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v(TAG, "Subscribed createJustObservable");
            }

            @Override
            public void onNext(Object o) {
                Log.v(TAG, "createJustObservable: " + o.toString());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                Log.v(TAG, "createJustObservable onComplete");
            }
        };

        stringObservable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        stringObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(result -> {
                    Log.v(TAG, "doOnSubscribe:" + result);
                })
                .subscribe(result -> {
                    Log.v(TAG, "Result of just:" + result);
                });
    }

    private void createIteratorObservable() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        Observable<Integer> observable = Observable.fromArray(1, 2, 3, 4, 5);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.v(TAG, "Result :" + result);
                });

    }

    private void createEmitterObservable() {
        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onNext("4");
                emitter.onNext("5");
                emitter.onNext("6");
                emitter.onNext("7");

            }
        });

        observable.subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.v(TAG, "onNext:" + o.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.v(TAG, "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.v(TAG, "onComplete");
            }
        });
    }

    private void createObservableFromArray() {
        Integer[] array = new Integer[10];
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }

        Observable observable = Observable.fromArray(array);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( result-> {
                    Log.v(TAG,"Array:"+result);
                });
    }
}
