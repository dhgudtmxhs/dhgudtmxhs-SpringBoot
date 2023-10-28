package dhgudtmxhs.dhgudtmxhsSpringBoot.repository;

import dhgudtmxhs.dhgudtmxhsSpringBoot.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 시퀀스

    @Override
    public Member Save(Member member) {
        member.setId(++sequence); // 아이디 세팅 (시퀀스) 0, 1, 2, 3, 4, 5 ...
        store.put(member.getId(), member); // map에 저장 ( key(id) : 시퀀스 , 값 : member 객체( 고유의 name을 가질 것 같음 )
        return member; // 반환    // {1=???, 2=??? 3=??? } Test class에서 name을 삽입한다.
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 매개변수로 받은 Long id를 Map에서 찾는다.
        // null이 반환된 가능성이 있으면 Optional 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)).findAny();
        // store.values() = store Map의 모든 값을 Collection으로 반환. 모든 멤버가 포함된 컬렉션을 얻는다.
        // .stream() 컬렉션을 스트림으로 변환하여 스트림으로 데이터를 처리 할 수 있게 만든다.
        // .filter() 람다 표현식을 사용한 필터링으로 각 멤버의 이름을 name과 비교해 조건에 맞는 멤버만을 유지한다.
        // (parameters) -> expression
        // .findAny() 조건에 맞는 멤버중 임의의 하나를 찾아 반환한다. 조건에 맞는 멤버 없으면 Optional.empty()를 반환한다.

        // stream 안쓰면 for문으로 돌려야 한다고 함. filter, findAny 는 스트림연산, -> 는 람다식

        /*  return store.values().stream()
            .filter(member -> {
                if (member.getName().equals(name)) {
                    // 일치하는 멤버를 찾았을 때 추가 동작을 수행할 수 있음
                    // 이 예제에서는 추가 동작을 수행하지 않고 조건만 검사
                    return true; // 조건에 맞으면 요소를 유지
                } else {
                    return false; // 조건에 맞지 않으면 요소를 필터링
                }
            })
            .findAny();*/
        // 이런 방식으로 람다식이 돌아간다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store.values() == 멤버들
    }

    public void clearStore(){
        store.clear(); // Map을 비운다.
    }

}
