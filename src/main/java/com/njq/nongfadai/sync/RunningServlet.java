/**
 * Project Name:servlet3-async
 * File Name:LongRunningServlet.java
 * Package Name:com.njq.nongfadai
 * Date:2017年5月6日下午7:31:39
 * Copyright (c) 2017, yangjie_software@163.com All Rights Reserved.
*/

package com.njq.nongfadai.sync;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/running.do")
public class RunningServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Random random = new Random();
    
    static AtomicLong index = new AtomicLong();
    static AtomicLong counter = new AtomicLong();
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Tomcat分配一个新线程: " + index.incrementAndGet());
        long startTime = System.currentTimeMillis();
        longProcessing();
 
        PrintWriter out = response.getWriter();
        long endTime = System.currentTimeMillis();
        
        System.out.println("counter: " + counter.incrementAndGet() + " ,耗时: " + (endTime-startTime)/1000.0 + "s");
    }
 
    private void longProcessing() {
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
}

