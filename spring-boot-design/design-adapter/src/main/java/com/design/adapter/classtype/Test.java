package com.design.adapter.classtype;

/**
 * 从代码中我们可以看到，其实适配器做的主要工作就是为了让目标角色的API可以调用到源角色的API，
 * 适配器在中间做的是一个类似的中转作用，并且不影响源角色和目标角色原有的功能和逻辑。
 * 总结：Adapter类，通过继承源类，实现目标类接口，完成源类->目标角色的适配
 * @author lizhifu
 * @date 2020/12/28
 */
public class Test {
    public static void main(String[] args) {
        DC5V dc5V = new Adapter();
        int dc5 = dc5V.dc5v();
        System.out.println("转换后的电压为：" + dc5 + " 伏...");
    }
}
