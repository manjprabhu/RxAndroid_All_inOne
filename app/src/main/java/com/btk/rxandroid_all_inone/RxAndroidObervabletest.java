package com.btk.rxandroid_all_inone;

import android.util.Log;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxAndroidObervabletest {

    public RxAndroidObervabletest() {
        testmaybe();
    }

    private void testmaybe() {

        MaybeObserver maybeObserver = new MaybeObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===","onSubscribe");
            }

            @Override
            public void onSuccess(Object o) {
                Log.v("===","Onsuccess:"+o.toString());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };

        getString().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(maybeObserver);

    }

    private Maybe<String> getString() {
        //create Maybe observable using create
        return Maybe.create(new MaybeOnSubscribe<String>() {
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                emitter.onSuccess("hello");
            }
        });

        //create Maybe observable using just
//        return Maybe.just("hello this is maybe ");
    }


    private void testSingle(boolean value) {

        SingleObserver singleObserver  = new SingleObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===","Single observable subscribed");
            }

            @Override
            public void onSuccess(Object o) {
                Log.v("===","Onsuccess:"+o.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.v("===","onError:"+e.toString());
            }
        };

        getSingleValue(value).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(singleObserver);
    }

    private Single<Boolean> getSingleValue(boolean value) {

        return Single.create(new SingleOnSubscribe<Boolean>() {

            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {
                if(value) {
                    emitter.onSuccess(true);
                } else {
                    emitter.onError(new Throwable("failure"));
                }

            }
        });
    }


    private void testcompletable() {

        CompletableObserver completableObserver = new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===","Oncompletable subscription");
            }

            @Override
            public void onComplete() {
                Log.v("===","Oncomplete");
            }

            @Override
            public void onError(Throwable e) {

            }
        };

        getValue().observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(completableObserver);

    }

    private Completable getValue() {

        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                Thread.sleep(5000);
                emitter.onComplete();
            }
        });
    }

    private void testObservable() {

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===","Subscription started:");
            }

            @Override
            public void onNext(Object o) {
                Log.v("===","onNext:"+o.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.v("===","onComplete");
            }
        };


        getAnimalObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    private Observable<String> getAnimalObservable() {

        return Observable.create(new ObservableOnSubscribe<String>() {
            /*
             * Called for each Observer that subscribes.
             *
             * @param emitter the safe emitter instance, never null
             * @throws Exception on error*/

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Cat");
                emitter.onNext("Dog");
                emitter.onNext("Horse");
                emitter.onComplete();
            }
        });

//        Create observable using "from" operator
//        return Observable.fromArray("hi","hello","who","how");

//        Create observable using "just" operator
//        return Observable.just("hi","hello","who","how","where");
    }
}