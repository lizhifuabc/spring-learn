package com.doc.demo.gc;

/**
 * ReferenceCountingGC
 *
 * @author lizhifu
 * @since 2023/9/7
 */
public class ReferenceCountingGC {
    public Object instance = null;

    public static void main(String[] args) {
        ReferenceCountingGC objectA = new ReferenceCountingGC();
        ReferenceCountingGC objectB = new ReferenceCountingGC();
        objectA.instance = objectB;
        objectB.instance = objectA;
    }
}
