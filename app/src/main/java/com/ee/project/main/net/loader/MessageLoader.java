package com.ee.project.main.net.loader;

import com.ee.project.base.net.http.RetrofitServiceManager;
import com.ee.project.base.net.loader.ObjectLoader;
import com.ee.project.main.bean.MessageBean;
import com.ee.project.main.net.service.MessageService;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by rnd on 2018/3/16.
 *
 */

public class MessageLoader extends ObjectLoader{
    private MessageService messageService;

    public MessageLoader(){
        messageService = RetrofitServiceManager.getInstance().create(MessageService.class);
    }

    public Observable<MessageBean> getMessage(Map<String,Object> params,LifecycleProvider<FragmentEvent> lifecycleProvider){
        return observefg(messageService.getMessage(params),lifecycleProvider).map(new Function<MessageBean, MessageBean>() {
            @Override
            public MessageBean apply(MessageBean messageBean) throws Exception {
                return messageBean;
            }
        });
    }
}
