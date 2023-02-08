package com.springcloud.servicea.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小栈示例
 * @author: linjinp
 * @create: 2019-08-02 16:22
 **/
public class MinStack {

    List<Integer> data;

    // 栈初始化
    public MinStack() {
        data = new ArrayList<>();
    }

    // 将元素 x 推入栈中
    public void push(int x) {
        data.add(x);
    }

    //  删除栈顶的元素
    public void pop() {
        if (data.size() > 0) {
            data.remove(data.size() - 1);
        }
    }

    // 获取栈顶元素。
    public int top() {
        if (data.size() > 0) {
            return data.get(data.size() - 1);
        }
        return 0;
    }

    // 检索栈中的最小元素
    public int getMin() {
        int temp = 0;
        for (int i = 0; i < data.size(); i ++) {
            if (i == 0) {
                temp = data.get(0);
            }
            if (data.get(i) < temp) {
                temp = data.get(i);
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        MinStack m = new MinStack();
        m.push(-2);
        m.push(0);
        m.push(-3);
        m.getMin();
        m.pop();
        m.top();
        m.getMin();
    }
}
