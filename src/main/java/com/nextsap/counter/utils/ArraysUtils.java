package com.nextsap.counter.utils;

import java.util.ArrayList;
import java.util.List;

public class ArraysUtils {

    public static List<String> reverse(List<String> list) {
        List<String> output = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--)
            output.add(list.get(i));
        return output;
    }

    public static String salepute(String _char, int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++)
            builder.append(_char);

        return builder.toString();
    }
}
