package dhgudtmxhs.dhgudtmxhsSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("Hello") // http://localhost:8080/Hello 주소를 받음
    public String hello(Model model){
        model.addAttribute("data","springboot"); // model(data:hello)
        return "hello"; // resources 안에 있는 hello 찾아서 렌더링
        // 컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리한다.

        // 스프링 부트 템플릿엔진 기본 viewName 매핑
        // 'resources:templates/' + {ViewName} + '.html'
        // -> resources:templates/hello.html 이 열림

        // MVC : Controller +  Model + 화면(템플릿엔진) 를 모델 뷰 컨트롤러 MVC 라고 한다. -> 동적 개발에 사용

        // API : 안드로이드, 아이폰 클라이언트 과거 xml -> 현재 JSON 으로 클라이언트한테 전달하는 방식
        // ex) Vue, React, Vue.js, React

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = false) String name
                           ,Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

}
