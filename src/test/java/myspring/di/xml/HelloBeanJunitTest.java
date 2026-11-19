package myspring.di.xml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanJunitTest {
	
	@Test
	void hello() {
		//1. Bean Container 객체 생성
		BeanFactory factory = new GenericXmlApplicationContext("classpath:spring_beans.xml");
		
		//2. Hello Bean을 Container 에게 요청하기 Bean의 id를 기준으로 Bean을 요청하기
		Hello helloByID = (Hello)factory.getBean("hello");
		//2-1 Hello Bean을 Container 에게 요청하기 Bean의 id와 Type을 기준으로 Bean을 요청하기
		Hello helloByType = factory.getBean("hello", Hello.class);
		
		//2개의 Hello Bean의 주소비교
		System.out.println(helloByID == helloByType);
	}
}
