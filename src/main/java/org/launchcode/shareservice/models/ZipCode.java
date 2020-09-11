package org.launchcode.shareservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ZipCode{

    @Id
    @GeneratedValue
    private int id;

//    @NotNull(message = "Zip Code is required!")
    @Size(min = 5, max = 5, message = "Zip Code must be 5 characters.")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "zipCode")
    private final List<AcNeedsRepairing> acNeedsRepairings=new ArrayList<>();

    public ZipCode() {
    }

    public List<AcNeedsRepairing> getAcNeedsRepairings() {
        return acNeedsRepairings;
    }

}
