package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;

    public String firstName;
    public String middleName;
    public String lastName;
    public String nickname="";
    public String company;
    public String title="";
    public String address;

    @Column(name = "home")
    public String homePhone;

    @Column(name = "mobile")
    public String mobilePhone;

    @Column(name = "work")
    public String workPhone;

    public String fax="";
    public String email;
    public String email2;
    public String email3;

    public String im="";
    public String im2="";
    public String im3="";
    public String homepage="";
    public int bday=0;
    public String bmonth="";
    public String byear="";
    public int aday=0;
    public String amonth="";
    public String ayear="";
    public String address2="";

    @Column(name = "phone2")
    public String secondaryPhone;

    public String notes="";




    public ContactRecord(){
    }

    public ContactRecord(int id, String firstName, String middleName, String lastName, String company, String address, String homePhone, String mobilePhone, String workPhone, String email, String email2, String email3, String secondaryPhone){
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.secondaryPhone = secondaryPhone;
    }
}
