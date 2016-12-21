package be.makingidea.androidboilerplate.data.local;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import be.makingidea.androidboilerplate.data.model.User;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import timber.log.Timber;

@Singleton
public class DatabaseHelper {

    private final Provider<Realm> mRealmProvider;

    @Inject
    DatabaseHelper(Provider<Realm> realmProvider) {
        mRealmProvider = realmProvider;
    }

    public Observable<User> setUsers(final Collection<User> newUsers) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                Realm realm = null;

                try {
                    realm = mRealmProvider.get();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(newUsers);
                        }
                    });
                } catch (Exception e) {
                    Timber.e(e, "There was an error while adding in Realm.");
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }

    public Observable<List<User>> getUsers() {
        final Realm realm = mRealmProvider.get();
        return realm.where(User.class).findAllAsync().asObservable()
                .filter(new Func1<RealmResults<User>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<User> users) {
                        return users.isLoaded();
                    }
                })
                .map(new Func1<RealmResults<User>, List<User>>() {
                    @Override
                    public List<User> call(RealmResults<User> users) {
                        return realm.copyFromRealm(users);
                    }
                });
    }

    /*public Observable<List<User>> getUsers() {
        final Realm realm = mRealmProvider.get();
        RealmResults<User> realmUsers = realm.where(User.class).findAll();

        realm.beginTransaction();
        List<User> users = realm.copyFromRealm(realmUsers);
        realm.commitTransaction();

        realm.close();

        return Observable.just(users);
    }*/
}
