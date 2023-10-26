package dhgudtmxhs.dhgudtmxhsSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DhgudtmxhsSpringBootApplication {

	// spring boot java의 main method 실행으로 그냥 실행시킬 수 있다.
	// Tomcat started on port(s): 8080 (http) with context path '' -> http://localhost:8080/ 로 웹 확인 가능
	public static void main(String[] args) {

		SpringApplication.run(DhgudtmxhsSpringBootApplication.class, args);
		// DhgudtmxhsSpringBootApplication 이 실행되고
		// @SpringBootApplication 어노테이션은 톰캣 웹서버를 내장하고있다. 웹서버를 자체적으로 띄움
	}

	// settings 에서 gradle 검색 -> build and run을 intellij로 바꿔주면 gradle을 통해 실행하는 것 보다
	// gradle을 통하지 않고 intellij 에서 자바를 바로 띄워서 돌려서 더 빠르다.


}
