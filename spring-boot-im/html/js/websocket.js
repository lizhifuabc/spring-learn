var _websocket ={
    option: {
        websocket: window.WebSocket || window.MozWebSocket,
        websockets: '',
        _websocket_des: '',
        url: ''
    },
    init: function(url,send,message){
        if(this.option.websocket){
            window.onbeforeunload = function(){
               _websocket.option._websocket_des.close();
            }
            this.option.url=url;
            if(send &&  Object.prototype.toString.call(send)=="[object Function]") this.option.send=send;
            if(message &&  Object.prototype.toString.call(message)=="[object Function]") this.option.message=message;
            if(this.option.message && this.option.send){
                return this.main(url,send,message);
            }else if(this.option.message){
                return this.main(url,false,message);
            }else if(this.option.send){
                return this.main(url,send,false);
            }else{
                return this.main(url);
            }

        }else{
            return '当前设备不支持'
        }
    },
    doopen:function(){
        console.log('连接成功')
    },
    doclose:function(){
        console.log('连接失败');
        _websocket.option._websocket_des.close();
    },
    doError:function(){
        console.log('连接出错');
    },
    doMessage:function(message){
        console.log('收到message='+message);
    },
    doSend:function(message){
		if(_websocket.option.websockets.readyState == 1) {
			_websocket.option.websockets.send(JSON.stringify(message));
		}else {
			_websocket.Reconnect();
            console.log("您已经掉线，无法与服务器通信!");
        }
    },
    Reconnect:function(){
        return _websocket.main(_websocket.option.url,_websocket.option.send,_websocket.option.message);
    },
    main:function(wcUrl,send,message){
        this.option.websockets = new this.option.websocket(encodeURI(wcUrl));
        this.option.websockets.onopen = this.doopen;//打开
        this.option.websockets.onerror = this.doError;//出错
        this.option.websockets.onclose = this.doclose;//关闭
        this.option.websockets.onmessage = (message || this.doMessage);//接收
        this.option.websockets.onSend = (send || this.doSend);//发送
        this.option.websockets.Reconnect = this.Reconnect;//重新连接
        this.option._websocket_des = this.option.websockets;
        return this.option.websockets;
    }
}