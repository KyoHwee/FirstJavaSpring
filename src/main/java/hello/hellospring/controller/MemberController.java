package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller   //컨트롤러는 컴포넌트 스캔으로 해야한다
public class MemberController {
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }
}
