package com.example.jackson;

import com.example.jackson.pojos.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class NormalTest {
    public static String getCar() {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson = "{\"brand\":\"GengTao Wu\",\"doors\":4}";
        Car car = null;
        try {
            car = objectMapper.readValue(carJson, Car.class);

            System.out.println("car's brand = " + car.getBrand());
            System.out.println("car's doors = " + car.getDoors());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert car != null;
        return car.toString();
    }
}
