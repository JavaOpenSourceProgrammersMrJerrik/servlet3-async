/**
 * Project Name:servlet3-async
 * File Name:AyncProcessor.java
 * Package Name:com.njq.nongfadai.async
 * Date:2017年5月6日下午10:36:55
 * Copyright (c) 2017, yangjie_software@163.com All Rights Reserved.
*/

package com.njq.nongfadai.async;

import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;

/**
 * ClassName: AyncProcessor
 * Description: TODO.
 * Date: 2017年5月6日 下午10:36:55
 * Copyright (c) 2017, yangjie_software@163.com All Rights Reserved
 * Author: Jerrik
 */
public class AyncProcessor implements Runnable {
	private AsyncContext asyncContext;
	private int seconds;
	
	public AyncProcessor(AsyncContext asyncContext,int seconds) {
		this.asyncContext = asyncContext;
		this.seconds = seconds;
	}

	@Override
	public void run() {
		longProcessing();
		asyncContext.complete();
	}
	
	private void longProcessing() {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

