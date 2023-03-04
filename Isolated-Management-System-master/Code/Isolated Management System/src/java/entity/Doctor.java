/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class Doctor {
    int doctor;
    String doctorName;
    int phone;
    String fullName;
    int idAccount;
    String Address;

    public Doctor() {
    }

    public Doctor(int doctor, String doctorName, int phone, String fullName, int idAccount, String Address) {
        this.doctor = doctor;
        this.doctorName = doctorName;
        this.phone = phone;
        this.fullName = fullName;
        this.idAccount = idAccount;
        this.Address = Address;
    }

    public int getDoctor() {
        return doctor;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @Override
    public String toString() {
        return "Doctor{" + "doctor=" + doctor + ", doctorName=" + doctorName + ", phone=" + phone + ", fullName=" + fullName + ", idAccount=" + idAccount + ", Address=" + Address + '}';
    }
    
    
}
