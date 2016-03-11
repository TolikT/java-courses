package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        // go to "add new" tab
        app.getNavigationHelper().goToContactPage();

        // type all information in contact form
        app.getContactHelper().createContact(new ContactData("Vladimir", "Alexandrovich",
                "Drobyshev", "Tolsty", "vladimir.drobyshev@emc.com", "SaintP", "test1"));
    }



}
