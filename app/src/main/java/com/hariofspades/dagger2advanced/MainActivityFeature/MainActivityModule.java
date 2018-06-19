package com.hariofspades.dagger2advanced.MainActivityFeature;

import com.hariofspades.dagger2advanced.MainActivityNew;
import com.hariofspades.dagger2advanced.adapter.RandomUserAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hari on 20/12/17.
 */
@Module
public class MainActivityModule {

    private final MainActivityNew mainActivity;

    public MainActivityModule(MainActivityNew mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public RandomUserAdapter randomUserAdapter(){
        return new RandomUserAdapter(mainActivity);
    }
}
