package org.launchcode.shareservice.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City  extends AbstractEntity{

    @OneToMany(mappedBy = "city")
    private final List<AcNeedsRepairing> acNeedsRepairings=new ArrayList<>();

    public City() {
    }

    public List<AcNeedsRepairing> getAcNeedsRepairings() {
        return acNeedsRepairings;
    }

}
