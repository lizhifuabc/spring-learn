# 事件
1. google eventbus 事件总线
2. spring application event 事件

## spring

Spring 的事件机制主要包含三部分:
1. ApplicationEvent:代表应用中的某个事件,是事件的载体。可以是任意的对象。
2. ApplicationListener:事件监听器,用来监听 ApplicationEvent 并在事件发生时做出响应。
3. ApplicationEventPublisher:事件发布者,用来发布 ApplicationEvent 事件。

http://localhost:8080/