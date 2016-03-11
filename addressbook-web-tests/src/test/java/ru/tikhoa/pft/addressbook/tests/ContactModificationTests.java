package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification() {
        // go to home page
        app.getNavigationHelper().goToHomePage();

        // check contact presenting
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().createContact(new ContactData("Vladimir", "Alexandrovich",
                    "Drobyshev", "Tolsty", "vladimir.drobyshev@emc.com", "SaintP", "test1"));
        }

        // go to "add new" page
        app.getContactHelper().initContactModification();

        // fill all data
        app.getContactHelper().fillContactForm(new ContactData("Vladimir", "Alexandrovich",
                "Drobyshev", "Tolsty", "vladimir.drobyshev@emc.com", "SaintP", null), false);

        // click "enter" button
        app.getContactHelper().submitContactModification();
    }
}
