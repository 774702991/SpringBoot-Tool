package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest
@EnableScheduling
class TimingApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("开始");
	}

}
