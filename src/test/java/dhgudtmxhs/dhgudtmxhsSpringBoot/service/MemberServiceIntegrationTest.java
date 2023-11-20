package dhgudtmxhs.dhgudtmxhsSpringBoot.service;

import dhgudtmxhs.dhgudtmxhsSpringBoot.domain.Member;
import dhgudtmxhs.dhgudtmxhsSpringBoot.repository.MemberRepository;
import dhgudtmxhs.dhgudtmxhsSpringBoot.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행 진짜 스프링을 띄워서 테스트함.
@Transactional  // 테스트 시작 전 트랜잭션 실행 -> 쿼리 시행 -> 테스트 끝 -> 롤백처리 --->>> 테스트 반복가능
                // 대신 스프링을 띄워야해서 스프링 없이 테스트하는 것 보다 오래걸린다.
                // --> 스프링 없이 단위테스트를 만드는게 더 좋음
class MemberServiceIntegrationTest {

    //MemberService memberService = new MemberService(memberRepository);
    
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository; // test는 field 기반으로 해도 됨

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

    }


}