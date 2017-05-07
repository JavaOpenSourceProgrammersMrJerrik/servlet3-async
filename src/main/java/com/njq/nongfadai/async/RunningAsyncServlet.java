/**
 * Project Name:servlet3-async
 * File Name:LongRunningServlet.java
 * Package Name:com.njq.nongfadai
 * Date:2017年5月6日下午7:31:39
 * Copyright (c) 2017, yangjie_software@163.com All Rights Reserved.
*/

package com.njq.nongfadai.async;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/runningAync.do" }, asyncSupported = true)
public class RunningAsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Random random = new Random();

	static AtomicLong index = new AtomicLong();

	static AtomicLong counter = new AtomicLong();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RunningAsyncServlet Tomcat分配一个新线程: " + index.incrementAndGet());
		long startTime = System.currentTimeMillis();

		// servlet3.0异步处理
		AsyncContext asyncContext = request.startAsync();
		asyncContext.addListener(new RunningAsyncListener(counter));
		asyncContext.setTimeout(10000);

		ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
		executor.execute(new AyncProcessor(asyncContext, 7));//添加任务至线程池 指定处理时间

		long endTime = System.currentTimeMillis();
	}

}
