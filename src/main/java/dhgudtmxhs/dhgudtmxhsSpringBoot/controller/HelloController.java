package dhgudtmxhs.dhgudtmxhsSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody // http에서도 header, body가 있는데 그 body 부에 이 데이터를 내가 return으로 넣겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // html과 관련 없이 그냥 문자가 적은 그대로 내려감.
                               // http://localhost:8080/hello-string?name=api
    }

    // 데이터 내리기
    @GetMapping("hello-api")
    @ResponseBody
    public Hello HelloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // Json 방식으로출력 { "name" : "str" }
        // Spring 기본 : 문자열은 문자열로 출력하지만 객체에 담아서 Responsebody로 반환하면 기본값 JSON 형식으로 반환한다.

        // ResponseBody 가 없으면 viewResolver한테 던져서 맞는 template 찾아주는데,

        // ResponseBody 가 있으면 httpMessageConverter가 동작함.
        // 단순 문자 --> StringHttpMessageConverter 동작
        // 객체 --> MappingJackson2HttpConverter 동작 (JsonConverter)
        // Jackson : Java 객체를 Json으로 바꿔주는 라이브러리 , Gson도 있지만 Spring의 기본값은 Jackson임.
        // Java -> Json 직렬화, Json -> Java 역직렬화 가능

    }
    static class Hello {
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        private String name;

    }

}
