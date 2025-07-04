package com.generator;

public enum TestDataFormat {
    CSV, JSON, XML;

    public String getFileExtension() {
        return name().toLowerCase();
    }
}    