package helloJpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();     //트랜잭션 시작
        //code
        try {
            Member member1 = new Member();
            member1.setUsername("user1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("user1");
            em.persist(member2);

            em.clear(); //영속성 컨텍스트 다 지움
            em.flush();

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference.getClass() = " + reference.getClass()); //Proxy
            reference.getUsername(); //강제 초기화
            Hibernate.initialize(reference); //강제 초기화
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(reference)); //프록시 인스턴스의 초기화 여부 확인

//            em.detach(reference); //영속성 컨텍스트에서 관리안하도록 함
//            em.close(); //영속성 컨텍스트 닫음

//            System.out.println("reference = " + reference.getUsername()); //영속성 컨텍스트에 없으니 오류 발생

//            Member m1 = em.find(Member.class, member1.getId());
//            Member m2 = em.find(Member.class, member2.getId());
//            System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass())); //find 한 것과 getReference 한 것은 == 비교하면 false, instanceof 로 사용해야 함
//            System.out.println("m1 == m2 : " + (m1 instanceof Member));

//            Member findMember = em.find(Member.class, member.getId());
//            Member findMember = em.getReference(Member.class, member1.getId()); //호출 시점에 select 쿼리 출력 X, 실제 사용 되는 시점에 select 쿼리 실행
//            System.out.println("findMember.getId() = " + findMember.getId()); //위에 id가 있으니까 쿼리 실행 X
//            System.out.println("findMember.getId() = " + findMember.getUsername()); //이 시점에 select 쿼리 실행
            tx.commit();    //커밋
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}