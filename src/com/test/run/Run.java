package com.test.run;

import java.util.UUID;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Run {

	public static void main(String[] args) {
		DelayQueue<DelayTask> dbq = new DelayQueue<DelayTask>();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						DelayTask dt = new DelayTask(UUID.randomUUID().toString(), 3, TimeUnit.SECONDS);
						System.out.println("put task...");
						dt.printContent();
						dbq.put(dt);
						System.out.println("queue size:" + dbq.size());
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}, "Producer Thread").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("currentTime: " + System.currentTimeMillis());
						DelayTask task = dbq.poll();
						if (task != null) {
							System.out.println("take task...");
							task.printContent();
						}
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}, "Consumer Thread -1").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("currentTime: " + System.currentTimeMillis());
						DelayTask task = dbq.poll();
						if (task != null) {
							System.out.println("take task...");
							task.printContent();
						}
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		}, "Consumer Thread -2").start();
	}

}
