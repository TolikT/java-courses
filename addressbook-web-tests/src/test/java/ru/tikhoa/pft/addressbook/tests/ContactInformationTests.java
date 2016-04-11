package ru.tikhoa.pft.addressbook.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTests extends TestBase{

    private ContactData contact;
    private ContactData contactInfoFromEditForm;

    @BeforeMethod
    public void ensurePreconditions(){

        // go to home page
        app.goTo().homePage();

        contact = app.db().contacts().iterator().next();
        contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    }

    @BeforeClass
    public void ensurePreconditionsAll(){

        // go to home page
        app.goTo().homePage();

        // if there are no groups - create one
        if (app.db().contacts().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Anatoly").withLastname("Tikhomirov")
                    .withEmail("anatoly.tikhomirov@emc.com")
                    .withHomePhone("111")
                    .withMobilePhone("222")
                    .withWorkPhone("333")
                    .withAddress("SaintP"));
        }

    }

    @Test()
    public void testContactPhones() {
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    @Test()
    public void testContactEmailAndAddress() {
        assertThat(contact.getEmail(), equalTo(cleanedEmail(contactInfoFromEditForm.getEmail())));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    @Test()
    public void testContactDetails() {
        assertThat(app.contact().getContactDetailsById(contact.getId()),
                equalTo(mergeDetails(contactInfoFromEditForm)));
    }

    @Test()
    public void testContactUI() {
        verifyContactListInUI();
    }


    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactInformationTests::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    private String mergeDetails(ContactData contact) {
        return Arrays.asList(contact.getFirstname()+" "+contact.getLastname(),
                contact.getAddress(), cleanedSpaces(contact.getHomePhone()), cleanedSpaces(contact.getMobilePhone()),
                cleanedSpaces(contact.getWorkPhone()), cleanedEmail(contact.getEmail()))
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private static String cleanedPhone(String phone){
        return phone.replaceAll("[-\\s()]", "");
    }

    private String cleanedEmail(String email){
        return cleanedSpaces(email);
    }

    private String cleanedSpaces(String email){
        return email.replaceAll("\\s+", " ");
    }
}
