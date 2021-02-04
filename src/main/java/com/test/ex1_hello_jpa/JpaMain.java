package com.test.ex1_hello_jpa;

import com.test.ex1_hello_jpa.domain.*;

import javax.persistence.*;
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

            Adress adress = Adress.builder()
                    .city("서울")
                    .street("허준로")
                    .zipcode("176")
                    .build();

            Member member = new Member();
            member.setUsername("나승후a");
            member.setPeriod(Period.builder()
                    .stDateTime(LocalDateTime.now())
                    .enDateTime(LocalDateTime.now())
                                    .build());
            member.setAdress(adress);

            em.persist(member);
            Member member2 = new Member();
            member2.setUsername("나승후a");
            member2.setPeriod(Period.builder()
                    .stDateTime(LocalDateTime.now())
                    .enDateTime(LocalDateTime.now())
                    .build());
            member2.setAdress(adress);
            em.persist(member2);


            member.setAdress(Adress.builder().city("미국").build());


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
