package ru.tikhoa.pft.addressbook.model;


import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {

    @Expose
    @Column(name="firstname")
    private String firstname;

    @XStreamOmitField
    @Transient
    private String middlename;

    @Expose
    @Column(name="lastname")
    private String lastname;

    @XStreamOmitField
    @Transient
    private String nickname;

    @Expose
    @XStreamOmitField
    @Column(name="email")
    @Type(type="text")
    private String email;

    @Expose
    @XStreamOmitField
    @Column(name="address")
    @Type(type="text")
    private String address;

    @Expose
    @Transient
    private String group;

    @Expose
    @XStreamOmitField
    @Column(name="home")
    @Type(type="text")
    private String homePhone;

    @Expose
    @XStreamOmitField
    @Column(name="mobile")
    @Type(type="text")
    private String mobilePhone;

    @Expose
    @XStreamOmitField
    @Column(name="work")
    @Type(type="text")
    private String workPhone;

    @XStreamOmitField
    @Transient
    private String allPhones;

    @XStreamOmitField
    @Id
    @Column(name="id")
    private int id;

    @XStreamOmitField
    @Column(name="photo")
    @Type(type="text")
    @Transient
    private String photo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        return workPhone != null ? workPhone.equals(that.workPhone) : that.workPhone == null;

    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", workPhone='" + workPhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
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
        String result = new String(homePhone + "\n" + mobilePhone + "\n" + workPhone);
        return (result.equals("\n\n") ? "" : homePhone + "\n" + mobilePhone + "\n" + workPhone);
    }

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        } else {
            return null;
        }
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
        this.photo = photo.getPath();
        return this;
    }

}
