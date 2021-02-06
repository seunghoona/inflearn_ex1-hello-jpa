package com.test.ex1_hello_jpa;

import com.test.ex1_hello_jpa.domain.Member;
import com.test.ex1_hello_jpa.domain.Team;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Team team3 = new Team();
            team3.setName("TeamC");
            em.persist(team3);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("관리자2");
            member3.setTeam(team3);
            em.persist(member3);


            em.flush();
            em.clear();
/*

            // N+1의 문제가 발생할 수 있기 때문에
            List<Member> resultList1 = em.createQuery("SELECT  m From Member AS m join fetch m.team" , Member.class).getResultList();

            for (Member member : resultList1) {
                System.out.println("member = " + member.getUsername() + " , Member.team = "+ member.getTeam().getName());
                //회원1 ,팀A(SQL)
                //회원2, 팀B(1차캐시)
                //회원3, 팀(SQL)
            }



            List<Member> resultList = em.createQuery("SELECT t.* , m.* FROM Member m join fetch m.team t", Member.class).getResultList();

            for (Member member : resultList) {
                System.out.println("member = " + member);
            }

*/



            tx.commit();
            /* 실제 코드가 들어가는 부분 종료 */
        } catch (Exception e) {
            tx.rollback();
        } finally {
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
