/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author KingJ
 */
public class UserManager {
    private static UserManager instance;
    private int userId;
    private String username;
    
    private UserManager(){
        
    }
    
    public static synchronized UserManager getInstance(){
        if(instance == null) {
            instance = new UserManager();
    }
        return instance;
    }
    
    public void setUserIId(int userId) {
        this.userId = userId;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void clearUser() {
        userId = 0;
    }
}
