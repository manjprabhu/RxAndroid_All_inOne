package com.btk.rxandroid_all_inone;


import android.util.Log;

import androidx.annotation.MainThread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ObservableUsingOperators {

    public ObservableUsingOperators() {

        test();
    }

    //Create observable using "Create" operator
    private void ObservableusingCreate() {

        String array[] = {"One", "Two","Three" };

        Observable createObservable = Observable.create(emitter -> {

            for(String x : array) {
                emitter.onNext(x);
            }
            emitter.onComplete();
        });

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===","onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.v("===","onNext:"+o.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.v("===","onError");
            }

            @Override
            public void onComplete() {
                Log.v("===","onComplete:");
            }
        };

        createObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //Create Observable using "from" opearator" , here we have used "fromarray"
    private void observableusingfromarray() {
        String[] array = {"a","b","c","d","e","f"};

        Observable observable = Observable.fromArray(array);

        Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===","onSubscribe:");
            }

            @Override
            public void onNext(String o) {
                Log.v("===","onNext:"+o);
            }

            @Override
            public void onError(Throwable e) {
                Log.v("===","onError:");
            }

            @Override
            public void onComplete() {
                Log.v("===","onComplete:");
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //Create an observable using "from" operator, here we have used "fromCallable"
    private void observableusingfromCallable() {

        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return "This is callable observable";
            }
        };

        Observable observable = Observable.fromCallable(callable);

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===","onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.v("===","onNext:"+o.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.v("===","onError");
            }

            @Override
            public void onComplete() {
                Log.v("===","onComplete");
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //Create an observable using "range" operator
    private void createObsevableusingRange() {

        Observable observable = Observable.range(9, 9);

        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===","onSubscribe");
            }

            @Override
            public void onNext(Integer o) {
                Log.v("===","onNext:"+o.intValue());
            }

            @Override
            public void onError(Throwable e) {
                Log.v("===","onError");
            }

            @Override
            public void onComplete() {
                Log.v("===","onComplete");
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //Create an observable using "interval" opearator.
    private void createObservableusingInterval() {

        Observable observable = Observable.interval(1, TimeUnit.SECONDS);

        Observer observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===","onSubscribe");
            }

            @Override
            public void onNext(Long o) {
                Log.v("===","onNext:"+o);
            }

            @Override
            public void onError(Throwable e) {
                Log.v("===","onError");
            }

            @Override
            public void onComplete() {
                Log.v("===","onComplete");
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //Create an observable using "interval" opearator.
    private void createObservableusingIntervalRange() {

        Observable observable = Observable.intervalRange(1, 10,1,1, TimeUnit.SECONDS);

        Observer observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===","onSubscribe");
            }

            @Override
            public void onNext(Long o) {
                Log.v("===","onNext:"+o);
            }

            @Override
            public void onError(Throwable e) {
                Log.v("===","onError");
            }

            @Override
            public void onComplete() {
                Log.v("===","onComplete");
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
