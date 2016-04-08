package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;
import ru.tikhoa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){

        // go to home page
        app.goTo().homePage();

        // if there are no groups - create one
        if (app.db().contacts().size() == 0){
            app.contact().create(new ContactData().withFirstname("Anatoly").withLastname("Tikhomirov")
                    .withMiddlename("Vladimirovich").withEmail("anatoly.tikhomirov@emc.com").withAddress("SaintP"));
        }

    }

    @Test
    public void testContactDeletion() {

        // list of groups before
        Contacts before = app.db().contacts();

        // preconditions are present, set is not empty
        // random contact
        ContactData deletedContact = before.iterator().next();

        // delete a contact
        app.contact().delete(deletedContact);

        // go to home page
        app.goTo().homePage();

        // compare before and after size
        assertThat(app.contact().count(), equalTo(before.size() - 1));

        // list of contacts after
        Contacts after = app.db().contacts();

        // compare lists
        assertThat(after, equalTo(before.without(deletedContact)));

    }

}
