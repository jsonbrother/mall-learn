package com.priv.other;

/**
 * @author Json
 * @date 2021/5/28 14:17
 */
class CompareAndSwap {

    private int value;

    // 获取内存值
    synchronized int get() {
        return value;
    }

    // 无论更新成功与否,都返回修改之前的内存值
    private synchronized int compareAndSwap(int expectedValue, int newValue) {
        // 获取旧值
        int oldValue = value;

        if (oldValue == expectedValue) {
            this.value = newValue;
        }

        // 返回修改之前的值
        return oldValue;
    }

    // 判断是否设置成功
    synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}

