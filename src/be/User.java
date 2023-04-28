package be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private IntegerProperty id =  new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty type = new SimpleIntegerProperty();

    public User(int id, String name, int type){
        setId(id);
        setName(name);
        setType(type);
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

    public int getType() {
        return type.get();
    }

    public void setType(int type){
        this.type.set(type);
    }
}
