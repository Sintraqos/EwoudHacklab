package com.sintraqos.portfolioproject.Statics;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrypter {

    static String baseEncryptKey = "Shindu Fallah Nah"; // Just a pun, it comes from a song from World of Warcraft, meaning "The enemies are breaking through"

    /**
     * Create a new Time object with specified times
     * @param passwordInput the password that was given
     * @param passwordHash the hash stored inside the database
     * @param passwordSalt the salt stored inside the database
     */
    public static boolean verifyPassword(String passwordInput, String passwordHash, String passwordSalt) {
        // Create a new hash from the inputted password, and the existing salt
        String hashedInput = encryptString(passwordInput, passwordSalt);
        return hashedInput.equals(passwordHash);
    }

    /**
     * Create a new password hash and salt, using this way each account has a unique salt stored
     * @param password the password that was given
     * @return a String[] that contains the passwordHash and the passwordSalt
     */
    public static String[] encryptPassword(String password) {
        // Create a new salt based on the password and the base encryption key
        String salt = encryptString(password, baseEncryptKey);
        // Then generate the hash based on the salt we just created
        String hash = encryptString(password, salt);
        return new String[]{salt, hash};
    }

    /**
     * Create a new password hash and salt, using this way each account has a unique salt stored
     * @param input the String to encrypt
     * @param salt the salt to encrypt the String to
     * @return an encrypted string based on the input and salt given
     */
    static String encryptString(String input, String salt) {
        try {
            // Concatenate the input and salt
            String saltedInput = input + salt;

            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(saltedInput.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encrypting string", e);
        }
    }
}