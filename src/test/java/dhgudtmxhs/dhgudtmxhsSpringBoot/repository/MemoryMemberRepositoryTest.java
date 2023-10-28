package dhgudtmxhs.dhgudtmxhsSpringBoot.repository;

import dhgudtmxhs.dhgudtmxhsSpringBoot.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 테스트 부터 만들고, 구현 클래스를 만들어서 실행하는 것 -> TDD

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메소드가 실행이 끝날 때 마다 동작하는 어노테이션
    public void afterEach(){
        repository.clearStore();
    }

    // test의 메소드 실행 순서는 랜덤이라고 생각해야 함. 테스트는 의존관계도 가지면 안됨.
    // 메소드끼리 객체의 변수명 같은 게 겹치면 오류가 남.
    // -> 테스트가 하나 끝나면 데이터를 Clear 시켜줘야 한다.


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.Save(member); // id저장하는 메소드 -> name을 넘겨서 id, name을 k:v 형식으로 저장할 수 있게 된다.

        Member result = repository.findById(member.getId()).get();
        // repository로 갔다 온 id를 넣고 메소드 매개변수로 보내서 찾는다.
        // get() -> optional 객체에서 값을 추출한다. 원래 get()일떄 optional.empty면 exception 발생하는데 테스트라 그냥 썼다고 함.

        //System.out.println("result = " +  (result == member));
        // 글자를 매번 보기가 힘듬
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); // Assertion -> static import로 없앰 // 객체를 비교함

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.Save(member1); // id, name 저장

        Member member2 = new Member();
        member2.setName("spring2");
        repository.Save(member2);

        Member result = repository.findByName("spring1").get(); // name으로 찾음. get()은 optional을 벗긴다고 생각

        assertThat(result).isEqualTo(member1); // member2 -> spring 2 로만 정상 동작

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.Save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.Save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); // 2개 넣는다면 크기가 2개 나와야 정상 실행

    }


}
