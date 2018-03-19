# RRRMvp-master
## MVP+Retrofit2+Rxjava2+RxLifecycle2.0萌新文章<br/>

### Rxjava2为什么会造成内存泄漏？<br/>
一句话描述：使用RxJava发布一个 Subscribe（订阅）后，当页面被finish()，此时订阅逻辑还未完成，如果没有及时取消订阅，就会导致Activity/Fragment无法被回收，从而引发内存泄漏。
<br/>
### 当出现了内存泄漏怎么解决？<br/>
1. 使用trello公司提供的RxLifecycle来解决rxjava所造成的内存泄漏。<br/>
2. Rxlifecycle是通过绑定生命周期的方式，解决内存泄漏的问题。<br/>
### 开始使用<br/>
1. 首先要导入我们所需要的库 所使用的androidstudio版本是 3.0。<br/>
2. rxlifecycle Github地址 最新的版本在这里找 https://github.com/trello/RxLifecycle <br/>
3. rxjava Github地址 最新版本 https://github.com/ReactiveX/RxJava <br/>
4. retrofit GitHub 版本 https://github.com/square/retrofit <br/>
5. 导入库的结构如下所示：使用的rxjava版本与rxlifecycle版本必须要对应 1.0 配1.0的，2.0配2.0 
不能把1.0的版本配置到2.0里面去。<br/>

app->build<br/>
```Java
dependencies {
    //retrofit2+rxjava2+rxlifecycle 网络请求框架
    compile 'io.reactivex.rxjava2:rxjava:2.1.3'
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.3.0'
    compile 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
    // If you want to bind to Android-specific lifecycles
    compile 'com.trello.rxlifecycle2:rxlifecycle-android:2.2.1'
    // If you want pre-written Activities and Fragments you can subclass as providers
    compile 'com.trello.rxlifecycle2:rxlifecycle-components:2.2.1'

    //其它的
    compile 'com.squareup.okhttp3:okhttp:3.9.0'
    //metral design设计
    compile 'com.android.support:design:26.1.0'
    //底部导航
    compile 'com.ashokvarma.android:bottom-navigation-bar:2.0.4'
    //照片处理
    compile 'de.hdodenhof:circleimageview:2.1.0'
    //图片轮滑效果
    compile 'com.youth.banner:banner:1.4.10'  //最新版本
    //加载图片glide
    compile 'com.github.bumptech.glide:glide:3.7.0'
    //下拉刷新/上啦加载控件
    compile 'com.github.Aspsine:SwipeToLoadLayout:1.0.4'
    //侧滑
    compile 'com.github.mcxtzhang:SwipeDelMenuLayout:V1.3.0'
    //没有消息的时候显示
    compile 'com.github.Kennyc1012:MultiStateView:1.3.2'
}  

```
还需要添加以下的配置<br/>
项目->build
```Java
allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```
着手网络层 <br/>
首先要配置Reftrofit ：
```Java
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
                //创建Rxjava2工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //创建Gson工厂
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
```
配置Rxjava2 公共装载 这里面compose就是用来管理
```Java
public class ObjectLoader {
    protected <T> Observable<T> observeat(Observable<T> observable,LifecycleProvider<ActivityEvent> lifecycleProvider){
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                //绑定生命周期
                .compose(lifecycleProvider.<T>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()); //指定在主线程中
    }

    protected <T> Observable<T> observefg(Observable<T> observable, LifecycleProvider<FragmentEvent> lifecycleProvider){
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .compose(lifecycleProvider.<T>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()); //指定在主线程中
    }
}

```

上面配置完成后方可以写代码MVP代码 <br/>
MVP的写法有很多种，我也是参考泓洋大神的文章来写的，可能稍微有些改动。 <br/>
谷歌官方的MVP感觉有点啰嗦所以取容易自己理解简单的方式来写。<br/>
由于很多数据都是共用的 所以必须要建立base类<br/>
Activity继承于RxAppCompatActivity<br/>
```Java
public class BaseActivity extends RxAppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
```
BaseMVP类
```Java
    public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView,
        View.OnClickListener{

    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init(){
        setContentView(getLayoutResID());
        initView();
        mPresenter = PMUtil.getT(this,0);

        if(this instanceof BaseView){
            mPresenter.setMV(this);
        }
    }

    protected abstract int getLayoutResID();

    protected void initView(){

    }

    @Override
    public void onClick(View v) {

    }
}
```
建立BasePresenter
```Java
public class BasePresenter<V> {
    protected V mView;

    public void setMV(V v){
        mView = v;
    }

}
```
建立BaseView层
```Java
public interface BaseView {
    void showLoading();
    void hideLoading();

}
```
以登录为Demo<br/>

LoginView层
```Java
public interface LoginView extends BaseView{
    void loginSuccess(UserBean.UserBeanBean userBeanBean);
    void loginError(String message);

    String getUserName();
    String getPassword();

}
```
LoginPresenter
```Java
public class LoginPresenter extends BasePresenter<LoginView>{
    private LoginLoader loginLoader;

    public void login(LifecycleProvider<ActivityEvent> lifecycleProvider){
        loginLoader = new LoginLoader();
        Map<String,Object> params = new HashMap<>();
        params.put("action","getUser");
        params.put("username",mView.getUserName());
        params.put("password",mView.getPassword());
        //params.put("deviceinfo",deviceinfo);
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
}
```

LoginActivity
```Java
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginView{
    private EditText mUser;
    private EditText mPwd;
    private Button mLogin;
    //private LinearLayout mLbg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mUser = findViewById(R.id.mUser);
        mPwd = findViewById(R.id.mPwd);
        mLogin = findViewById(R.id.mLogin);
        //mLbg = findViewById(R.id.mLbg);
        //尽量暗一点
        //mLbg.getBackground().setAlpha(50);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mLogin:
                mPresenter.login(this);
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess(UserBean.UserBeanBean userBeanBean) {
        if(userBeanBean!=null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void loginError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public String getUserName() {
        return mUser.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPwd.getText().toString().trim();
    }
}
```

以上代码只是一个简单的流程,具体过程看项目里面的代码。<br/>
谢谢你的Star...





