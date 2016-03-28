package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;
import ru.tikhoa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        // now we are on the home page
        // list of contacts before
        Contacts before = app.contact().all();

        // go to "add new" tab
        app.goTo().goToContactPage();

        File photo = new File("src/test/resources/picture.jpg");

        // create contact as object
        ContactData contact = new ContactData().withFirstname("Anatoly").withLastname("Tikhomirov")
                .withMiddlename("Vladimirovich").withEmail("anatoly.tikhomirov@emc.com")
                .withGroup("test1").withPhoto(photo);

        // type all information in contact form
        app.contact().create(contact);

        // go to home page
        app.goTo().homePage();

        // compare before and after size
        assertThat(app.contact().count(), equalTo(before.size() + 1));

        // set of contacts after
        Contacts after = app.contact().all();

        // find max id (from checkboxes)
        int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

        // compare new and old sets
        contact.withId(max);
        assertThat(after, equalTo(before.withAdded(contact)));

    }

}
