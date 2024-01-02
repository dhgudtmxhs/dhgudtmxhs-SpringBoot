package dhgudtmxhs.dhgudtmxhsSpringBoot.repository;

import dhgudtmxhs.dhgudtmxhsSpringBoot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// JpaRepository 인터페이스를 상속하면 자동으로 구현체를 만들어서 스프링 빈에 자동 등록해줌 -> 인젝션을 받을 수 있음
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> /*Member의 Id 자료형*/,
                                                        MemberRepository
{
    // select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name); // findByNameAndId 이런식으로도 가능.
    // findBy~~~~ 로 쿼리 자체가 무엇인지 정의할 수 있음. name을 찾는건지 Id를 찾는건지

    // 동적 쿼리는 Querydsl이라는 라이브러리를 이용(JPQL 사용 가능 네이티브 쿼리(그냥 처음부터 짜는것) )
}