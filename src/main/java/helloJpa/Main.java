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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("user1");
            member1.setTeam(team);
            em.persist(member1);

            em.clear(); //영속성 컨텍스트 다 지움
            em.flush();

            Member m = em.find(Member.class, member1.getId());
            System.out.println("m = " + m.getTeam().getClass()); //Proxy 로 나옴
            System.out.println("=====================");
            m.getTeam().getName(); //실제 team 을 사용하는 시점에 초기화
            System.out.println("=====================");
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