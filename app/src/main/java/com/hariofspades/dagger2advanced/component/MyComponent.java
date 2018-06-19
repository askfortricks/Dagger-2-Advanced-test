package com.hariofspades.dagger2advanced.component;

import com.hariofspades.dagger2advanced.interfaces.RandomUserApplicationScope;
import com.hariofspades.dagger2advanced.interfaces.RandomUsersApi;
import com.hariofspades.dagger2advanced.module.RandomUsersModule;

import dagger.Component;

@RandomUserApplicationScope
@Component(modules = {RandomUsersModule.class})
public interface MyComponent {

    RandomUsersApi getRandomUserService();
}


