package com.test.run;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTask implements Delayed {

	//����ʱ��
	private long createTime;
	//��ʼ��ʱʱ��
	private long delayTime;
	//��ʼʱ��
	private long startTime;
	private String data;

	public DelayTask(String data, long delayTime, TimeUnit unit) {
		this.createTime = System.currentTimeMillis();
		this.delayTime = TimeUnit.MILLISECONDS.convert(delayTime, unit);
		this.startTime = this.createTime + this.delayTime;
		this.data = data;
	}

	@Override
	public int compareTo(Delayed arg0) {
		if (this.startTime > ((DelayTask) arg0).startTime) {
			return 1;
		} else if (this.startTime < ((DelayTask) arg0).startTime) {
			return -1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit arg0) {
		System.out.println("call getDelay...");
		System.out.println("Now:" + System.currentTimeMillis() + "  startTime:" + startTime + "  delay:"
				+ (startTime - System.currentTimeMillis()));
		return arg0.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	public void printContent() {
		System.out.println("This Object, CreateTime:" + createTime + "  Delay:" + delayTime + "  startTime:" + startTime
				+ "  data:" + data);

	}
}
