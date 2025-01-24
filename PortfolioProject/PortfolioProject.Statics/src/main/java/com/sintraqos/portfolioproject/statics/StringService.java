package com.sintraqos.portfolioproject.statics;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class StringService {
    @Getter
    private List<String> bannedWords;

    @Autowired
    public StringService(Logger logger) {
        // Load the banned words from JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Assuming the file is placed inside the resources folder
            File jsonFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("bannedWords.json")).getFile());
            BannedWords bannedWordsData = objectMapper.readValue(jsonFile, BannedWords.class);
            this.bannedWords = bannedWordsData.getBannedWords();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public String validateString(String inputString) {
        StringBuilder returnString = new StringBuilder();

        // Split the input string by spaces
        for (String string : inputString.split(" ")) {
            String stringPart = string;

            // Check if the word has a banned word, if it does, loop through the list to get the exact word
            if (isBannedWord(string)) {
                // Check if the word contains any banned word and replace only the banned part
                stringPart = bannedWords.stream().reduce(string, this::censorWord);
            }

            returnString.append(stringPart).append(" ");
        }

        return returnString.toString().trim();
    }

    // Helper method to replace the banned part with asterisks
    private String censorWord(String word, String bannedWord) {
        // Check if the word contains the banned word using a case-insensitive search
        int index = word.toLowerCase().indexOf(bannedWord.toLowerCase());

        if (index != -1) {
            // Replace the banned part of the word with asterisks
            StringBuilder modifiedWord = new StringBuilder(word);
            for (int i = index; i < index + bannedWord.length(); i++) {
                modifiedWord.setCharAt(i, '*');
            }
            return modifiedWord.toString();
        }
        return word;
    }

    public boolean isBannedWord(String string) {
        return bannedWords.stream()
                .anyMatch(bannedWord -> string.toLowerCase().contains(bannedWord.toLowerCase()));
    }
}
