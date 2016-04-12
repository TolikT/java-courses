package ru.tikhoa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;
import ru.tikhoa.pft.addressbook.model.Contacts;
import ru.tikhoa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToAndFromGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){

        // go to group page and create group if there is no groups
        app.goTo().groupPage();

        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }

        app.goTo().homePage();

        // if there are no contacts - create one
        if (app.db().contacts().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Anatoly").withLastname("Tikhomirov")
                    .withEmail("anatoly.tikhomirov@emc.com").withAddress("SaintP"));
        }
    }

    @Test
    public void testContactAdditionAndRemoving() {

        // select group to add contact to it
        String groupName = app.db().groups().iterator().next().getName();

        // list of contacts
        Contacts contacts = app.db().contacts();

        // preconditions are present, set is not empty
        // random contact
        ContactData addedContact = contacts.iterator().next();

        app.goTo().homePage();

        // add contact to group
        app.contact().addToGroup(addedContact, groupName);

        // go to home page
        app.goTo().homePage();

        app.contact().filterByGroupName(groupName);

        // check that contact in group
        assertThat(app.contact().all().contains(addedContact), equalTo(true));

        // remove contact from group
        app.contact().removeFromGroup(addedContact);

        // go to home page
        app.goTo().homePage();

        app.contact().filterByGroupName("[all]");

        // check that contact is present in list
        assertThat(app.contact().all().contains(addedContact), equalTo(true));

        app.contact().filterByGroupName(groupName);

        // check that contact is not in group
        assertThat(app.contact().all().contains(addedContact), equalTo(false));
    }
}
