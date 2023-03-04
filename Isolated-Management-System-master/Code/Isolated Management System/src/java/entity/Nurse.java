/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author hoang
 */
public class Nurse {

    private int id_nurse;
    private String name_nurse;
    private int phone;
    private String fullName;
    private int id_account;
    private String address;
    private int id_area;

    public Nurse() {
    }

    public Nurse(int id_nurse, String name_nurse, int phone, String fullName, int id_account, String address, int id_area) {
        this.id_nurse = id_nurse;
        this.name_nurse = name_nurse;
        this.phone = phone;
        this.fullName = fullName;
        this.id_account = id_account;
        this.address = address;
        this.id_area = id_area;
    }

    public int getId_nurse() {
        return id_nurse;
    }

    public void setId_nurse(int id_nurse) {
        this.id_nurse = id_nurse;
    }

    public String getName_nurse() {
        return name_nurse;
    }

    public void setName_nurse(String name_nurse) {
        this.name_nurse = name_nurse;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId_area() {
        return id_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    @Override
    public String toString() {
        String result = "Nurse ID = " + id_nurse + "\n"
                + "Nurse Name = " + name_nurse + "\n"
                + "Nurse Phone = " + phone + "\n"
                + "Nurse Fullname = " + fullName + "\n"
                + "Nurse Account_Id = " + id_account + "\n"
                + "Nurse Address = " + address + "\n"
                + "Nurse Area_Id = " + id_area + "\n";
        return result;
    }

}
