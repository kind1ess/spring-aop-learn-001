package com.kind1ess.test;


import com.kind1ess.aspect.ServiceAspect;
import com.kind1ess.config.AccountConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AccountConfig.class)
public class AspectTest {

    @Autowired
    private ServiceAspect serviceAspect;

    @Test
    public void testAspect(){
        System.out.println(serviceAspect);
    }
}
