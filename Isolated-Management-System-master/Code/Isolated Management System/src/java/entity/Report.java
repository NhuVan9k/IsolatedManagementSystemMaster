/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Report {

    private int id_report;
    private String content;
    private Timestamp createDate;
    private Patient patient;

    public Report() {
    }

    public Report(int id_report, String content, Timestamp createDate, Patient patient) {
        this.id_report = id_report;
        this.content = content;
        this.createDate = createDate;
        this.patient = patient;
    }

    public int getId_report() {
        return id_report;
    }

    public void setId_report(int id_report) {
        this.id_report = id_report;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Report{" + "id_report=" + id_report + ", content=" + content + ", createDate=" + createDate + ", patient=" + patient + '}';
    }
}
