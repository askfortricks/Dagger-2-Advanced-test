package com.hariofspades.dagger2advanced.MainActivityFeature;

import com.hariofspades.dagger2advanced.MainActivityNew;
import com.hariofspades.dagger2advanced.component.MyComponent;

import dagger.Component;

/**
 * Created by Hari on 20/12/17.
 */
@Component(modules = MainActivityModule.class, dependencies = MyComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivityNew mainActivity);

}
