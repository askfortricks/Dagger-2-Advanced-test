package com.hariofspades.dagger2advanced.application;

import android.app.Activity;
import android.app.Application;

import com.hariofspades.dagger2advanced.component.DaggerMyComponent;
import com.hariofspades.dagger2advanced.component.MyComponent;
import com.hariofspades.dagger2advanced.module.ContextModule;

import timber.log.Timber;

public class MyApplication extends Application {

    //component
    MyComponent myComponent;

    public static MyApplication get(Activity activity)
    {
        return (MyApplication)activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        myComponent= DaggerMyComponent.builder().
                contextModule(new ContextModule(this))
                .build();
    }

    public MyComponent getMyComponent() {
        return myComponent;
    }



}
