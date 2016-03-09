package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteContact();
    }

}
