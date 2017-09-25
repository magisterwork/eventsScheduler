package org.spree.vkscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("org.spree.vkscheduler")
@EnableScheduling
public class VkSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkSchedulerApplication.class, args);
	}
}
