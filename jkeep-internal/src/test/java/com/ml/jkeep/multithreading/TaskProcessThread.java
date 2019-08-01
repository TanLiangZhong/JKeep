package com.ml.jkeep.multithreading;

import java.text.NumberFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2019/8/1-10:40
 * @author mengh
 * Description:
 */
@Slf4j
public class TaskProcessThread implements Runnable {

	/**
	 * 自定义线程名
	 */
	private String threadName;

	/**
	 * 默认时间 1秒
	 */
	private int taskProcessTime = 1000 ;

	private CountDownLatch latch;

	private AtomicInteger atomicSuccessItems;

	/**
	 * @param taskProcessTime - 处理任务的时间 (具体业务可以传入处理的对象或Bean)
	 */
	public TaskProcessThread(CountDownLatch latch,AtomicInteger atomicSuccessItems, String threadName,int taskProcessTime){
		this.latch = latch;
		this.atomicSuccessItems = atomicSuccessItems;
		this.threadName = threadName;
		this.taskProcessTime = taskProcessTime;
	}

	@Override
	public void run() {

		NumberFormat numberFormat = NumberFormat.getInstance();

		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);

		for( int i = 1 ; i <= taskProcessTime / 1000 ; i ++ ){
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			String taskProcess = numberFormat.format((float) i / ( (float) taskProcessTime / 1000 ) * 100) + "%";
			log.info( String.format("------------{%s} 正在执行，执行进度 : [%s]------------",threadName, taskProcess) );
		}
		log.info(  String.format("》》》》》{%s} 任务已结束《《《《《",threadName ) );

		atomicSuccessItems.incrementAndGet();

		latch.countDown();
	}


}
