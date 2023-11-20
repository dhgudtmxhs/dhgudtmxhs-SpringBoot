package dhgudtmxhs.dhgudtmxhsSpringBoot.service;

import dhgudtmxhs.dhgudtmxhsSpringBoot.domain.Member;
import dhgudtmxhs.dhgudtmxhsSpringBoot.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService = new MemberService(memberRepository);
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){ // 테스트 실행할 때 마다 각각 동작
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 왜 계속 새로운 객체를 의존성 주입해서 만들까??
    // 그냥 테스트를 위해서 만든거라 findAll 같이 전체회원 조회같은 걸 할 때,
    // 애초에 내가 임의로 만들어서 할꺼니까 객체를 새로 생성해도 상관이 없다.
    // 데이터를 어디서 불러오지 않고 메소드 안에서 값 넣어서 테스트할꺼니까.
    // 객체가 static이 아닐 때, 이렇게 생성자로 안하면 test의 객체와 서로 다른 객체가 되어서 문제가 생긴다.

    @AfterEach // 메소드가 실행이 끝날 때 마다 동작하는 어노테이션
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {

        // given 뭔가 주어지고,
        Member member = new Member();
        member.setName("hello");

        // when 이걸 실행했을 때,
        Long saveId = memberService.join(member);

        // then 이 결과가 나와야 한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외발생시키기(){

        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 특정 메소드 호출 시 예외가 발생하는지를 확인
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /* try{
            memberService.join(member2);

        }catch(IllegalStateException e){ // 예외 throw 받음.
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 메세지가 잘 받아지는지 확인용
        }*/

        // when

        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}