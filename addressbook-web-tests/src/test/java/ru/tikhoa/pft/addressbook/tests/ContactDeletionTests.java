package ru.tikhoa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;

import java.util.List;

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

        // list of contacts before
        List<ContactData> before = app.getContactHelper().getContactList();

        // go to custom (before.size()) contact in the list
        app.getContactHelper().initContactModification(before.size());

        // click "delete" button
        app.getContactHelper().deleteContact();

        // redirect to home page

        // list of contacts after
        List<ContactData> after = app.getContactHelper().getContactList();

        // compare before and after size
        Assert.assertEquals(before.size() - 1, after.size());

        // old list without removed list
        before.remove(before.size() - 1);

        // compare lists
        Assert.assertEquals(before, after);

    }

}
