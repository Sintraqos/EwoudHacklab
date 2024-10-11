package com.sintraqos.portfolioproject.Repositories;

import com.sintraqos.portfolioproject.Entities.AccountEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountRepositoryTest {

//    @Autowired
//    private AccountRepositoryTest userRepository;

    @Before
    public void setUp() throws Exception {
        AccountEntity user1 = new AccountEntity("Alice", "alice@mail", "password");
        AccountEntity user2 = new AccountEntity("Bob", "bob@mail", "password");
    }

    @Test
    public void testFetchData() {
//        /*Test data retrieval*/
//        AccountEntity userA = userRepository.findByName("Bob");
//        assertNotNull(userA);
//        assertEquals(38, userA.getAge());
//        /*Get all products, list should only have two*/
//        Iterable<User> users = userRepository.findAll();
//        int count = 0;
//        for (User p : users) {
//            count++;
//        }
//        assertEquals(count, 2);
    }
}
