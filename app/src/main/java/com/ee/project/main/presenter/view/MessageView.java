package com.ee.project.main.presenter.view;

import com.ee.project.base.presenter.view.BaseView;
import com.ee.project.main.bean.MessageBean;
import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 *
 */
public interface MessageView extends BaseView{
    void messageSuccess(List<MessageBean.MessageBeansBean> messageBeansBeans);
    void messageError(String message);
    void messageFail(String message);
}
