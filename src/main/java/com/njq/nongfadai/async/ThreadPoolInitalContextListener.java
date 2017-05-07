/**
 * Project Name:servlet3-async
 * File Name:ThreadPoolInitalContextListener.java
 * Package Name:com.njq.nongfadai.async
 * Date:2017年5月6日下午9:02:04
 * Copyright (c) 2017, yangjie_software@163.com All Rights Reserved.
*/

package com.njq.nongfadai.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName: ThreadPoolInitalContextListener
 * Description: TODO.
 * Date: 2017年5月6日 下午9:02:04
 * Copyright (c) 2017, yangjie_software@163.com All Rights Reserved
 * Author: Jerrik
 */
@WebListener
public class ThreadPoolInitalContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("ThreadPoolInitalContextListener init()");
		ThreadPoolExecutor executor = new ThreadPoolExecutor(128, 1024, 10000L, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(800));
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			
			@Override
			public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
				System.out.println("执行失败任务...");
				//new Thread(task,"失败任务线程池").start();
			}
		});
		servletContextEvent.getServletContext().setAttribute("executor", executor);

	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("ThreadPoolInitalContextListener destroy()");
		ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent.getServletContext().getAttribute("executor");
		executor.shutdown();
	}

}
