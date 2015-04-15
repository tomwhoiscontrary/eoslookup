package io.pivotal.labs.eoslookup;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Camera {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    public Camera(String name) {
        this.name = name;
    }

    protected Camera() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
