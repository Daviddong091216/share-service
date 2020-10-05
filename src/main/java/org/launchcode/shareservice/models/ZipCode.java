package org.launchcode.shareservice.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ZipCode extends AbstractEntity {

    @OneToMany(mappedBy = "zipCode")
    private final List<AcNeedsRepairing> acNeedsRepairings=new ArrayList<>();

    public ZipCode() {
    }

    public List<AcNeedsRepairing> getAcNeedsRepairings() {
        return acNeedsRepairings;
    }

}
