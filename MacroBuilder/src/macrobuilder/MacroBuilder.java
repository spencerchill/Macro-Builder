/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package macrobuilder;

/**
 *
 * @author spencerhill
 */
public class MacroBuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Java Fx and Helper Methods in this class
        User user = new User("jayden", "jaydenpassword", User.Gender.Male, 19, (float) 90.7185, (float) 177.8, User.ActivityLevel.ACTIVE, User.CurrentMode.CUT);
        
        System.out.println("Calorie Goal - " + user.getDay().getCalorieGoal());
        System.out.println("Protein Goal - " + user.getDay().getProteinGoal());
        System.out.println("Cals - " + user.getDay().getCalories() + " Fat - " + user.getDay().getFat() + 
                " Carbs - " + user.getDay().getCarbs() + " Protrein - " + user.getDay().getProtein());
        
        //Eating some kit kats
        user.getDay().intake(140, 7, 19, 1);
        
        System.out.println("Calorie Goal - " + user.getDay().getCalorieGoal());
        System.out.println("Protein Goal - " + user.getDay().getProteinGoal());
        System.out.println("Cals - " + user.getDay().getCalories() + " Fat - " + user.getDay().getFat() + 
                " Carbs - " + user.getDay().getCarbs() + " Protrein - " + user.getDay().getProtein());
                 
    }
    
}