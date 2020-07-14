package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)//테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
//여기서는 SpringRunner라는 스프링 실행자를 사용한다.
//즉 스프링부트테스트와 JUnit 사이에 연결자 역할을 한다.

@WebMvcTest(controllers = HelloController.class)//여러 스프링 테스트 어노테이션 중 web(spring MVC)에 집중할 수 있는 어노테이션이다.
//선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다.
//단 @Service, @Component, @Repository등은 사용할수 없다.
//여기서는 컨트롤러만 사용하기 때문에 선언한다.
public class HelloControllerTest {
    @Autowired//스프링이 관리하는 Bean을 주입받는다.
    private MockMvc mvc;//webAPI를 테스트할 때 사용한다.
    //스프링 MVC 테스트의 시작점이다.
    //이 클래스를 통해 HTTP GET, POST등에 대한 API를 테스트 할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello"))//MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다. 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있다.
        .andExpect(status().isOk())//mvc.perform의 결과를 검증한다. http header의 status를 검증한다.
                //200, 404, 500 등의 상태를 검증한다.
                //여기서는 isOK 즉 200인지 아닌지를 검증한다.
        .andExpect(content().string(hello));//mvc.perform의 결과를 검증한다.
        //응답본문의 내용을 검증한다.
        //contoller에서 "hello"를 리턴하기 때문에 이값이 맞는지 검증한다.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "fuck";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)    //param: api 테스트할 때 사용될 요청 파라미터 설정. 단 값은 string만 허용. 그래서 숫자 날짜 데이터도 등록할 때는 문자열로 변경해야만 가능!!
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))//jsonpath: json 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시. 여기서는 name과 amount를 검증하니 $.name , $.amount로 검증한다.
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
