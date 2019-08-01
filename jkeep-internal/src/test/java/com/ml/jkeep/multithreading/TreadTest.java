package com.ml.jkeep.multithreading;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Date: 2019/8/1-11:06
 * @author meng
 * Description: 多线程测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TreadTest {

	@Autowired
	@Qualifier("jkeepThreadPoolTaskExecutor")
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Test
	public void taskProcessTest(){

		/**
		 * AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减
		 * AtomicInteger提供原子操作来进行Integer的使用，因此十分适合高并发情况下的使用
		 * https://www.cnblogs.com/zhaoyan001/p/8885360.html
 		 */
		AtomicInteger atomicSuccessItems = new AtomicInteger(0);

		Map<String,Integer> taskMap = new HashMap<>();
		taskMap.put("Thread-Tan",3000);
		taskMap.put("Thread-Pan",5000);
		taskMap.put("Thread-Meng",7000);
		taskMap.put("Thread-Shuai",10000);

		/**
		 * CountDownLatch是一个计数器闭锁，通过它可以完成类似于阻塞当前线程的功能，即：一个线程或多个线程一直等待，直到其他线程执行的操作完成。
		 * https://www.jianshu.com/p/bb5105303d85
		 */
		CountDownLatch latch = new CountDownLatch(taskMap.size());


		/**
		 * 以线程处理时间来体现具体业务任务处理时间的不同。
		 * 更能体现出某些业务场景需要并发操作，同时所有业务全部处理结束后才能给予反馈 (如果不需要反馈，则不需要线程锁)
		 * 业务场景比如 :  一个10000数据的excel需要导入数据库 . 但同时需要导入成功后反馈给用户 . 这种情况下不仅需要多线程并发，还需要线程锁(计数器闭锁)来统计所有任务的结束状态
		 */
		for ( String key : taskMap.keySet() ){
			threadPoolTaskExecutor.execute( new TaskProcessThread(latch,atomicSuccessItems,key,taskMap.get(key)));
		}

		try {
			// 等待所有线程执行完
			latch.await();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		log.info("线程池所有任务已结束，可以响应结果给用户！");

	}




}
