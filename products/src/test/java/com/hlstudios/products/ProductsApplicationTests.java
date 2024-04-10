package com.hlstudios.products;

import com.hlstudios.products.utils.HashUUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Random;
import java.util.UUID;

@SpringBootTest
class ProductsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void parseIdToUUID(){
        Random random = new Random();
        Integer randomId = random.nextInt();
        UUID uuid1 = HashUUID.v5(randomId.toString());
        UUID uuid2 = HashUUID.v5(randomId.toString());

        Assert.isTrue(uuid1.toString().equals(uuid2.toString()), "is equals");
    }
}
