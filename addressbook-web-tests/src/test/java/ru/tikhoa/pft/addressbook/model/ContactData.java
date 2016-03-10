package ru.tikhoa.pft.addressbook.model;


public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String email;
    private final String address;
    private String group;

    public ContactData(String firstname, String middlename, String lastname,
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
}
