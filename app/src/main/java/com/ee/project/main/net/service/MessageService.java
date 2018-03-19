package com.ee.project.main.net.service;

import com.ee.project.main.bean.MessageBean;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by rnd on 2018/3/16.
 *
 */

public interface MessageService {
    /**
     * 登录
     * @param params
     * @return
     */
    @POST("getData")
    Observable<MessageBean> getMessage(@QueryMap Map<String, Object> params);
}
