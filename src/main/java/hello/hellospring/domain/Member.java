package hello.hellospring.domain;

import javax.persistence.*;

@Entity        //jpa는 객체와 관계형데이터베이스를 연결지어주는 ORM기술이므로, @Entitiy로 객체를 jpa가 관리하게 해준다
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)    //id는 PK(기본 키)이며, identity다(자동으로 생성됨)
    private Long id;

    //@Column(name="username")   설정하면 String name변수가 DB의 username과 연결된다
    private String name;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
}
