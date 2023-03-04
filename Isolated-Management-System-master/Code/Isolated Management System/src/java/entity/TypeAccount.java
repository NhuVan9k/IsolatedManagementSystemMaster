
package entity;

public class TypeAccount  {

  
    private Integer accountTypeId;
    
    private String accountTypeName;

    public TypeAccount() {
    }

    public TypeAccount(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    @Override
    public String toString() {
        return "entity.AccountType[ accountTypeId=" + accountTypeId + " ]";
    }
    
}
