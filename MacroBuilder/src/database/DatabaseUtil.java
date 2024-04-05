/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Class that handles database connection and password hashing.
 * @author KingJ
 */
public class DatabaseUtil {

    private static String url;
    private static String username;
    private static String password;

    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    /**
     * Reads database credentials from config file.
     * @throws IOException if error reading file.
     */
    public DatabaseUtil() throws IOException {
         //secure database credentials.
        ConfigReader configReader = new ConfigReader("config.properties");
        url = configReader.getUrl();
        username = configReader.getUsername();
        password = configReader.getPassword();
    }
    /**
     * Gets connection to server.
     * @return Connection object.
     * @throws SQLException.
     */
    public  Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Hashes password with salt using PBKDF2 algorithm with HMAC SHA-256.
     * @param password
     * @param salt
     * @return hashed password
     */
    public String hashPassword(String password, String salt) {
        // Expects array of characters and bytes
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = Base64.getDecoder().decode(salt);

        KeySpec spec = new PBEKeySpec(passwordChars, saltBytes, ITERATIONS, KEY_LENGTH);
        try {

            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generates random salt.
     * @return random salt.
     */
    public String generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
