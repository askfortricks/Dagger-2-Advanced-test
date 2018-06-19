package com.hariofspades.dagger2advanced.module;

import android.app.Activity;
import android.content.Context;

import com.hariofspades.dagger2advanced.interfaces.ActivityContext;
import com.hariofspades.dagger2advanced.interfaces.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context activity)
    {
        this.context =activity;
    }

    @Provides
    @ActivityContext
    public Activity provideActivity()
    {
        return (Activity) context;
    }

    @Provides
    @ApplicationContext
    public Context provideContext()
    {
        return context;
    }
}
