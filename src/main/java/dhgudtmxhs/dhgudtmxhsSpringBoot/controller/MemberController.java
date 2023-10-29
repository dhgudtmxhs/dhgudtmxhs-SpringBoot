package dhgudtmxhs.dhgudtmxhsSpringBoot.controller;

import dhgudtmxhs.dhgudtmxhsSpringBoot.domain.Member;
import dhgudtmxhs.dhgudtmxhsSpringBoot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController  { // spring 컨테이너가 spring이 뜰 때 controller 어노테이션에 대한 객체 자동 생성
// 웹 브라우저 -> 내장 톰캣 서버 -> 스프링 컨테이너 -> 뷰리졸버 -> HTML(변환 후) -> 웹 브라우저의 순서

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { // 컨테이너 뜰 때 생성자 생성 -> AUTOWIRED로 연결
        this.memberService = memberService;
    }

    //private final MemberService memberService = new MemberService();
    // -> @Autowired
    //    private BoardService_OHS service;
    //    이렇게 하면 필드에 객체 만드는게 아니라 기본 생성자에 대한 객체를 만드는 거라 함.
    //    그래서 생성자가 있으면 기본생성자가 없고, 그 생성자에 따라 의존성 주입을 해야만 함.
    //    Spring은 객체를 생성할 때 먼저 기본 생성자를 호출하고, 그 후에 의존성 주입을 수행한다.

    // 스프링 빈 등록하는 2가지 방법
    // 1. 컴포넌트 스캔과 자동 의존관계 설정 (@Service, @Controller, @Repository는 @Component를 포함하고있음),
    //    bean으로 자동 등록 하는 게 컴포넌트 스캔 -> 의존관계도 자동으로 등록
    // 2. 자바 코드로 직접 스프링 빈 등록

    // @Component 는 기본적으로 main method가 있는 package의 하위 패키지에 대해서 spring bean으로 등록함.
    // dhgudtmxhs.dhgudtmxhsSrpingBoot에 대해서만 가능하다는 것. 설정하면 바꿀 순 있다.
    // 메인메소드 어노테이션 까보면 @ComponentScan 가 있다.

    // 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본 '싱글톤'으로 등록한다.(유일하게 하나만 등록해서 공유)
    // 그래서 같은 스프링 빈이면 같은 인스턴스다. -> 다른 곳들에서 하나를 의존할 때 객체가 같다. 특별한 경우 제외하면 다 싱글톤.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm"; // templates 으로
    }

    @PostMapping("/members/new")
    public String create(@ModelAttribute MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //home
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }



}
