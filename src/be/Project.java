package be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project {

    private int id;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty dateLastVisited = new SimpleStringProperty();
    private StringProperty customerName = new SimpleStringProperty();
    private StringProperty companyAddress = new SimpleStringProperty();
    private IntegerProperty zipCode = new SimpleIntegerProperty();
    private int companyType;

    private int phoneNumber;
    private String customerEmail;


    public Project(int id, String name, String dateLastChecked, String customerName, String companyAddress, int zipCode, int companyType, int phoneNumber, String customerEmail){
        setId(id);
        setName(name);
        setDateLastVisited(dateLastChecked);
        setCustomerName(customerName);
        setZipCode(zipCode);
        setCompanyAddress(companyAddress);
        setCompanyType(companyType);
        setPhoneNumber(phoneNumber);
        setCustomerEmail(customerEmail);
    }

    public Project(String name, String dateLastChecked, String customerName, String companyAddress, int zipCode, int companyType, int phoneNumber, String customerEmail){
        setName(name);
        setDateLastVisited(dateLastChecked);
        setCustomerName(customerName);
        setCompanyAddress(companyAddress);
        setZipCode(zipCode);
        setCompanyType(companyType);
        setPhoneNumber(phoneNumber);
        setCustomerEmail(customerEmail);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return companyType;
    }

    public void setCompanyType(int companyType) {
        this.companyType=companyType;
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

    private void setPhoneNumber(int phoneNumber) {
        this.phoneNumber=phoneNumber;
    }

    private void setCustomerEmail(String customerEmail) {
        this.customerEmail=customerEmail;
    }

    public int getPhoneNumber(){
        return phoneNumber;
    }

    public String getCustomerEmail(){
        return customerEmail;
    }
}

