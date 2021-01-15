# 规范

最好创建两个module ，其中一个是**autoconfigure module** 一个是**starter module** ，其中 starter module 依赖 autoconfigure module，这样可以将自动配置代码和依赖项管理分离开来。

当然这不是必须的。

