/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ACER
 */
public class Data 
{
    private String username;
    private int score;
    private int standing;
    
    public Data()
    {
        
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    
     public void setScore(int score)
    {
        this.score = score;
    }
    
    public int getScore()
    {
        return this.score;
    }
    
     public void setStanding(int standing)
    {
        this.standing = standing;
    }
    
    public int getStanding()
    {
        return this.standing;
    }
}
