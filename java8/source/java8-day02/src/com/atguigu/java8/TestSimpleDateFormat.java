package com.atguigu.java8;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestSimpleDateFormat {
	
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(new Date()));
//		LocalDate localDate = LocalDate.parse("yyyy-MM-dd");
//		System.out.println(localDate);
	}

	@Test
	public void unSafesdf() throws ExecutionException, InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		System.out.println("right date:"+sdf.format(new Date()));
		//Callable<Date> task = () -> sdf.parse("20161121");
		Callable<Date> task = () -> sdf.parse(sdf.format(new Date()));

		new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return null;
			}
		};

		ExecutorService pool = Executors.newFixedThreadPool(10);

		List<Future<Date>> results = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}

		for (Future<Date> future : results) {
			System.out.println(sdf.format(future.get()));
		}

		pool.shutdown();

	}

	@Test
	public  void ThreadLocalsdf() throws ExecutionException, InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//解决多线程安全问题
		Callable<Date> task = () -> DateFormatThreadLocal.convert2(sdf.format(new Date()));

		ExecutorService pool = Executors.newFixedThreadPool(10);

		List<Future<Date>> results = new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			results.add(pool.submit(task));
		}

		for (Future<Date> future : results) {
			System.out.println(sdf.format(future.get()));
		}

		pool.shutdown();
	}


	@Test
	public void safeDate() throws InterruptedException, ExecutionException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		Callable<LocalDateTime> task = () -> {
//			LocalDate ld = LocalDate.parse("20161121", dtf);
//			return ld;
			return LocalDateTime.now();
		};

		ExecutorService pool = Executors.newFixedThreadPool(10);

		List<Future<LocalDateTime>> results = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			results.add(pool.submit(task));
		}

		for (Future<LocalDateTime> future : results) {
			System.out.println(dtf.format(future.get()));
		}

		pool.shutdown();
	}

}
