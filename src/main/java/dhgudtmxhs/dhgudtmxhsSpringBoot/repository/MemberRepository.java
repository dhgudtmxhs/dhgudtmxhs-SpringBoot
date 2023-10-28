package dhgudtmxhs.dhgudtmxhsSpringBoot.repository;

import dhgudtmxhs.dhgudtmxhsSpringBoot.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member Save(Member member);
    Optional<Member> findById(Long id); // 원래 Member FindById(Long id) 를 null 방지로 제네릭스, optional 씀
    Optional<Member> findByName(String name); // optional -> java 8 기능, null 반환 방지용
    List<Member> findAll(); // 없으면 null이 아닌 빈 리스트가 반환되서 optional 사용할 필요 없음

}
