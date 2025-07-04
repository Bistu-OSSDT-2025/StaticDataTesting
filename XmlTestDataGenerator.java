package com.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlTestDataGenerator implements TestDataGenerator {
    private static final String[] FIRST_NAMES = {"John", "Jane", "Michael", "Sarah", "Robert", "Jennifer"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis"};
    private static final String[] EMAIL_DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};

    @Override
    public void generateTestData(String outputFile, int recordCount) throws IOException {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("records");
            doc.appendChild(rootElement);

            Random random = new Random();
            for (int i = 1; i <= recordCount; i++) {
                Element record = doc.createElement("record");
                rootElement.appendChild(record);

                Element id = doc.createElement("id");
                id.setTextContent(String.valueOf(i));
                record.appendChild(id);

                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                Element firstNameElement = doc.createElement("firstName");
                firstNameElement.setTextContent(firstName);
                record.appendChild(firstNameElement);

                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
                Element lastNameElement = doc.createElement("lastName");
                lastNameElement.setTextContent(lastName);
                record.appendChild(lastNameElement);

                Element email = doc.createElement("email");
                email.setTextContent(generateEmail(firstName, lastName, random));
                record.appendChild(email);

                Element age = doc.createElement("age");
                age.setTextContent(String.valueOf(random.nextInt(50) + 18));
                record.appendChild(age);
            }

            // Write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                StreamResult result = new StreamResult(writer);
                transformer.transform(source, result);
            }
        } catch (ParserConfigurationException | TransformerException e) {
            throw new IOException("Error generating XML data", e);
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