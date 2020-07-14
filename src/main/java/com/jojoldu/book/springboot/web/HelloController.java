package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name,amount);
    }
}



//@RestController
//- 컨트롤러를 json을 반환하는 컨트롤러로 만들어 준다.
//-예전에 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다.

//@GetMapping
//-http메소드인 get의 요청을 받을 수 있는 api를 만들어 준다.
//-예전 RequestMapping(method = Request.Method.GET)으로 사용되었다.
//-이프로젝트에서는 /hello로 요청이 오면 문자열 hello를 반환하는 기능을 가진다.

//@RequestParam
//외부에서 api로 넘긴 파라미터를 가져오는 어노테이션
//여기에서는 외부에서 name(@RequestParam("name")) 이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장한다.
