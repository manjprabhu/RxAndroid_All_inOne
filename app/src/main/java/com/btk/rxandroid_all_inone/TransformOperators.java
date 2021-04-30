package com.btk.rxandroid_all_inone;

import android.util.Log;

import com.btk.rxandroid_all_inone.data.Employee;
import com.btk.rxandroid_all_inone.data.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TransformOperators {

    public TransformOperators() {
        testFlatmap();
    }

    private Observable getEmployeeObservable(Person person) {

        int[] salary = {100, 200, 350};
        List<Employee> employees = new ArrayList<>();

        for (int x = 0; x < salary.length; x++) {

            Employee employee = new Employee();
            employee.setSalary(salary[x]);
            employees.add(employee);
        }

        Observable observable = Observable.create(emitter -> {
            if (!emitter.isDisposed()) {
                final int delay = new Random().nextInt(2);
                person.setSalary(100);
                emitter.onNext(person);
            }

        });
        return observable;
    }

    private Observable getPersonObservable() {

        String[] name = {"A", "B", "C", "D", "E", "F", "G"};
        List<Person> personList = new ArrayList<>();

        for (int x = 0; x < name.length; x++) {
            Person person = new Person();
            person.setName(name[x]);
            person.setAge((x * 2) + 10);
            personList.add(person);
        }

        Observable personObservable = Observable.create(emitter -> {

            if (!emitter.isDisposed()) {
                for (Person person : personList) {
                    emitter.onNext(person);
                }
            } else {
                emitter.onComplete();
            }
        });
        return personObservable;
    }

    private void testFlatmap() {
        Observer observer = new Observer<Person>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===", "onSubscribe");
            }

            @Override
            public void onNext(Person o) {
                Log.v("===", "onNext:" + o.getSalary() + " Name:" + o.getName());
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

        getPersonObservable().subscribeOn(Schedulers.io())
                .flatMap(new Function<Person, Observable>() {
                    @Override
                    public Observable apply(Person person) throws Exception {
                        return getEmployeeObservable(person);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //test map opeartor.
    private void testmapoperator() {

        Observable observable = getPersonObservable();

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.v("===", "onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                Log.v("===", "onNext");
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
                .map(new Function<Person, String>() {
                    @Override
                    public String apply(Person o) throws Exception {
                        return o.getName().toLowerCase();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
