package ru.tikhoa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        // now we are on the home page
        // list of contacts before
        List<ContactData> before = app.getContactHelper().getContactList();

        // go to "add new" tab
        app.getNavigationHelper().goToContactPage();

        // create contact as object
        ContactData contact = new ContactData("Anatoly", "Tikhomirov", "Alexandrovich",
                 null, "vladimir.drobyshev@emc.com", "SaintP", "test1");

        // type all information in contact form
        app.getContactHelper().createContact(contact);

        // redirect to home page happens automatically after creation

        // list of contacts after
        List<ContactData> after = app.getContactHelper().getContactList();

        // compare before and after size
        Assert.assertEquals(before.size() + 1, after.size());

        // find max id (from checkboxes)
        int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

        // compare new and old lists using sort
        contact.setId(max);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }

}
