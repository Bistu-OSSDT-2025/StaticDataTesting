package com.generator;

import java.io.IOException;

public interface TestDataGenerator {
    void generateTestData(String outputFile, int recordCount) throws IOException;
}    