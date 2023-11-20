package dhgudtmxhs.dhgudtmxhsSpringBoot.domain;

// JPA : 인터페이스 -> 보통 hibernate를 씀
//       객체 ORM ( object + relational (관계형 데이터베이스의 r과 같음) + Mapping)


import jakarta.persistence.*;
// jakarta는 java EE에서 관리되던 기술스택이 jakarta EE로 이관되며 패키지 명이 변경 된 것.

@Entity
public class Member {

    // pk 매핑
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 아이디 자동생성 -> identity
    private Long id; // 시스템이 저장하는 id ( 데이터 구분)

    // 컬럼 매핑 @Column (name = "username") 하면 username 컬럼과 매핑되는것. 엔티티의 필드 이름이 테이블의 열 이름과 일치하면 안해도 됨
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
