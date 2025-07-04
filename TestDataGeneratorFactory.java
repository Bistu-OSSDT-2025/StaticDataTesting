package com.generator;

import java.io.IOException;

public class TestDataGeneratorFactory {
    public static TestDataGenerator createGenerator(TestDataFormat format) {
        switch (format) {
            case CSV:
                return new CsvTestDataGenerator();
            case JSON:
                return new JsonTestDataGenerator();
            case XML:
                return new XmlTestDataGenerator();
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
}    