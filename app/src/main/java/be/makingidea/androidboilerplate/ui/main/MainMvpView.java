package be.makingidea.androidboilerplate.ui.main;

import java.util.List;

import be.makingidea.androidboilerplate.data.model.User;
import be.makingidea.androidboilerplate.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showUsers(List<User> users);

    void showUsersEmpty();

    void showError();

}
