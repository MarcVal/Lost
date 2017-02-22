package my.apps.domain;
import java.sql.Date;
/**
 * Created by Valeria Marc on 2/22/2017.
 */
public class LostEntry {

private String owner;
private String email;
private String phone;
private String message;
private String chipped;
private String neutered;

public LostEntry (String owner,String email,String phone,String message, String chipped, String neutered) {
    this.owner = owner;
    this.email = email;
    this.phone = phone;
    this.message=message;
    this.chipped=chipped;
    this.neutered=neutered;
}
    @Override
    public String toString() {
        return "LostEntry{" +
                "Owner = " + owner + '\'' +
                "Email = " + email + '\'' +
                "Phone = " + phone + '\'' +
                "Message = " + message + '\'' +
                "Chipped = " + chipped + '\'' +
                "Neutered = " + neutered + '\'' +
                '}';

    }

    public String getOwner() {
        return owner;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMessage() {
        return message;
    }

    public String getChipped() {
        return chipped;
    }

    public String getNeutered() {
        return neutered;
    }
}
