package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller   //컨트롤러는 컴포넌트 스캔으로 해야한다
public class MemberController {
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";  //templates-members-createMemberForm.html
    }

    @PostMapping("/members/new")   //데이터를 전달받을때 html파일에서 메소드가 post형식으로 구성되어있는 곳에서 데이터 넘어옴
    public String create(MemberForm form){   //MemberForm객체인 form에 html파일에서 설정된 id를 가진 변수에 데이터 저장
        Member member=new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";       //홈화면으로 돌아가기=redirect:/
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
