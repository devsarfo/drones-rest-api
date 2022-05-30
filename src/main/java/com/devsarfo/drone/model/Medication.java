package com.devsarfo.drone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Table(name = "medications")
@Entity
public class Medication
{
    public Medication()
    {

    }

    public Medication(String name, double weight, String code, String image)
    {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_-]*$",message="Allowed only letters, numbers, hyphen and underscore")
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false, scale = 2)
    private double weight;

    @Id
    @Pattern(regexp="^[A-Z0-9_]*$",message="Allowed only upper case letters, underscore and numbers")
    @Column(nullable = false)
    private String code;

    @NotEmpty
    @Column(nullable = false)
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
