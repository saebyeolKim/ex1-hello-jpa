package helloJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); //** => 편의메소드, 편의메소드는 두개 중 하나만 걸어준다.
            em.persist(member);

            //team.addMember(member); => 편의메소드

            //team.getMembers().add(member); //** 양방향 매핑 시 양쪽 객체에 모두 값을 세팅해주어야 한다. => 편의메소드를 만들어서 지우자. 단반향 매핑만으로 연관관게 매핑 완료 해야 함

            em.flush(); //영속성컨텍스트 날리기
            em.clear(); //영속성컨텍스트 초기화

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit();    //커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}