package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //EntityManagerFactory는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //EntityManager는 쓰레드간에 공유X(사용하고 버리자!)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //비영속
            //Member member = new Member();
            //member.setId(101L);
            //member.setName("HelloJPA");

            //영속(DB에 저장되는 것은 아님)
            //em.persist(member); //JPA에 member 저장
            //em.detach(member); //회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
            //em.remove(member); //엔티티 삭제

            //===============================================================

            //Member findMember = em.find(Member.class, 101L);
            //em.remove(findMember); //member 삭제

            //===============================================================

            //Member member1 = new Member(150L, "A");
            //Member member2 = new Member(160L, "B");

            //em.persist(member1);
            //em.persist(member2);
            //여기까지 INSERT SQL을 DB에 보내지 않음

            //커밋하는 시점에 DB에 INSERT SQL을 보냄
            //tx.commit();

            //===============================================================

            //영속 엔티티 조회
            Member member = em.find(Member.class, 150L);
            //영속 엔티티 데이터 수정
            member.setName("ZZZ");
            //em.persist(member); --> 필요없는 코드!!
            tx.commit(); //Entity와 스냅샷을 비교 후 UPDATE쿼리를 생성

            //===============================================================

            //Member findMember = em.find(Member.class,1L);
            //findMember.setName("HelloJPA"); //member 수정
            //따로 em.persist를 통해 수정된 값을 다시 저장하지 않아도 됨
            //JPA를 통해 Entity를 가져오면 JPA가 값을 관리하게 되는데
            //JPA가 트랜잭션을 커밋하는 시점에 변경된 부분이 있으면 UPDATE쿼리를 생성
            //JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

            //JPQL은 Entity 객체를 대상으로 쿼리 -> 객체 지향 SQL
            //SQL은 데이터베이스 테이블을 대상으로 쿼리
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            for(Member member : result){
//                System.out.println("member.name = " + member.getName());
//            }
        } catch (Exception e){
            tx.rollback();

        } finally {
            em.close(); //사용 후 EntityManager를 꼭 닫아주자!
        }
        emf.close();
    }
}
