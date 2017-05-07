/**
 * Project Name:servlet3-async
 * File Name:AppAyncListener.java
 * Package Name:com.njq.nongfadai.async
 * Date:2017年5月6日下午9:00:06
 * Copyright (c) 2017, yangjie_software@163.com All Rights Reserved.
*/

package com.njq.nongfadai.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;

/**
 * ClassName: RunningAsyncListener
 * Description: TODO.
 * Date: 2017年5月6日 下午9:00:06
 * Copyright (c) 2017, yangjie_software@163.com All Rights Reserved
 * Author: Jerrik
 */
@WebListener
public class RunningAsyncListener implements AsyncListener {
	
	static AtomicLong counter;

	public RunningAsyncListener(AtomicLong counter2) {
		this.counter = counter2;
	}
	
	public RunningAsyncListener() {
		super();
		
		
	}



	@Override
	public void onComplete(AsyncEvent asyncEvent) throws IOException {
		System.out.println("RunningAsyncListener onComplete,执行完成个数: " + counter.incrementAndGet());
	}

	@Override
	public void onError(AsyncEvent asyncEvent) throws IOException {
		System.out.println("RunningAsyncListener onError");
	}

	@Override
	public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
		System.out.println("RunningAsyncListener onStartAsync");
	}

	@Override
	public void onTimeout(AsyncEvent asyncEvent) throws IOException {
		System.out.println("RunningAsyncListener onTimeout");
	}

}
