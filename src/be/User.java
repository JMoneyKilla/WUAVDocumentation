package be;

import be.enums.UserType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private IntegerProperty id =  new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private UserType type;

    public User(int id, String name, int userType){
        setId(id);
        setName(name);
        switch (userType) {
            case 1 -> this.type = UserType.PROJECT_MANAGER;
            case 2 -> this.type = UserType.TECHNICIAN;
            case 3 -> this.type = UserType.SALES_PERSON;
        }
    }

    public User(String name)
    {
        setName(name);
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

    public void setName(String name) {
        this.name.set(name);
    }

    public UserType getType() {
        return type;
    }

}
