package com.ee.project.base.net.http;


import com.ee.project.utils.util.UrlConfig;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 配置Retrofit拦截
 * Created by rnd on 2018/1/18.
 *
 */
public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 20;//超时时间 20s
    private static final int DEFAULT_READ_TIME_OUT = 30;
    private Retrofit mRetrofit;

    public RetrofitServiceManager(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);

        //添加公共拦截参数
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("palthform","adnroid")
                .addHeaderParams("userToken","12345678912")
                .addHeaderParams("userId","123")
                .build();

        builder.addInterceptor(commonInterceptor);


        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(UrlConfig.BASEURL)
                .build();

    }



    //单列模式
    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }

    /**
     * 获取RetrofitServiceManager
     * @return
     */
    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T  create(Class<T> service){
        return mRetrofit.create(service);
    }
}
