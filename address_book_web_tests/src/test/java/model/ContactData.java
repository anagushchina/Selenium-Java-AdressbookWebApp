package model;

public record ContactData(String id, String firstName, String middleName, String lastName, String company, String address, String homePhone, String mobilePhone,
                          String workPhone, String email, String email2, String email3, String photo) {
    public ContactData(){
        this("","", "", "", "", "","","","","","","", "");
    }

    public ContactData withFullName(String firstName, String lastName) {
        return new ContactData(this. id, firstName, this.middleName, lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withMinSetOfData(String firstName, String lastName, String address, String mobilePhone, String email){
        return new ContactData(this. id, firstName, this.middleName, lastName, this.company, address, this.homePhone, mobilePhone, this.workPhone, email, this.email2, this.email3, this.photo);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this. id, firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this. id, this.firstName, middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this. id, this.firstName, this.middleName, lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withCompany(String company) {
        return new ContactData(this. id, this.firstName, this.middleName, this.lastName, company, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this. id, this.firstName, this.middleName, this.lastName, this.company, address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this. id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, mobilePhone, this.workPhone, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this. id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, this.mobilePhone, this.workPhone, email, this.email2, this.email3, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this. id, this.firstName, this.middleName, this.lastName, this.company, this.address, this.homePhone, mobilePhone, this.workPhone, this.email, this.email2, this.email3, photo);
    }
}
