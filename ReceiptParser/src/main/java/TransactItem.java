import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class TransactItem {

    private String accountType;
    private String numberType;
    private String currency;
    private Date dateOfOperation;
    private String transactionReference;
    private String nameCompany;
    private Double income;
    private Double expenses;

    public TransactItem(String accountType, String numberType, String currency,
                        Date dateOfOperation, String transactionReference,
                        String nameCompany, Double income, Double expenses) {
        this.accountType = accountType;
        this.numberType = numberType;
        this.currency = currency;
        this.dateOfOperation = dateOfOperation;
        this.transactionReference = transactionReference;
        this.nameCompany = nameCompany;
        this.income = income;
        this.expenses = expenses;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }
}

