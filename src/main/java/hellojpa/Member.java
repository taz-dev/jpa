package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity //@Entity는 JPA가 처음 로딩될 때 JPA를 사용하는 걸로 인식하기 때문에 꼭 넣어줘야 함
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
//@Table(name = "USER") DB 테이블명이 "USER"일 경우
public class Member {

    @Id //JPA에게 PK가 뭔지 알려줘야 하기 때문에 @Id를 써줘야 함
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

    @Column(name = "name", nullable = false) //DB 컬럼명이 "name"일 경우
    private String username;

//    private int age;

//    EnumType.STRING : enum 이름을 DB에 저장
//    EnumType.ORDINAL : enum 순서를 DB에 저장 --> 사용X
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    private LocalDate testLocalDate; //년월
//    private LocalDateTime testLocalDateTime; //년월일
//
//    @Lob //큰 컨텐츠를 넣고 싶을 때
//    private String description;
//
//    @Transient //특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
//    private int temp;

    public Member(){
    }

//    public Member(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    //Getter and Setter 단축키(Alt + Insert)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
