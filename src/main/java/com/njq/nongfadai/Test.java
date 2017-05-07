/**
 * Project Name:servlet3-async
 * File Name:Test.java
 * Package Name:com.njq.nongfadai
 * Date:2017年5月7日上午10:08:04
 * Copyright (c) 2017, yangjie_software@163.com All Rights Reserved.
*/

package com.njq.nongfadai;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: Test
 * Description: TODO.
 * Date: 2017年5月7日 上午10:08:04
 * Copyright (c) 2017, yangjie_software@163.com All Rights Reserved
 * Author: Jerrik
 */
public class Test {
	private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
	private static final int COUNT_BITS = Integer.SIZE - 3;
	private static final int CAPACITY = (1 << COUNT_BITS) - 1;

	// runState is stored in the high-order bits
	private static final int RUNNING = -1 << COUNT_BITS;
	private static final int SHUTDOWN = 0 << COUNT_BITS;
	private static final int STOP = 1 << COUNT_BITS;
	private static final int TIDYING = 2 << COUNT_BITS;
	private static final int TERMINATED = 3 << COUNT_BITS;

	// Packing and unpacking ctl
	private static int runStateOf(int c) {
		return c & ~CAPACITY;
	}

	private static int workerCountOf(int c) {
		return c & CAPACITY;
	}

	private static int ctlOf(int rs, int wc) {
		return rs | wc;
	}

	public static void main(String[] args) throws InterruptedException {
		/*Test test = new Test();
		int c = test.ctl.get();
		System.out.println(workerCountOf(c));
		
		System.out.println("COUNT_BITS: " + COUNT_BITS);
		System.out.println("CAPACITY: " + CAPACITY);
		System.out.println("RUNNING: " + RUNNING);
		System.out.println("SHUTDOWN: " + SHUTDOWN);
		System.out.println("STOP: " + STOP);
		
		System.out.println("========" + runStateOf(test.ctl.get()));*/
		
		int recycleNum = 6;
		final CountDownLatch latch = new CountDownLatch(recycleNum);
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(5));
		System.out.println("线程池corePoolSize 1: " + executor.getCorePoolSize() + " ,queue size: " + executor.getQueue().size() + " ,maxPoolSize: " + executor.getMaximumPoolSize());
		for(int i=0;i<recycleNum;i++){
			executor.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程: " + Thread.currentThread().getName());
					latch.countDown();
					try {
						TimeUnit.SECONDS.sleep(7);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		latch.await();
		System.out.println("线程池corePoolSize 2: " + executor.getCorePoolSize() + " ,queue size: " + executor.getQueue().size() + " ,maxPoolSize: " + executor.getMaximumPoolSize());
		
		executor.setCorePoolSize(10);
		executor.setMaximumPoolSize(20);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("线程池corePoolSize 3: " + executor.getCorePoolSize() + " ,queue size: " + executor.getQueue().size() + " ,maxPoolSize: " + executor.getMaximumPoolSize());
		
		
		for(int i=0;i<recycleNum;i++){
			executor.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("调整线程池大小: " + Thread.currentThread().getName());
					latch.countDown();
					try {
						TimeUnit.SECONDS.sleep(7);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

}
