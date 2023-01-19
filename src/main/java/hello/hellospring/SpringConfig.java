package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration  //수동 스프링 빈 등록
public class SpringConfig {
    /*
    private DataSource dataSource;
    @Autowired             //스프링부트가 데이터 소스 만들어서 부여해준다.
    public SpringConfig(DataSource dataSource){
        this.dataSource=dataSource;
    }

    private EntityManager em;            //JPA사용하기 위하여 EntityManager 등록(D.I)
    @Autowired
    public SpringConfig(EntityManager em){
        this.em=em;
    }

     */

    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository){  //Spring JPA가 JpaRepository<> 구현해놓은 인터페이스의
        //구현체를 만들어서 spring bean에 등록해준다
        this.memberRepository=memberRepository;
    }

    @Bean
    public MemberService memberService(){
       // return new MemberService(memberRepository()); //Spring JPA 사용 X
        return new MemberService(memberRepository);  //Spring JPA 사용
    }
    /*   Spring Jpa 사용할때는 사용 x
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();  //Memory로 회원저장
        //return new JdbcMemberRepository(dataSource); //DB로 회원저장
        //return new JdbcTemplateMemberRepository(dataSource);  //jdbc 템플릿을 이용하여 회원저장
        //return new JpaMemberRepository(em);
    }

     */
}
