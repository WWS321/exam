package com.example.jackson;

import com.example.jackson.pojos.Car;
import com.example.jackson.pojos.Employee;
import com.example.jackson.pojos.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class JacksonApplicationTests {
    private static String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
    private static String jsonStr =
            "{ \"brand\" : \"Mercedes\", \"doors\" : 4," +
                    "  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," +
                    "  \"nestedObject\" : { \"field\" : \"value\" } }";
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
        byte[] b = objectMapper.writeValueAsBytes(car);
        System.out.println(json);
        System.out.println(Arrays.toString(b));
    }

    @Test
    void jacksonDateFormats() throws JsonProcessingException {
        Transaction transaction = new Transaction("transfer", new Date());

        System.out.println(objectMapper.writeValueAsString(transaction));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(simpleDateFormat);
        System.out.println(objectMapper.writeValueAsString(transaction));
    }

    @Test
    void jacksonTreeModelExample() {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonStr);
            System.out.println(jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void jacksonJsonNodeExample() {
        try {
            JsonNode jsonNode = objectMapper.readValue(jsonStr, JsonNode.class);

            JsonNode brandNode = jsonNode.get("brand");
            String brand = brandNode.asText();
            System.out.println("brand = " + brand);

            JsonNode doorsNode = jsonNode.get("doors");
            int doors = doorsNode.asInt();
            System.out.println("doors = " + doors);

            JsonNode array = jsonNode.get("owners");
            JsonNode jsonNode1 = array.get(0);
            String john = jsonNode1.asText();
            System.out.println("john = " + john);

            JsonNode child = jsonNode.get("nestedObject");
            JsonNode childField = child.get("field");
            String field = childField.asText();
            System.out.println("field = " + field);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void convertObjectToJsonNode() {
        Car car = new Car("Cadillac", 4);
        JsonNode carJsonNode = objectMapper.valueToTree(car);
        System.out.println(carJsonNode);
    }

    @Test
    void convertJsonNodeToObject() {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        try {
            JsonNode carJsonNode = objectMapper.readTree(carJson);
            Car car = objectMapper.treeToValue(carJsonNode, Car.class);
            System.out.println(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readAndWriteYAMLWithObjectMapper() {
        ObjectMapper objectMapper1 = new ObjectMapper(new YAMLFactory());
        Employee employee = new Employee("John Doe", "john@doe.com");

        String yamlString = null;
        try {
            yamlString = objectMapper1.writeValueAsString(employee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(yamlString);

        Employee employee2 = null;
        try {
            employee2 = objectMapper1.readValue(yamlString, Employee.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(employee2);
    }
}
