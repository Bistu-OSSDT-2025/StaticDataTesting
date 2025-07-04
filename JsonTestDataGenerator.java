package com.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTestDataGenerator implements TestDataGenerator {
    private static final String[] FIRST_NAMES = {"John", "Jane", "Michael", "Sarah", "Robert", "Jennifer"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis"};
    private static final String[] EMAIL_DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};

    @Override
    public void generateTestData(String outputFile, int recordCount) throws IOException {
        List<Map<String, Object>> records = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= recordCount; i++) {
            Map<String, Object> record = new HashMap<>();
            record.put("id", i);
            record.put("firstName", FIRST_NAMES[random.nextInt(FIRST_NAMES.length)]);
            record.put("lastName", LAST_NAMES[random.nextInt(LAST_NAMES.length)]);
            record.put("email", generateEmail(record.get("firstName").toString(), record.get("lastName").toString(), random));
            record.put("age", random.nextInt(50) + 18);
            records.add(record);
        }

        JSONArray jsonArray = new JSONArray(records);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(jsonArray.toString(2));
        }
    }

    private String generateEmail(String firstName, String lastName, Random random) {
        String base = firstName.toLowerCase() + "." + lastName.toLowerCase();
        if (random.nextBoolean()) {
            base += random.nextInt(100);
        }
        return base + "@" + EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)];
    }
}    