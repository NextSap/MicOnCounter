package com.nextsap.counter;

import com.nextsap.counter.graphics.frames.DefaultFrame;
import com.nextsap.counter.setup.Initializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The main class
 */
public class Counter {

    public static void main(String[] args) {
        new Initializer();
        new DefaultFrame().show();
    }
}
