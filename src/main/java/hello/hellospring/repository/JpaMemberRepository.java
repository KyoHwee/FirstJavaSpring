package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;  //jpa는 EntityManager 객체로 동작하므로 jpa사용하려면 EntityManager 주입받아야함
    //참고로 EntityManager은 Spring Boot가 만들어줌
    public JpaMemberRepository(EntityManager em){  //repository에 EntityManager 주입
        this.em=em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member); //persist(객체)함수 : 영구적 저장하는 메소드(DB에)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member= em.find(Member.class, id);     //find(객체.class, 식별변수) : 식별되는 것 찾아준다(pk만)
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result= em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name).getResultList();
        //jpql 문법(객체지향 쿼리 언어) 사용해야 한다 (pk가 아닌 것으로 식별하는 경우)
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result= em.createQuery("select m form Member m", Member.class).getResultList();
        //jpql query문법 : table 대상이 아닌 객체(entity)를 대상으로 query를 날리는 것
        return result;
    }
}
