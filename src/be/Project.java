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
    private StringProperty companyName = new SimpleStringProperty();
    private IntegerProperty companyType = new SimpleIntegerProperty();

    public Project(int id, String name, String dateLastChecked, String customerName, String companyName, int companyType){
        setId(id);
        setName(name);
        setDateLastVisited(dateLastChecked);
        setCustomerName(customerName);
        setCompanyName(companyName);
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

    public String dateLastVisited(){
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

    public String getCompanyName() {
        return companyName.get();
    }


    public StringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName.set(companyName);
    }

    public int getCompanyType(){
        return companyType.get();
    }

    public void setCompanyType(int companyType) {
        this.companyType.set(companyType);
    }
}

