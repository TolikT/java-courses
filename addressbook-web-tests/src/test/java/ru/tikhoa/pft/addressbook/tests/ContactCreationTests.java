package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Vladimir", "Alexandrovich",
                "Drobyshev", "Tolsty", "vladimir.drobyshev@emc.com", "SaintP", "test1"), true);
        app.getContactHelper().submitContactCreation();
    }

}
