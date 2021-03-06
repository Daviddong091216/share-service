package org.launchcode.shareservice.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AcNeedsRepairing extends AbstractEntity {


    private String description;

    @NotNull(message = "Address is required!")
    private String address;

    @NotNull(message = "Email is required!")
    @Email(message = "Invalid email. Try again.")
    private String email;

    @NotNull(message = "Phone number is required!")
    @Size(min = 10, max = 10, message = "Phone number needs 10 digits.")
    private String phoneNumber;

    @NotNull(message = "Date is required!")
    private String date;

    @NotNull(message = "Time is required!")
    private String time;

    @NotNull(message = "Whether Emergency is required!")
    private String whetherEmergency;

//    private BufferedImage image;

    @ManyToOne
    @NotNull(message = "State is required.")
    private State state;

    @ManyToOne
    @NotNull(message = "City is required.")
    private ZipCode zipCode;

    @ManyToMany
    @NotEmpty(message = "Problems are required.")
//    @NotBlank(message = "Problems are required.")
    @NotNull(message = "Problems are required.")
    private final List<Problem> problems = new ArrayList<>();

    public AcNeedsRepairing() {
    }

    public AcNeedsRepairing(String name, String description, String address,
                            String email, String phoneNumber, String date,
                            String time, String whetherEmergency, State state,
                            ZipCode zipCode /*BufferedImage image*/) {
        super(name);
        this.description = description;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;
        this.whetherEmergency = whetherEmergency;
        this.state = state;
        this.zipCode = zipCode;
//        this.image=image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getWhetherEmergency() {
        return whetherEmergency;
    }

    public void setWhetherEmergency(String whetherEmergency) {
        this.whetherEmergency = whetherEmergency;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> aProblems) {
        for (Problem problem : aProblems) {
            this.problems.add(problem);
        }
    }

//    public BufferedImage getImage() {
//        return image;
//    }
//
//    public void setImage(BufferedImage image) {
//        this.image = image;
//    }
}
