package com.generator;

public class TestDataApp {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java TestDataApp <format> <outputFile> <recordCount>");
            System.out.println("Supported formats: CSV, JSON, XML");
            return;
        }

        try {
            TestDataFormat format = TestDataFormat.valueOf(args[0].toUpperCase());
            String outputFile = args[1];
            int recordCount = Integer.parseInt(args[2]);

            TestDataGenerator generator = TestDataGeneratorFactory.createGenerator(format);
            generator.generateTestData(outputFile, recordCount);

            System.out.println("Successfully generated " + recordCount + " records in " + format + " format to " + outputFile);
        } catch (IllegalArgumentException e) {
            System.err.println("Unsupported format. Please use CSV, JSON, or XML.");
        } catch (NumberFormatException e) {
            System.err.println("Invalid record count. Please provide a valid integer.");
        } catch (Exception e) {
            System.err.println("Error generating test data: " + e.getMessage());
        }
    }
}    