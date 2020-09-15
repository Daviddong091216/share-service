package org.launchcode.shareservice.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Problem extends AbstractEntity {

    @ManyToMany(mappedBy = "problems")
    private final List<AcNeedsRepairing> acNeedsRepairings = new ArrayList<>();

    public Problem() {
    }

    public List<AcNeedsRepairing> getAcNeedsRepairings() {
        return acNeedsRepairings;
    }
}
