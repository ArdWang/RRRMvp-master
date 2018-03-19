# RRRMvp-master
##MVP+Retrofit2+Rxjava2+RxLifecycle2.0萌新文章

###Rxjava2为什么会造成内存泄漏？
一句话描述：使用RxJava发布一个 Subscribe（订阅）后，当页面被finish()，此时订阅逻辑还未完成，如果没有及时取消订阅，就会导致Activity/Fragment无法被回收，从而引发内存泄漏。
<br/>
###当出现了内存泄漏怎么解决？
1. 使用trello公司提供的RxLifecycle来解决rxjava所造成的内存泄漏。<br/>
2. Rxlifecycle是通过绑定生命周期的方式，解决内存泄漏的问题。<br/>
###开始使用

