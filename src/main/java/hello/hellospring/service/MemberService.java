package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {    //이 클래스의 Test만들고 싶으면 컨트롤+쉬프트+T  키로 만들 수 있다
    private final MemberRepository memberRepository;
    //@Autowired
    public MemberService(MemberRepository memberRepository){  //Dependency Injection (D.I)
                                                              //MemberService 객체 만들때 repository 외부에서받음
        this.memberRepository=memberRepository;
    }
    public Long join(Member member){      //회원가입 (같은 이름의 중복회원 X)
        long start=System.currentTimeMillis();  //메소드의 실행 시간 측정용(AOP 예제)
        try {
            Optional<Member> result = memberRepository.findByName(member.getName());
            result.ifPresent(m -> {           //Optional객체 함수, 기존에 존재하면 오류 띄우기
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis(); //메소드 실행 시간 측정용(AOP예제)
            long timeMs = finish - start;
            System.out.println("join= " + timeMs + "ms");
        }
        //사용 메소드가 많을수록 시간 측정 코드 대입이 힘들어짐+유지보수가 힘듦 -> AOP사용
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
