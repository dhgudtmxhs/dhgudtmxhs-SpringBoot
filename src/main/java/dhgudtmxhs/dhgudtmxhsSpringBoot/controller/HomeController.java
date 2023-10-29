package dhgudtmxhs.dhgudtmxhsSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 컨트롤러가 정적 파일보다 우선 순위가 높다.
// Controller 찾고, 없으면 static 파일을 찾아서 static 인 index.html(정적 리소스)은 무시하고 여기로 온다.
@Controller
public class HomeController {

    @GetMapping("/") // localhost 8080 오면 호출 됨
    public String home(){
        return "home"; // home.html 호출
    }

}
