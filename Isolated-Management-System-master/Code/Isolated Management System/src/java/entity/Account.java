package entity;

import java.util.Collection;

public class Account {

    private Integer accountId;

    private String userName;

    private String password;

    private String avatar;

    private String email;

    private TypeAccount type;

    private Patient patient; // unrealistic

    public Account() {
    }

    public Account(Integer accountId, String userName, String password, String avatar, String email, TypeAccount type, Patient patient) {
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.type = type;
        this.patient = patient;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TypeAccount getType() {
        return type;
    }

    public void setType(TypeAccount type) {
        this.type = type;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    
    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", userName=" + userName + ", password=" + password + ", avatar=" + avatar + ", email=" + email + ", type=" + type + ", patient=" + patient + '}';
    }

}
