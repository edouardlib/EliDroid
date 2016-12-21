package be.makingidea.androidboilerplate.injection.component;

import be.makingidea.androidboilerplate.injection.scope.PerActivity;
import be.makingidea.androidboilerplate.injection.module.ActivityModule;
import be.makingidea.androidboilerplate.ui.main.MainActivity;
import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
