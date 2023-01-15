package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {    //이 클래스의 Test만들고 싶으면 컨트롤+쉬프트+T  키로 만들 수 있다
    private final MemberRepository memberRepository;
    //@Autowired
    public MemberService(MemberRepository memberRepository){  //Dependency Injection (D.I)
                                                              //MemberService 객체 만들때 repository 외부에서받음
        this.memberRepository=memberRepository;
    }
    public Long join(Member member){      //회원가입 (같은 이름의 중복회원 X)
        Optional<Member> result=memberRepository.findByName(member.getName());
        result.ifPresent(m->{           //Optional객체 함수, 기존에 존재하면 오류 띄우기
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
