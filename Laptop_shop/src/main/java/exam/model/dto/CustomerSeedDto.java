package exam.model.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CustomerSeedDto {

    @Expose
    @NotBlank
    @Size(min = 2)
    private String firstName;

    @Expose
    @NotBlank
    @Size(min = 2)
    private String lastName;

    @Expose
    @Email
    @NotBlank
    private String email;


    @Expose
    @NotBlank
    private String registeredOn;

    @Expose
    private TownNameSeedDto town;

    public CustomerSeedDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public TownNameSeedDto getTown() {
        return town;
    }

    public void setTown(TownNameSeedDto town) {
        this.town = town;
    }
}
