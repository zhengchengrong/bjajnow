package com.threehmis.bjaj.injector.components;

import com.threehmis.bjaj.injector.modules.HomeModule;
import com.threehmis.bjaj.module.home.HomeActivity;

import dagger.Component;


/**
 * Created by zhengchengrong on 2017/9/4.
 */
@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity activity);

}
