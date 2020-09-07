package org.launchcode.shareservice.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AcNeedsRepairing extends AbstractEntity {

    @ManyToOne
    @NotNull(message = "State is required.")
    private State state;

    @ManyToOne
    @NotNull(message = "City is required.")
    private City city;

    @ManyToMany
    @NotEmpty(message = "Problems are required.")
//    @NotBlank(message = "Problems are required.")
    @NotNull(message = "Problems are required.")
    private final List<Problem> problems = new ArrayList<>();

    public AcNeedsRepairing() {
    }

    public AcNeedsRepairing(String name,State state, City city) {
        super(name);
        this.state = state;
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> aProblems) {
        for (Problem problem : aProblems) {
            this.problems.add(problem);
        }
    }

}
