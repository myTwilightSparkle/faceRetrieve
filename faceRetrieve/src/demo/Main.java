package demo;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import biz.CustomerBiz;
import entity.Person;

public class Main {

	static CustomerBiz cb = new CustomerBiz();

	public static void main(String[] args) {
		while (true) {
			File dir = new File("assert");
			File[] images = dir.listFiles();
			Date comingTime = new Date();
			if (comingTime.getMinutes() < 30) {
				comingTime.setHours(comingTime.getHours() - 1);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
			try {
				String timeStr = sdf.format(comingTime);
				comingTime = sdf.parse(timeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int code = cb.findComingPerson(images);
			if (code == 3) {
				continue;
			}
			cb.addComingRecord(comingTime);
			try {
				Thread.sleep(1000 * 3600);// 一小时执行一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
