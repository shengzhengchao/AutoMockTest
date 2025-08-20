package com.scc.server;

import org.junit.Test;

public class ThreadStudy {
    @Test
    public void cpuNum(){
        Runtime runtime = Runtime.getRuntime();
        int i = runtime.availableProcessors();
        System.out.println("当前cpu数量: " + i + "个");
    }
}
