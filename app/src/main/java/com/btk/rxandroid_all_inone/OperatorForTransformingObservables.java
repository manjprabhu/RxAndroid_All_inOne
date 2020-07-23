package com.btk.rxandroid_all_inone;

import android.util.Log;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OperatorForTransformingObservables {

    public OperatorForTransformingObservables() {
        mapoperator();
    }

    private void mapoperator() {

        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,2));

        Observable observable =  Observable.fromIterable(list);

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
                .map(new Function<Integer,Integer>() {
                    @Override
                    public Integer apply(Integer o) throws Exception {
                        return o*2;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
