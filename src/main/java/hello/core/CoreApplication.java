package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication	//@SpringBootApplication는 스프링 부트에서 관례로 사용
						// @ComponentScan이 포함되어 있다
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
