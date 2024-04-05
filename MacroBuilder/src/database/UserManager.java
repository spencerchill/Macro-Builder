/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 * Singleton class that holds userId
 * @author KingJ
 */
public class UserManager {
    private static UserManager instance;
    private int userId;
    private String username;
    
    private UserManager(){
    }
    
    /**
     *  Returns instance of UserManager.
     * Creates one if instance is null.
     * @return instance.
     */
    public static synchronized UserManager getInstance(){
        if(instance == null) {
            instance = new UserManager();
    }
        return instance;
    }
    
    /**
     * Sets userId.
     * @param userId 
     */
    public void setUserIId(int userId) {
        this.userId = userId;
    }
    /**
     * @return userId.
     */
    public int getUserId(){
        return userId;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    /**
     * @return username.
     */
    public String getUsername(){
        return username;
    }
    /**
     * Sets userId to 0;
     * Sets username to null;
     */
    public void clearUser() {
        userId = 0;
        username = null; 
    }
}
