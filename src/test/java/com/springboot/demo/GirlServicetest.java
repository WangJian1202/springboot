package com.springboot.demo;

import com.springboot.domain.Girl;
import com.springboot.service.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServicetest {
    @Autowired
    private GirlService girlService;
    @Test
    @Transactional
    public void findOneTest(){
        Girl one = girlService.findOne(4);
        Assert.assertEquals(new Integer(20),one.getAge());
    }
}
