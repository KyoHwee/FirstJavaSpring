package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {   //클래스를 run하면 각 메소드별 테스트 결과가 도출됨
    MemoryMemberRepository repository=new MemoryMemberRepository();
    @AfterEach      //각 @Test 메소드들이 실행된 후 데이터 클리어 해주는 기능 만들기
    public void afterEach(){
        repository.clearStore();
    }

    @Test //test용은 @Test 붙여준다
    public void save() {       //임의의 한명의 member 만들고 저장한 후 둘을 비교하여 같은지 확인
        Member member = new Member();
        member.setName("kkh");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();  //get()으로 Optional설정되어있는 객체 꺼내주기
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result); //result가 member와 같은 것인지 기대하는 기능(Test용, 주피터문법)
        assertThat(member).isEqualTo(result);  // assertj문법, Assertions. 를 스태틱으로 선언하여 생략가능
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("k1");
        repository.save(member1);

        Member member2=new Member();   //이 코드 3줄을 위에꺼에서 복붙 후 member1(겹치는 변수명)에 쉬프트+F6로 단체수정
        member2.setName("k2");
        repository.save(member2);

        Member result=repository.findByName("k1").get();
        //Member result=repository.findByName("k2").get();  하면 틀린 Test

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("k1");  //클래스 전체를 테스트할때 findAll먼저 실행된 후 findByName이 실행되면
                                //같은 이름의 member가 이미 저장되어 있는 상태이므로 오류 >>테스트마다 데이터 클리어필요
                                //그래서 만드는것이 @AfterEach
        repository.save(member1);

        Member member2=new Member();
        member2.setName("k2");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
