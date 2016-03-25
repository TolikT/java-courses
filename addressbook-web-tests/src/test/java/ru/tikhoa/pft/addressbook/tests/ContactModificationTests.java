package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;
import ru.tikhoa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){

        // go to home page
        app.goTo().homePage();

        // if there are no groups - create one
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData().withFirstname("Anatoly").withLastname("Tikhomirov")
                    .withMiddlename("Vladimirovich").withEmail("anatoly.tikhomirov@emc.com"));
        }

    }

    @Test
    public void testContactModification() {

        // list of contacts before
        Contacts before = app.contact().all();

        // preconditions are present, set is not empty
        // random contact
        ContactData modifiedContact = before.iterator().next();

        // modify with created data
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Anatoly").withLastname("Tikhomirov")
                .withMiddlename("Vladimirovich").withEmail("anatoly.tikhomirov@emc.com").withAddress("SaintP");

        app.contact().modify(modifiedContact.getId(), contact);

        // go to home page
        app.goTo().homePage();

        // compare before and after size
        assertThat(app.contact().count(), equalTo(before.size()));

        // set of contacts after
        Contacts after = app.contact().all();

        // check equality of sets
        assertThat(before.without(modifiedContact).withAdded(contact), equalTo(after));
    }

}
