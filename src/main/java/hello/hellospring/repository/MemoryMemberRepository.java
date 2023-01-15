package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store=new HashMap<>();
    private static long sequence = 0L; //회원 등록할때마다 sequnce를 늘려가며 id로지정
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Optional.ofNullable()로 NULL가능성 있는것 처리
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        //store의 value(member)들중 찾고자 하는 name과 같은 value(member)를 아무거나 찾음
        //stream : 저장되어 있는 요소들을 하나씩 참조하여 람다 식으로 처리 가능
        //Stream.filter() : filter내의 함수로 요소들을 걸러준다.
        //람다 : 익명의 함수(매개변수 -> 처리할 함수) 구조로 이루어짐
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //모든 value(멤버들 반환)
    }

    public void clearStore(){
        store.clear();
    }
}
