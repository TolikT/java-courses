package ru.tikhoa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase{

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
