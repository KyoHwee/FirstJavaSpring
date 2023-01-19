package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;  //Spring JPA 사용시 import하고 extends한다.

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    //Member객체를 Long타입의 id로 구분하기 때문, Spring JPA 인터페이스가 만들어둔 JPA구현하면 자동으로 spring bean에 등록

    @Override
    Optional<Member> findByName(String name);  //나머지 메소드들은 기본적인 Spring JPA에서 제공한다.
    /*
    메소드명의 규칙을 통해서 대신 JPQL 언어로 Spring JPA가 변환해준다
    위의 메소드는 findByName= select m from Member m where m.name = ?
    따라서 findByNameAndId(String name, Long id); 같은 식으로 선언만 해도 자동변환된다.
     */
}
