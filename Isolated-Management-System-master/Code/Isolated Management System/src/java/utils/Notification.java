/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


public class Notification {
    private String tittle;
    private String message;
    private String type;
    private int duration = 5000;

    public Notification() {
    }

    public Notification(String tittle, String message, String type) {
        this.tittle = tittle;
        this.message = message;
        this.type = type;
    }

    
    public Notification(String tittle, String message, String type, int duration) {
        this.tittle = tittle;
        this.message = message;
        this.type = type;
        this.duration = duration;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    
}
