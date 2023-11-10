package model;

public record ContactData(String id, String firstName, String middleName, String lastName, String company, String address, String homePhone, String mobilePhone,
                          String workPhone, String email, String email2, String email3, String photo,
                          String secondaryPhone) {
    public ContactData(){
        this("","", "", "", "", "","","","","","","", "", "");
    }

    public ContactData withFullName(String firstName, String lastName) {
        return new ContactData(this.id, firstName, this.middleName, lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withMinSetOfData(String firstName, String lastName, String address, String mobilePhone, String email){
        return new ContactData(this.id, firstName, this.middleName, lastName, this.company, address, this.homePhone, mobilePhone, this.workPhone, email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.id, this.firstName, middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, this.middleName, lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withCompany(String company) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.company, address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withHomePhone(String homePhone) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.company, this.address, homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withWorkPhone(String workPhone) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, workPhone, this.email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, email, this.email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, email2, this.email3, this.photo, this.secondaryPhone);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, email3, this.photo, this.secondaryPhone);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, mobilePhone, this.workPhone, this.email, this.email2, this.email3, photo, this.secondaryPhone);
    }

    public ContactData withSecondaryPhone(String secondaryPhone) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo, secondaryPhone);
    }
}
