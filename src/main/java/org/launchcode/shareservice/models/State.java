package org.launchcode.shareservice.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class State extends AbstractEntity {

    @OneToMany(mappedBy = "state")
    private final List<AcNeedsRepairing> acNeedsRepairings = new ArrayList<>();

    public State() {
    }

    public List<AcNeedsRepairing> getAcNeedsRepairings() {
        return acNeedsRepairings;
    }

}
