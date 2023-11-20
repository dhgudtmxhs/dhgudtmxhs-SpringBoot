package dhgudtmxhs.dhgudtmxhsSpringBoot;

import dhgudtmxhs.dhgudtmxhsSpringBoot.repository.*;
import dhgudtmxhs.dhgudtmxhsSpringBoot.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

// 자바 코드로 직접 스프링 빈 등록하기
// XML 설정 방식도 있지만 최근엔 거의 사용 안함

// DI는 필드 주입, 생성자 주입, 세터 주입이 있음

// 필드 주입은 테스트 시 필드를 모의 객체로 대체하기 어려울 수 있음,
// 의존성이 외부 노출 안됨(의존성이 외부에서 어떻게 제공되는지 잘 드러나지 않음), 코드 리뷰와 디버깅 어려움

// 세터 주입은 세터가 퍼블릭으로 열려있어야 해서 노출이 되는 단점 있음

// 의존 관계가 실행 중에 동적으로 변하는 경우는 거의 아예 없어서 생성자 주입을 권장한다. 그럴 땐 config 파일을 수정함

// @Autowired 하면 spring에서 bean으로 등록한 객체에 대해서만 동작함.
@Configuration // spring에서 설정 클래스를 정의 (Bean, 외부 설정 파일과 연결, 프로파일 지정, AOP 설정 등)
public class SpringConfig {

    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
        // 생성자 주입은 객체가 생성되는 시점에 모든 의존성이 주입되므로, 객체의 무결성을 보장하며 불변성을 유지함
    }

    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository());

    }

    @Bean
    public MemberRepository memberRepository(){
        //return new JdbcTemplateMemberRepository(dataSource);
        // 상황에 따라 구현 클래스를 변경해야 하면, 설정을 통해 스프링 빈으로 등록한다.
        // 이렇게 하면 db가 바꼈을 때, dbMemoryMemberRepository 이렇게만 바꿔주면 끝임.
        // 다형성 활용 (인터페이스를 두고 구현체를 바꿈) MEMORY -> JDBC로 바꿈
        // SOLID 개방-폐쇄 원칙 - OCP 확장에는 열려있고 수정 변경 에는 닫혀있다.

        // Spring의 DI를 사용하면, 기존 코드를 전혀 손대지 않고 설정만으로 구현 클래스를 변경할 수 있다.

        return new JpaMemberRepository(em);

    }

}
