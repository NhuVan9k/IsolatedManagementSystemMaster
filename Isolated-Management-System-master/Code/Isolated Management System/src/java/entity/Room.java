/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Room  {
    private Integer roomId;
 
    private String roomName;
    
    private int bedNumber;
    
    private String note;
   
    private Area area;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    
    public Room() {
    }

    public Room(Integer roomId, String roomName, int bedNumber, String note, Area area) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.bedNumber = bedNumber;
        this.note = note;
        this.area = area;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Room{" + "roomId=" + roomId + ", roomName=" + roomName + ", bedNumber=" + bedNumber + ", note=" + note + ", area=" + area + '}';
    }

}
