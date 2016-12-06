package com.graffitab.graffitabsdk;

import com.graffitabsdk.config.dagger.components.UserComponent;
import com.graffitabsdk.config.dagger.modules.AppModule;
import com.graffitabsdk.config.dagger.modules.NetworkModule;
import com.graffitabsdk.config.dagger.modules.UserModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by davidfernandez on 06/12/2016.
 */
@Singleton
@Component(modules = {NetworkModule.class, AppModule.class, UserModule.class})
public interface TestComponent extends UserComponent {
	void inject(UserServiceTest test);
}
