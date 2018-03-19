package com.ee.project.user.net.loader;

import com.ee.project.base.net.http.RetrofitServiceManager;
import com.ee.project.base.net.loader.ObjectLoader;
import com.ee.project.user.net.service.ILoginService;
import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import com.ee.project.user.bean.UserBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;


/**
 * Created by rnd on 2018/3/15.
 *
 */

public class LoginLoader extends ObjectLoader{

    private ILoginService iLoginService;

    public LoginLoader(){
        iLoginService = RetrofitServiceManager.getInstance().create(ILoginService.class);
    }

    /**
     * 登录
     * @param params
     * @return userBean
     */
    public Observable<UserBean> getLogin(Map<String,Object> params,LifecycleProvider<ActivityEvent> lifecycleProvider){
        return observeat(iLoginService.getLogin(params),lifecycleProvider).map(new Function<UserBean, UserBean>() {
            @Override
            public UserBean apply(UserBean userBean) throws Exception {
                return userBean;
            }
        });
    }




}
