package com.springcloud.servicea.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 环形队列示例
 * @author: linjinp
 * @create: 2019-07-29 10:13
 **/
public class MyQueue {
    // store elements
    private List<Integer> data;
    // a pointer to indicate the start position
    private int p_start;

    // 构造函数初始化
    public MyQueue() {
        data = new ArrayList<>();
        p_start = 0;
    }
    /** 添加元素到队列，如果成功返回 true */
    public boolean enQueue(int x) {
        data.add(x);
        return true;
    };
    /** 删除队列中的元素，如果成功返回 true */
    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        p_start++;
        return true;
    }
    /** 获取队列前端的元素 */
    public int Front() {
        return data.get(p_start);
    }
    /** 检查队列是否为空 */
    public boolean isEmpty() {
        return p_start >= data.size();
    }

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.enQueue(5);
        q.enQueue(3);
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
    }
}
