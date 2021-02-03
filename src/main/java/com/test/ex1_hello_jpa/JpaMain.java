package com.test.ex1_hello_jpa;

import com.test.ex1_hello_jpa.domain.Item;
import com.test.ex1_hello_jpa.domain.Member;
import com.test.ex1_hello_jpa.domain.Movie;
import com.test.ex1_hello_jpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // PERSISTENCE 유닛 네임인 hello를 넣어주면된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        ////고객이 무엇인가 상품을 장바구니에 담을 때 마다 DB CONNECTION을 하기위해 반드시 아래 내용이 필요하다.
        EntityManager em = emf.createEntityManager();

        //JPA는 트랜잭션에서 작업이 이루어져야한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {


            Member member = new Member();
            member.setUsername("hello");
            em.persist(member);

            em.flush();
            em.clear();

            //
 /*           em.find(Member.class, member.getId());
            System.out.println("member = " + member.getUsername());
            System.out.println("member.getId() = " + member.getId());*/


            // 프록시에서 값을 가져오면 하이버네이트는 두 값이 동일하는 것을 증명하기 위해서
            // em.find를 사용하여도 프록시를 가져오게 된다.
            Member reference = em.getReference(Member.class, member.getId());
            System.out.println("findMemberClass" + reference.getClass());

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMemberClass" + findMember.getClass());
            System.out.println("(findMember == reference : " + (findMember == reference));

            //프록시 인스턴스 초기화여부 확인
            boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember);
            System.out.println("loaded = " + loaded);


            tx.commit();
            /* 실제 코드가 들어가는 부분 종료 */
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.clear();
            em.close();
        }
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String userName = member.getUsername();
        System.out.println("userName = " + userName);

        Team team = member.getTeam();
        System.out.println("team = " + team);

    }
}
