package com.root.generators;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class EmergencyCodeGenerator {

    // Character set: a-z, A-Z, 0-9, and _
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
    private static final int STRING_LENGTH = 6;
    private static final int NUMBER_OF_STRINGS = 3;
    private static final Random random = new Random();

    // This method will generate and return 3 unique random strings
    public List<String> generateUniqueRandomStrings() {
        Set<String> uniqueStrings = new HashSet<>();
        
        while (uniqueStrings.size() < NUMBER_OF_STRINGS) {
            String randomString = generateRandomString();
            uniqueStrings.add(randomString); // Set ensures uniqueness
        }

        return new ArrayList<>(uniqueStrings);
    }

    // Private method to generate a single random string of length 6
    private String generateRandomString() {
        StringBuilder sb = new StringBuilder(STRING_LENGTH);

        for (int i = 0; i < STRING_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
}