package com.jun.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void test(){
        String username = null;
        if(username.equals("")){
            System.out.println("hfa");
        }
    }

}
