package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository=new MemoryMemberRepository();

    @BeforeEach             //각 메소드 실행 전에 실행
    public void beforeEach(){    //Service객체에 repository를 생성하여 넣어준다.
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given-when-then 문법 : 뭔가 주어졌는데, 뭔가 실행했을 떄, 뭔가 실행되어야 한다
        //given
        Member member=new Member();
        member.setName("hello");
        //when
        Long saveId=memberService.join(member);
        //then     >>회원가입한 member가 repository에 있는지 확인
        Member findMember=memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외확인(){
        //given
        Member member1=new Member();
        member1.setName("spring");
        Member member2=new Member();
        member2.setName("spring");
        //when   같은 이름의 회원이 join했을때 예외뜨는지 확인
        memberService.join(member1);
        /*
        try {
            memberService.join(member2);
        } catch(IllegalStateException e){
            org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            //기존의 member와 이름이 같은 member2가 가입할 때 service의 join기능에서 실행되어야 하는 exception가
            //실행되는지 확인하기 위하여 Exception 메시지가 올바르게 출력되는지 확인
         */
        IllegalStateException e=Assertions.assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        //member2가 가입할때 IllegalStateException(service의 join기능에서 중복가입시 발생시키는 exception) 발생하나확인
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //발생하는 Exception이 출력하는 메시지가 올바른지 확인
        }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}