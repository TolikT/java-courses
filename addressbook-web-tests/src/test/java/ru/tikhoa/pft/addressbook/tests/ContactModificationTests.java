package ru.tikhoa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

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

        // list of contacts before
        List<ContactData> before = app.getContactHelper().getContactList();

        // go to "add new" page
        app.getContactHelper().initContactModification(1);

        // fill all data
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Vladimir", "Alexandrovich",
                "Drobyshev", "Tolsty", "vladimir.drobyshev@emc.com", "SaintP", null);
        app.getContactHelper().fillContactForm(contact, false);

        // click "enter" button
        app.getContactHelper().submitContactModification();

        // redirect to home page

        // list of contacts after
        List<ContactData> after = app.getContactHelper().getContactList();

        // compare before and after size
        Assert.assertEquals(before.size(), after.size());

        // check using sort
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
    
}
