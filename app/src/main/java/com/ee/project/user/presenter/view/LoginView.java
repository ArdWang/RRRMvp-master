package com.ee.project.user.presenter.view;

import com.ee.project.base.presenter.view.BaseView;
import com.ee.project.user.bean.UserBean;

/**
 * Created by rnd on 2018/3/15.
 *
 */

public interface LoginView extends BaseView{
    void loginSuccess(UserBean.UserBeanBean userBeanBean);
    void loginError(String message);

    String getUserName();
    String getPassword();

}
