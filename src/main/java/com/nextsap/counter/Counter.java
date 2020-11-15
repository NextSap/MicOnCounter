package com.nextsap.counter;

import com.nextsap.counter.graphics.frames.DefaultFrame;
import com.nextsap.counter.setup.Initializer;

/**
 * The main class
 */
public class Counter {

    public static void main(String[] args) {
        new Initializer();
        new DefaultFrame().show();
    }
}
