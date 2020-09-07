package org.launchcode.shareservice.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Problem extends AbstractEntity{

    @NotNull(message = "Description is required!")
    @Size(min = 3, max = 500, message = "Description must be between 3 and 500 characters.")
    private String description;

    @ManyToMany(mappedBy = "problems")
    private final List<AcNeedsRepairing> acNeedsRepairings= new ArrayList<>();

    public Problem(String description) {
        this.description = description;
    }

    public Problem() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AcNeedsRepairing> getAcNeedsRepairings() {
        return acNeedsRepairings;
    }
}
