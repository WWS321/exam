package com.example.jackson;

import com.example.jackson.pojos.Car;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@SpringBootTest
class JacksonApplicationTests {

    @Test
    void getPathTraining() {
        System.out.println(System.getProperty("user.dir"));//C:\Users\stguy\Desktop\jackson
        System.out.println(this.getClass().getResource(""));//file:/C:/Users/stguy/Desktop/jackson/target/test-classes/com/example/jackson/
        System.out.println(this.getClass().getClassLoader().getResource(""));//file:/C:/Users/stguy/Desktop/jackson/target/test-classes/
        System.out.println(this.getClass().getClassLoader().getResource("data/car.json"));//file:/C:/Users/stguy/Desktop/jackson/target/classes/data/car.json
    }

    @Test
    void contextLoads() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "{\"brand\":\"AUDI\",\"doors\":2}";

        Car car = objectMapper.readValue(jsonStr, Car.class);
        System.out.println(car.toString());

    }

    @Test
    void readObjectFromJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("data/car.json")).getPath());

        Car car = objectMapper.readValue(file, Car.class);
        System.out.println(car.toString());
    }
}
