package com.ee.project.user.net.service;

import com.ee.project.user.bean.UserBean;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by rnd on 2018/3/15.
 *
 */

public interface ILoginService {
    /**
     * 登录
     * @param params
     * @return
     */
    @POST("getData")
    Observable<UserBean> getLogin(@QueryMap Map<String, Object> params);
}
