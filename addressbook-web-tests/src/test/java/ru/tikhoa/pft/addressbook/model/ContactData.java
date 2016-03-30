package ru.tikhoa.pft.addressbook.model;


import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")
public class ContactData {
    @Expose
    private String firstname;
    @XStreamOmitField
    private String middlename;
    @Expose
    private String lastname;
    @XStreamOmitField
    private String nickname;
    @XStreamOmitField
    private String email;
    @XStreamOmitField
    private String address;
    @XStreamOmitField
    private String group;
    @XStreamOmitField
    private String homePhone;
    @XStreamOmitField
    private String mobilePhone;
    @XStreamOmitField
    private String workPhone;
    @XStreamOmitField
    private String allPhones;
    @XStreamOmitField
    private int id;
    @XStreamOmitField
    private File photo;

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
    public String getNickname() {
        return nickname;
    }
    public String getWorkPhone() {
        return workPhone;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public String getAllPhones() {
        return allPhones;
    }
    public File getPhoto() {
        return photo;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }
    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }
    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
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

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
