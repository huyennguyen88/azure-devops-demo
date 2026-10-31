package com.example.azuredevopsdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AzureDevopsDemoApplicationTests {

	@Test
	void helloShouldReturnMessage() {
		HelloController controller = new HelloController();

		String result = controller.hello();

		assertEquals("Hello Azure DevOps!", result);
	}
}
