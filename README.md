# android im socket.io

## 项目简介

![](https://i.imgur.com/qPCsc0E.png)

> 基于[socket.io](https://github.com/socketio/socket.io "socket.io")实现的Android IM 实时通讯开源项目
>
> 服务端代码见 -> [service demo](https://github.com/cn-ljb/service_im_socket.io "service_im_socket.io")

## 架构

kotlin + [mvp](https://github.com/cn-ljb/mvp-kotlin "mvp-kotlin") + [socket.io](https://github.com/socketio/socket.io "socket.io") + [okhttp](https://github.com/square/okhttp "okhttp") + [retrofit](https://github.com/square/retrofit "retrofit") + [rxjava](https://github.com/ReactiveX/RxJava "rxjava") + [glide](https://github.com/bumptech/glide "glide") + [eventbus](https://github.com/greenrobot/EventBus "eventbus")

### 导入项目

修改 Constant.SOCKET_HOST  为你本地服务器端IP和端口（服务器端代码[service demo](https://github.com/cn-ljb/service_im_socket.io "service_im_socket.io")）

		  const val SOCKET_HOST = "http://172.16.201.33:9090" //loc ip and port

或者直接扫码体验：**（！！！该项目服务器已停，暂不支持扫码，建议clone本地后修改IP端口运行）**

![](https://i.imgur.com/gdOCpC4.png)

### 通讯机制

* 绿色：主动发送消息
* 红色：被动接收消息

![](./img/消息传递流程图.png)


## 截图

![](./img/截图1.png)![](./img/截图2.png)![](./img/截图3.png)

## 版本 

### v1.0.1

* 支持未读消息红点计数
* 支持通知栏提醒



### v1.0.0

* 支持文字、图片、语音消息
* 支持emoji表情
* 支持自定义消息
* 支持离线消息


