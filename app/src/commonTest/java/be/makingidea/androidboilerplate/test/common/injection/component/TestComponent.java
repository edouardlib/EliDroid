package be.makingidea.androidboilerplate.test.common.injection.component;

import javax.inject.Singleton;

import be.makingidea.androidboilerplate.injection.component.ApplicationComponent;
import be.makingidea.androidboilerplate.test.common.injection.module.ApplicationTestModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
