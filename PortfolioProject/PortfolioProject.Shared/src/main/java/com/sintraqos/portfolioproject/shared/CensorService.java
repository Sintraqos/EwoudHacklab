package com.sintraqos.portfolioproject.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@Service
public class CensorService {
    private final Logger logger;
    private List<String> bannedWords;

    @Autowired
    public CensorService(Logger logger) {
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
        this.logger = logger;
    }

    /**
     * Check if the string contains a banned word, replace that with '*'
     *
     * @param inputString the input string
     * @return the string with censorship
     */
    public String validateString(String inputString) {
        // Replace every banned word inside the given string
        return bannedWords.stream().reduce(inputString, this::censorWord);
    }

    /**
     * Helper method to replace the banned part with asterisks
     * IE: The word 'ass' is banned, so it will be displayed as a '***'.
     *
     * @param input      the input string
     * @param bannedWord the banned word
     */
    private String censorWord(String input, String bannedWord) {
        // Check if the input contains the banned word
        if (containsBannedWord(input)) {
            // If it does, replace the banned word with asterisks
            return input.replaceAll("(?i)" + Pattern.quote(bannedWord), "*".repeat(bannedWord.length()));
        }

        return input;
    }

    /**
     * Check if the banned word list contains the given string
     *
     * @param string the input string
     */
    public boolean containsBannedWord(String string) {
        return bannedWords.stream()
                .anyMatch(bannedWord -> string.toLowerCase().contains(bannedWord.toLowerCase().trim()));
    }
}
