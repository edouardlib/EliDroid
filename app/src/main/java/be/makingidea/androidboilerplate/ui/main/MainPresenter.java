package be.makingidea.androidboilerplate.ui.main;

import java.util.List;

import javax.inject.Inject;

import be.makingidea.androidboilerplate.data.DataManager;
import be.makingidea.androidboilerplate.data.model.User;
import be.makingidea.androidboilerplate.injection.ConfigPersistent;
import be.makingidea.androidboilerplate.ui.base.BasePresenter;
import be.makingidea.androidboilerplate.util.RxUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadUsers() {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the users.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<User> users) {
                        if (users.isEmpty()) {
                            getMvpView().showUsersEmpty();
                        } else {
                            getMvpView().showUsers(users);
                        }
                    }
                });
    }

}
