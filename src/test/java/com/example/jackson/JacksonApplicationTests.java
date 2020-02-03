package com.example.jackson;

import com.example.jackson.pojos.Car;
import com.example.jackson.pojos.Transaction;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest
class JacksonApplicationTests {
    private static String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
    private static String jsonStr = "{\"brand\":\"AUDI\",\"doors\":2}";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getPathTraining() {
        System.out.println(System.getProperty("user.dir"));//C:\Users\stguy\Desktop\jackson
        System.out.println(this.getClass().getResource(""));//file:/C:/Users/stguy/Desktop/jackson/target/test-classes/com/example/jackson/
        System.out.println(this.getClass().getClassLoader().getResource(""));//file:/C:/Users/stguy/Desktop/jackson/target/test-classes/
        System.out.println(this.getClass().getClassLoader().getResource("data/car.json"));//file:/C:/Users/stguy/Desktop/jackson/target/classes/data/car.json
    }

    @Test
    void contextLoads() throws IOException {
        Car car = objectMapper.readValue(jsonStr, Car.class);
        System.out.println(car.toString());

    }

    @Test
    void readObjectFromJsonFile() throws IOException {
        File file = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("data/car.json")).getPath());

        Car car = objectMapper.readValue(file, Car.class);
        System.out.println(car.toString());
    }

    @Test
    void readObjectFromJsonViaURL() throws IOException {
        URL url = new URL(Objects.requireNonNull(this.getClass().getClassLoader().getResource("data/car.json")).toString());
        Car car = objectMapper.readValue(url, Car.class);
        System.out.println(car.toString());
    }

    @Test
    void readObjectArrayFromJsonArrayString() throws IOException {
        Car[] cars = objectMapper.readValue(jsonArray, Car[].class);
        System.out.println(Arrays.toString(cars));
    }

    @Test
    void readObjectListFromJsonArrayString() throws IOException {
        List<Car> carList = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>() {
        });
        System.out.println(carList.toString());
    }

    @Test
    void readMapFromJsonString() throws IOException {
        Map<String, Objects> jsonMap = objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(jsonMap.toString());
    }

    @Test
    void writeJsonFromObject() throws IOException {
        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(4);
//        objectMapper.writeValue(new FileOutputStream("output-2.json"),car);
        String json = objectMapper.writeValueAsString(car);
        byte[] b=objectMapper.writeValueAsBytes(car);
        System.out.println(json);
        System.out.println(Arrays.toString(b));
    }

    @Test
    void jacksonDateFormats() throws JsonProcessingException {
        Transaction transaction  = new Transaction("transfer", new Date());

        System.out.println(objectMapper.writeValueAsString(transaction));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(simpleDateFormat);
        System.out.println(objectMapper.writeValueAsString(transaction));
    }
}
