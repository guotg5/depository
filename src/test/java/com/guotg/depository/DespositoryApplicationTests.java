package com.guotg.depository;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles
@SpringBootTest(classes = DepositoryApplication.class)
public class DespositoryApplicationTests {
 
    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }
 
    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}