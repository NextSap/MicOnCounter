package com.nextsap.counter;

import com.nextsap.counter.graphics.frames.DefaultFrame;
import com.nextsap.counter.loader.Loader;
import com.nextsap.counter.setup.Initializer;
import com.nextsap.counter.utils.DateUtils;

public class Counter {

    public static void main(String[] args) {
      //  new Initializer();
      //  new DefaultFrame().show();

        Loader.parser(DateUtils.getTime("16:58:00"), DateUtils.getTime("17:01:25"));
    }
}
