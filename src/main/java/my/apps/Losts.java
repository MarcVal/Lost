package my.apps;

import java.util.Date;

/**
 * Created by Valeria Marc on 2/19/2017.
 */
public class Losts {
    private String ownerName;
    private String email;
    private String phone;
    private String message;
    private String microchip;
    private String neutered;


    public Losts(String ownerName, String email, String phone, String message, String microchip, String neutered) {
        this.ownerName = ownerName;
        this.email = email;
        this.phone = phone;
        this.message = message;
        this.microchip = microchip;
        this.neutered = neutered;

    }

    @Override
    public String toString() {
        return "Losts: ownerName = " + ownerName;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMicrochip() {
        return this.microchip;
    }

    public String getNeutered() {
        return this.neutered;
    }


}
