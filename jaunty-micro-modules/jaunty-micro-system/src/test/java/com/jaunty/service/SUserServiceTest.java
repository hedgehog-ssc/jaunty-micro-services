package com.jaunty.service;

import com.jaunty.JauntyMicroSystemApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JauntyMicroSystemApplication.class)
public class SUserServiceTest {

    @Autowired(required = false)
    private SUserService userService;

    @Test
    public void getById() {
        System.out.println(userService.getById(1L));
    }
}