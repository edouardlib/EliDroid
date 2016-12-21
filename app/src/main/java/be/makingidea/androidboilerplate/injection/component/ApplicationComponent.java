package be.makingidea.androidboilerplate.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import be.makingidea.androidboilerplate.data.DataManager;
import be.makingidea.androidboilerplate.data.SyncService;
import be.makingidea.androidboilerplate.data.local.DatabaseHelper;
import be.makingidea.androidboilerplate.data.local.PreferencesHelper;
import be.makingidea.androidboilerplate.data.remote.RestService;
import be.makingidea.androidboilerplate.injection.ApplicationContext;
import be.makingidea.androidboilerplate.injection.module.ApplicationModule;
import be.makingidea.androidboilerplate.injection.module.RestModule;
import be.makingidea.androidboilerplate.util.RxEventBus;
import dagger.Component;
import io.realm.Realm;

@Singleton
@Component(modules = {ApplicationModule.class, RestModule.class})
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RestService restService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    Realm realm();
    DataManager dataManager();
    RxEventBus eventBus();
}
