package helloJpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();     //트랜잭션 시작
        //code
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity1", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

            em.persist(member);
            
            em.flush();
            em.clear();

            System.out.println("========== START ===========");
            Member findMember = em.find(Member.class, member.getId());

            //homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity") 수정 시 이렇게 하면 안됨
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode())); //이렇게 객체를 새로 만들어서 통으로 바꿔야 함

            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

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