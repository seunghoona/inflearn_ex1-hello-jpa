package com.test.ex1_hello_jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.test.ex1_hello_jpa.domain.Member;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // PERSISTENCE 유닛 네임인 hello를 넣어주면된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        ////고객이 무엇인가 상품을 장바구니에 담을 때 마다 DB CONNECTION을 하기위해 반드시 아래 내용이 필요하다.
        EntityManager em = emf.createEntityManager();

        //JPA는 트랜잭션에서 작업이 이루어져야한다.
        EntityTransaction  tx = em.getTransaction();
        tx.begin();
        try {
            /* 실제 코드가 들어가는 부분 */

            /*
             * 데이터 삭제 Member findMember = em.find(Member.class, 1L);
             * em.remove(findMember.getId());
             */


            /*
             * 데이터 수정 Member findMember = em.find(Member.class, 1L);
             * findMember.setName("친구");
             */

            /*
             * 특정한 조건을 가지는 조회를 해야한다면 어떻게 해야할 것인가? JPQL을 사용하면된다. 1. 테이블의 대상이 아닌 Member 객체를
             * 다가져와라는 의미이다.
             *
             *
             * List<Member> result = em.createQuery("select m from Member m", Member.class)
             * .getResultList(); result.stream().forEach((s)->{
             * System.out.println(s.getId()+"::::"+s.getName()); });
             *
             */

            //페이징처리
            List<Member> result = em.createQuery("SELECT m FROM Member AS m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            result.stream().forEach((s) -> {
                System.out.println(s.getId() + "::::" + s.getName());
            });

            /* 실제 코드가 들어가는 부분 종료 */
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.clear();
            em.close();
        }
    }
}
