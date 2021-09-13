package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        //EntityManagerFactory는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //EntityManager는 쓰레드간에 공유X(사용하고 버리자!)
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member); //JPA에 member 저장

//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember); //member 삭제

            Member findMember = em.find(Member.class,1L);
            findMember.setName("HelloJPA"); //member 수정
            //따로 em.persist를 통해 수정된 값을 다시 저장하지 않아도 됨
            //JPA를 통해 Entity를 가져오면 JPA가 값을 관리하게 되는데
            //JPA가 트랜잭션을 커밋하는 시점에 변경된 부분이 있으면 UPDATE쿼리를 생성
            //JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

            tx.commit();

        } catch (Exception e){
            tx.rollback();

        } finally {
            em.close(); //사용 후 EntityManager를 꼭 닫아주자!
        }
        emf.close();
    }
}
