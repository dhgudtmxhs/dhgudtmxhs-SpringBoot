package dhgudtmxhs.dhgudtmxhsSpringBoot.service;

import dhgudtmxhs.dhgudtmxhsSpringBoot.domain.Member;
import dhgudtmxhs.dhgudtmxhsSpringBoot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // JPA 데이터 변경 전부 다 트랜젝션에서 수행되어야 한다.
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); // 다형성
    // 하위 형식(서브 클래스 또는 구현 클래스)은 상위 형식(슈퍼 클래스 또는 인터페이스)으로 대체될 수 있어야 한다.
    // MemoryMemberRepository 객체를 MemberRepository 타입으로 대체(할당)할 수 있다.
    // 다른 구현 클래스를 사용하더라도 코드의 변경 없이 MemberRepository 인터페이스를 사용할 수 있게 해준다.
    // -> 유연성, 확장성을 높임 (코드의 유지보수 쉬워짐)

    // 근데 이렇게 하면 test의 객체와 서로 다른 객체가 됨. static이 아니면 문제가 생길 수 있음.

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { // 외부에서 넣어준다
        this.memberRepository = memberRepository; // 테스트 beforeEach와 연결 // 의존성 주입 DI
    }
    //Spring 4.3 버전부터는 @Autowired 애노테이션을 사용할 때, 생성자가 한 개뿐인 경우 @Autowired를 생략할 수 있다. 이렇게하면 생성자 주입이 자동으로 적용됩니다.


    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member){

        // 같은 이름 중복 회원 X
        //Optional<Member> result = memberRepository.findByName(member.getName());
        // result.get(); 으로 꺼내도 되는데 바로 꺼내는걸 권장하지 않음
        // orElseGet도있음

        validateDuplicateMember(member); // extract method
        memberRepository.Save(member); // 예외가 던져지면 실행하지 않음. 예외처리 블록으로 가게 됨
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
