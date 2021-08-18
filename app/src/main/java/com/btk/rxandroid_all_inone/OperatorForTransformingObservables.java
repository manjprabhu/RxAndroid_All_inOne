package com.btk.rxandroid_all_inone;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OperatorForTransformingObservables {

    public OperatorForTransformingObservables() {
        flatmapopearator();
    }

    //Map operator transforms the each item emitted by observable by applying some function, and return the modified item.
    private void mapoperator() {

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 2));

        Observable observable = Observable.fromIterable(list);

        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===", "onSubscribe");
            }

            @Override
            public void onNext(Integer o) {
                Log.v("===", "onNext:" + o.intValue());
            }

            @Override
            public void onError(Throwable e) {
                Log.v("===", "onError");
            }

            @Override
            public void onComplete() {
                Log.v("===", "onComplete");
            }
        };

        observable.subscribeOn(Schedulers.io())
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer o) throws Exception {
                        return o * 2;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //Flatmap operator transforms the each item emitted by an observable by applying some function and instead of returning the modified item
    // it returns the another item
    private void flatmapopearator() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        Observable OriginalObservable = Observable.create(emitter -> {
            for (Integer x : list) {
                if (!emitter.isDisposed()) {
                    emitter.onNext(x);
                }
            }

            if (emitter.isDisposed()) {
                emitter.onComplete();
            }
        });

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===", "onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.v("===", "onNext:" + o.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.v("===", "onError");
            }

            @Override
            public void onComplete() {
                Log.v("===", "onComplete");
            }
        };

        OriginalObservable.subscribeOn(Schedulers.io())
                .concatMap(new Function<Integer, Observable>() {
                    @Override
                    public Observable apply(Integer o) throws Exception {
                        return getModifiedObservable(o);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private Observable getModifiedObservable(Integer integer) {
        Observable modifiedObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                if (!emitter.isDisposed()) {
                    emitter.onNext(integer * 2);
                } else if (emitter.isDisposed()) {
                    emitter.onComplete();
                }

            }
        });
        return modifiedObservable;
    }

}
