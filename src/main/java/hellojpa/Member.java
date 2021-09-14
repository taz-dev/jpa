package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //@Entity는 JPA가 처음 로딩될 때 JPA를 사용하는 걸로 인식하기 때문에 꼭 넣어줘야 함
//@Table(name = "USER") DB 테이블명이 "USER"일 경우
public class Member {

    @Id //JPA에게 PK가 뭔지 알려줘야 하기 때문에 @Id를 써줘야 함
    private Long id;

    //@Column(name = "username") DB 컬럼명이 "username"일 경우
    private String name;

    public Member(){
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getter and Setter 단축키(Alt + Insert)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
