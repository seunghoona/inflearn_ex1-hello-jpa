package com.test.ex1_hello_jpa;

import com.test.ex1_hello_jpa.domain.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

            member.setUsername("member1");
            Adress adress = new Adress("한국", "허준로", "102302");
            member.setHomeAdress(adress);


            member.getFavoriteFood().add("치킨");
            member.getFavoriteFood().add("족발");
            member.getFavoriteFood().add("피자");

            member.getAdresses().add(new Adress("중국","상하이","09203"));
            member.getAdresses().add(new Adress("일본","도쿄","12939"));

            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("========Start===========");
            Member findMember = em.find(Member.class, member.getId());


            findMember.setHomeAdress(new Adress("미국",adress.getStreet(),adress.getZipcode()));


            //컬렉션업데이트 치킨을 -> 한식으로 바꾸고 싶다면
            findMember.getFavoriteFood().remove("치킨");
            findMember.getFavoriteFood().add("한식");

            //ArrayList를 삭제
            findMember.getAdresses().remove(new Adress("중국", "상하이", "09203"));
            findMember.getAdresses().add(new Adress("아프리카", "품바야", "09203"));
            

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
