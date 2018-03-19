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
2. rxlifecycle Github地址 最新的版本在这里找 https://github.com/trello/RxLifecycle。<br/>
3. rxjava Github地址 最新版本 https://github.com/ReactiveX/RxJava。<br/>
4. retrofit GitHub 版本 https://github.com/square/retrofit。<br/>
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

