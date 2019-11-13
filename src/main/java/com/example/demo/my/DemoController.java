package com.example.demo.my;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@RequestMapping("/demo/hi")
	public String helloWorld(int a) {

		new Thread(new Runnable() {
			private ReturnTest returnTest = new ReturnTest();

			@Override
			public void run() {
				while (true) {
					try {
						System.err.println(returnTest.getIntVal(a));
						TimeUnit.MILLISECONDS.sleep(5);
					} catch (Exception e) {
						 e.printStackTrace();
						 System.out.println("error:" + e.getMessage());
					}
				}
			}
		}).start();
	
		return "Hello World";
	}
}
