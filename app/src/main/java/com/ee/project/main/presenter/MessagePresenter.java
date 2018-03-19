package com.ee.project.main.presenter;

import android.util.Log;

import com.ee.project.base.presenter.BasePresenter;
import com.ee.project.main.bean.MessageBean;
import com.ee.project.main.net.loader.MessageLoader;
import com.ee.project.main.presenter.view.MessageView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 * Created by Administrator on 2018/3/15.
 */

//http://192.168.0.103:8080/getData?action=getMessage&username=admin&userid=60
public class MessagePresenter extends BasePresenter<MessageView>{
    private MessageLoader messageLoader;

    public void getMessage(Integer userid, String username, LifecycleProvider<FragmentEvent> lifecycleProvider){

        messageLoader = new MessageLoader();

        Map<String,Object> params = new HashMap<>();
        params.put("action","getMessage");
        params.put("userid",userid);
        params.put("username",username);

        messageLoader.getMessage(params,lifecycleProvider).subscribe(new Observer<MessageBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MessageBean messageBean) {
                if(messageBean.getCode().equals("200")){
                    mView.messageSuccess(messageBean.getMessageBeans());
                }else{
                    mView.messageFail("获取消息失败.");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i("Error is :",e.getMessage()+"");
                mView.messageError("网络错误");
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
