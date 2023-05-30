package be.enums;

public enum UserType {
    TECHNICIAN(2),
    SALES_PERSON(3),
    PROJECT_MANAGER(1);

    final int typeId;
    UserType(int typeId){
        this.typeId = typeId;
    }

    public int getTypeId(){
        return typeId;
    }
}
