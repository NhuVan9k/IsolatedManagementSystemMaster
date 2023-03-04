/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
public class TestResult{

    private Integer testResultId;
   
    private String testName;
   
    private String result;
   
    private Date createDate;
  
    private Patient patient;

    public TestResult() {
    }

    public TestResult(Integer testResultId) {
        this.testResultId = testResultId;
    }

    public TestResult(String testType, String result, Date testTime, Patient patient) {
        this.testName = testType;
        this.result = result;
        this.createDate = testTime;
        this.patient = patient;
    }

    public Integer getTestResultId() {
        return testResultId;
    }

    public void setTestResultId(Integer testResultId) {
        this.testResultId = testResultId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
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
        return "KqXetNghiem{" + "testResultId=" + testResultId + ", testType=" + testName + ", result=" + result + ", testTime=" + createDate + ", ngCachLy=" + patient + '}';
    }
}
