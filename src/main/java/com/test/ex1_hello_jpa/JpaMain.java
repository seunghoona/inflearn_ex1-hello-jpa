package com.test.ex1_hello_jpa;

import com.test.ex1_hello_jpa.domain.Member;
import com.test.ex1_hello_jpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member2");
            member.setTeam(team);
            em.persist(member);

            //양쪽에 값을 넣어주는 것이 중요하다
            // em flush 또는 em.clear를 사용하지 않으면 1차캐시 상태에서의 데이터를
            //가져와서 데이터를 조회 하기 때문에




            //db에 있는 정보를 가져오는 법
            //그렇지 않으면 실제 영속성 컨텍스트에서 가져온다 .
/*            em.flush();
            em.clear();*/


            //리스트에 값을 셋팅하지 않는데도 값이 출력이 된다.

            Team findTeam = em.find(Team.class, team.getId());
            //객체를 가져와서 반복을 돌릴 떄
            //JPA에서 members를 사용하는 시점에 쿼리를 한번 날립니다.
            List<Member> members = findTeam.getMembers();
            for (Member member1 : members) {
                System.out.println("member1.getUsername() = " + member1.getUsername());
            }


            //
//            //외래키의 존재를 찾아서 조회하는 것이 아니라 직접 조회에서 가져올 수 있다.
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember = " + findMember.getUsername());
//
//
//            //반대로 값을 찾는 것이 가능하다.
//            List<Member> members = findMember.getTeam().getMembers();
//            members.stream().forEach(x-> System.out.println("x.getUsername() = " + x.getUsername()));
            tx.commit();
            /* 실제 코드가 들어가는 부분 종료 */
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.clear();
            em.close();
        }
    }
}
