package com.priv.other;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Json
 * @date 2021/5/27 10:55
 */
public class LambdaDemo {

    @Test
    public void demo() {
        String[] strs = {"F", "A", "B", "C", "F", "D", "F", "C", "A", "C", "D", "F"};
        int k = 3;
        long t0 = System.nanoTime();

        List<String> list = algorithm(strs, k);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));

        list.forEach(System.out::print);
    }

    /**
     * 统计字符串出现的次数 并打印前？位
     * @param strs 数组
     * @param k    前几位
     * @return 集合
     */
    private List<String> algorithm(String[] strs, int k) {
        return Arrays.stream(strs)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((s1, s2) -> {
                    if (s1.getValue().equals(s2.getValue())) {
                        return s1.getKey().compareTo(s2.getKey());
                    } else {
                        return s2.getValue().compareTo(s1.getValue());
                    }
                })
                .map(Map.Entry::getKey)
                .limit(k)
                .collect(Collectors.toList());
    }
}
