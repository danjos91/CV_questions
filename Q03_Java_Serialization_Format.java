/**
 * Question 03: Java Serialization Format
 * 
 * Russian: "Какой из следующих форматов данных используется для стандартной 
 *           сериализации объектов в Java?"
 * English: "Which of the following data formats is used for standard object 
 *           serialization in Java?"
 * 
 * Options:
 * 1. Binary format
 * 2. JSON format
 * 3. BASE64-encoded text format
 * 4. .class file format
 * 5. XML format
 * 
 * ANSWER: Binary format
 * 
 * EXPLANATION:
 * - Java's default serialization uses a binary format
 * - The format is proprietary to Java (not human-readable)
 * - It includes metadata about classes, fields, and object graph
 * - JSON, XML, BASE64 are text-based formats - not used by default serialization
 * - .class files are bytecode format, not serialization format
 * - Custom serialization can use JSON/XML (Jackson, Gson, JAXB), but that's not "standard"
 */

import java.io.*;

public class Q03_Java_Serialization_Format {
    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Java Standard Serialization Format ===\n");
        
        Person person = new Person("John Doe", 30);
        
        try {
            // Serialize to byte array to show binary format
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(person);
            oos.close();
            
            byte[] serializedData = baos.toByteArray();
            
            System.out.println("Serialized object size: " + serializedData.length + " bytes");
            System.out.println("Format: BINARY (not human-readable)");
            System.out.println("\nFirst 20 bytes (hex):");
            for (int i = 0; i < Math.min(20, serializedData.length); i++) {
                System.out.printf("%02x ", serializedData[i]);
            }
            System.out.println("\n");
            
            // Deserialize
            ByteArrayInputStream bais = new ByteArrayInputStream(serializedData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Person deserialized = (Person) ois.readObject();
            ois.close();
            
            System.out.println("Deserialized: " + deserialized);
            
            System.out.println("\n=== Key Points ===");
            System.out.println("1. Java uses BINARY format for standard serialization");
            System.out.println("2. Format includes: magic number (AC ED), version, class metadata");
            System.out.println("3. Not human-readable (unlike JSON/XML)");
            System.out.println("4. Platform-independent but Java-specific");
            System.out.println("5. Custom formats (JSON/XML) require libraries like Jackson/Gson");
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
