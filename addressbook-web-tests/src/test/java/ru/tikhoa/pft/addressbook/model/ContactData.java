package ru.tikhoa.pft.addressbook.model;


public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String email;
    private final String address;
    private String group;
    private int id;

    public ContactData(String firstname, String lastname, String middlename,
                       String nickname, String email, String address, String group){
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.email = email;
        this.address = address;
        this.group = group;
    }

    public ContactData(int id, String firstname, String lastname, String middlename,
                       String nickname, String email, String address, String group){
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.email = email;
        this.address = address;
        this.group = group;
    }


    public String getFirstname(){
        return  firstname;
    }
    public String getMiddlename(){
        return middlename;
    }
    public String getLastname(){
        return lastname;
    }
    public String getNicktname(){
        return nickname;
    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public String getGroup() {
        return group;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
