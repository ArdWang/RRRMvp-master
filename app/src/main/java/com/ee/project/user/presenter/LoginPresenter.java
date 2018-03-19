package com.ee.project.user.presenter;




import android.util.Log;

import com.ee.project.base.presenter.BasePresenter;
import com.ee.project.user.bean.UserBean;
import com.ee.project.user.net.loader.LoginLoader;
import com.ee.project.user.presenter.view.LoginView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by rnd on 2018/3/15.
 *
 */

public class LoginPresenter extends BasePresenter<LoginView>{
    private LoginLoader loginLoader;

    public void login(LifecycleProvider<ActivityEvent> lifecycleProvider){
        loginLoader = new LoginLoader();

        Map<String,Object> params = new HashMap<>();
        params.put("action","getUser");
        params.put("username",mView.getUserName());
        params.put("password",mView.getPassword());
        //params.put("deviceinfo",deviceinfo);
        //推送 默认为 123321
        //params.put("pushid","123321");

        loginLoader.getLogin(params,lifecycleProvider).subscribe(new Observer<UserBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserBean userBean) {
                if(userBean.getCode().equals("200")){
                    mView.loginSuccess(userBean.getUserBean());
                }else{
                    mView.loginError("帐号或者密码错误！");
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.loginError("出现了错误");
            }

            @Override
            public void onComplete() {

            }
        });




    }


    /*@Override
    public void login(String username, String password,LifecycleProvider<ActivityEvent> lifecycleProvider) {
        String user = mModel.login(username,password,lifecycleProvider);
        mView.loginSuccess(user);
        mView.onError(user);
    }*/
}
