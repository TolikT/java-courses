package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        // home page contains list of all contacts
        app.getNavigationHelper().goToHomePage();

        // if there is no contact - create it
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().createContact(new ContactData("Vladimir", "Alexandrovich",
                    "Drobyshev", "Tolsty", "vladimir.drobyshev@emc.com", "SaintP", "test1"));
        }

        // go to first contact in the list
        app.getContactHelper().initContactModification();

        // click "delete" button
        app.getContactHelper().deleteContact();
    }

}
