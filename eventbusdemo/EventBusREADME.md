# EventBus
#主要是高效处理Activity、Fragment、Thread和Service之间的通信

步骤：

1、先添加依赖
    implementation "org.greenrobot:eventbus:3.0.0"
    
2、定义消息类
    MessageEvent.java
    
3、注册和取消订阅事件
    EventBus.getDefault().register(this);
    EventBus.getDefault().unregister(this);
    
4、事件的订阅处理事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MoonEvent(MessageEvent messageEvent) {
        textView.setText(messageEvent.getMessage());
    }
    这个是在主线程中进行UI的更新，还有（POSTING，BACKGROUND，ASYNC）
    
5、发布事件
    EventBus.getDeault().post(new MessageEvent("111111111111111"));
    
6、混淆规则

