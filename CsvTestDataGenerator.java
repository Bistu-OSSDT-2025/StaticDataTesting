package com.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CsvTestDataGenerator implements TestDataGenerator {
    private static final String[] FIRST_NAMES = {"John", "Jane", "Michael", "Sarah", "Robert", "Jennifer"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis"};
    private static final String[] EMAIL_DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};

    @Override
    public void generateTestData(String outputFile, int recordCount) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("ID,FirstName,LastName,Email,Age\n");

            Random random = new Random();
            for (int i = 1; i <= recordCount; i++) {
                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                String email = generateEmail(firstName, lastName, random);
                int age = random.nextInt(50) + 18;

                writer.write(String.format("%d,%s,%s,%s,%d\n", i, firstName, lastName, email, age));
            }
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