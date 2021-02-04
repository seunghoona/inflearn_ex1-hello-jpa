package com.test.ex1_hello_jpa;

import com.test.ex1_hello_jpa.domain.Adress;

public class valueMain {


    public static void main(String[] args) {


        int a = 10;
        int b = 10;
        System.out.println("(a==b) = " + (a==b));


        Adress build = Adress.builder().city("서울").zipcode("1234").street("허준로").build();
        Adress build1 = Adress.builder().city("서울").zipcode("1234").street("허준로").build();
        System.out.println("build1==build = " + (build1.equals(build)));

    }
}
