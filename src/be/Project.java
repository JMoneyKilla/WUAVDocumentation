package be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty dateLastVisited = new SimpleStringProperty();
    private StringProperty customerName = new SimpleStringProperty();
    private StringProperty companyAddress = new SimpleStringProperty();
    private IntegerProperty zipCode = new SimpleIntegerProperty();
    private IntegerProperty companyType = new SimpleIntegerProperty();

    public Project(int id, String name, String dateLastChecked, String customerName, String companyAddress, int zipCode, int companyType){
        setId(id);
        setName(name);
        setDateLastVisited(dateLastChecked);
        setCustomerName(customerName);
        setZipCode(zipCode);
        setCompanyAddress(companyAddress);
        setCompanyType(companyType);
    }

    public Project(String name, String dateLastChecked, String customerName, String companyAddress, int zipCode, int companyType){
        setName(name);
        setDateLastVisited(dateLastChecked);
        setCustomerName(customerName);
        setCompanyAddress(companyAddress);
        setZipCode(zipCode);
        setCompanyType(companyType);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }


    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name){
        this.name.set(name);
    }

    public StringProperty dateLastVisitedProperty(){
        return dateLastVisited;
    }

    public String getDateLastVisited(){
        return dateLastVisited.get();
    }
    public void setDateLastVisited(String dateLastVisited) {
        this.dateLastVisited.set(dateLastVisited);
    }

    public String getCustomerName() {
        return customerName.get();
    }


    public StringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName){
        this.customerName.set(customerName);
    }

    public String getCompanyAddress() {
        return companyAddress.get();
    }


    public StringProperty companyAddressProperty() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyName){
        this.companyAddress.set(companyName);
    }

    public int getCompanyType(){
        return companyType.get();
    }

    public void setCompanyType(int companyType) {
        this.companyType.set(companyType);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getZipCode() {
        return zipCode.get();
    }

    public IntegerProperty zipCodeProperty() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode.set(zipCode);
    }
}

