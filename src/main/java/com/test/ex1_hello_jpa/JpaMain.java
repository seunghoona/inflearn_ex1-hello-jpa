package com.test.ex1_hello_jpa;

import com.test.ex1_hello_jpa.domain.Item;
import com.test.ex1_hello_jpa.domain.Member;
import com.test.ex1_hello_jpa.domain.Movie;
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

            Movie movie = new Movie();

            movie.setDirector("A감독");
            movie.setActor("B배우");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(122220);

            em.persist(movie);
            //영속성 컨텍스트 삭제
            em.flush();
            em.clear();


            Movie movie1 = em.find(Movie.class, movie.getId());

            System.out.println("movie1 = " + movie1);


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
