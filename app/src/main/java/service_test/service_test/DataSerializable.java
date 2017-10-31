package service_test.service_test;

import java.io.Serializable;


public class DataSerializable implements Serializable {
    private int id;
    private String name;

    public DataSerializable(int _id, String _name){
        this.id = _id;
        this.name = _name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


