package com.nextsap.counter;

import com.nextsap.counter.graphics.frames.DefaultFrame;
import com.nextsap.counter.initialize.Initialize;

import java.io.IOException;

public class Counter {

    public static void main(String[] args) throws IOException {
        new Initialize();
        new DefaultFrame().show();

    }
}
