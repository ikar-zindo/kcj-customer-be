package com.kcj_customer_be;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class KCJCustomerBEApplicationTests {

	@Test
	void context_loads() {
	}

	public static void main(String[] args) {
		System.out.println("Hello");
		Date dateNow = new Date();
		System.out.println(dateNow);
	}
}

