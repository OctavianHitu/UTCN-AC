package model;

import java.io.Serializable;

public abstract class Menu implements Serializable {

    public String name;

    public String getname() {
        return name;
    }

    public void setname(String nm) {
        this.name = nm;
    }


    protected abstract float priceadd();
}
