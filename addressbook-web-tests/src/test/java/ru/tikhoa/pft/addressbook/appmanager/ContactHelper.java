package ru.tikhoa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.tikhoa.pft.addressbook.model.ContactData;
import ru.tikhoa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {

        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("address"), contactData.getAddress());
        if (contactData.getPhoto() != null) {
            attach(By.name("photo"), contactData.getPhoto());
        }

        if (creation && contactData.getGroups().size() != 0){
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        } else if (!creation){
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void submitContactDeletion(){
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void initContactModificationByNumber(int number){
        String locator = String.format("//table[@id='maintable']/tbody/tr['%s']/td[8]/a/img", number + 1);
        click(By.xpath(locator));
    }

    public boolean isThereAContact(){
        return isElementPresent(By.cssSelector("#maintable > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(8) > a:nth-child(1) > img:nth-child(1)"));
    }

    public void create(ContactData contactData) {
        fillContactForm(contactData, true);
        submitContactCreation();
        contactCache = null;
    }

    public void delete(int number) {
        // go to custom (before.size()) contact in the list
        initContactModificationByNumber(number);

        // click "delete" button
        submitContactDeletion();

        contactCache = null;
    }

    public void delete(ContactData contact) {
        // go to custom (before.size()) contact in the list
        initContactModificationById(contact.getId());

        // click "delete" button
        submitContactDeletion();

        contactCache = null;
    }

    public void modify(int id, ContactData contact) {

        initContactModificationById(id);

        // fill all data
        fillContactForm(contact, false);

        // click "enter" button
        submitContactModification();
    }

    // return list of contacts on the page
    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        WebElement table = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(rows.get(i).findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(name).withLastname(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    // return set of contacts on the page
    public Contacts all() {

        //if (contactCache != null) {
        //    return new Contacts(contactCache);
        //}

        contactCache = new Contacts();
        WebElement table = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(rows.get(i).findElement(By.tagName("input")).getAttribute("value"));
            String address = cells.get(3).getText();
            String email = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            String allPhonesArray[] = allPhones.split("\\n");
            String homePhone = allPhonesArray[0];
            String workPhone = allPhonesArray[2];
            String mobilePhone = allPhonesArray[1];

            contactCache.add(new ContactData().withId(id).withFirstname(name).withLastname(lastName)
                    .withAllPhones(allPhones).withEmail(email).withAddress(address)
                    .withMobilePhone(mobilePhone).withWorkPhone(workPhone).withHomePhone(homePhone));
        }
        return contactCache;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withAddress(address).withMiddlename(middlename);

    }

    public void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
        //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
        //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public String getContactDetailsById(int id) {
        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[7]/a", id))).click();
        String visibleText = wd.findElement(By.id("content")).getText()
                .replaceAll("(Member of.*|Notice:.*|test.*|[WMH]: | \\(www.*\\))", "")
                .replaceAll("\n+", "\n").trim();
        wd.navigate().back();
        return visibleText;
    }

    public void addToGroup(ContactData contact, String groupName) {
        wd.findElement(By.id(String.format("%s", contact.getId()))).click();

        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);

        wd.findElement(By.name("add")).click();
    }

    public void filterByGroupName(String name) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
    }

    public void removeFromGroup(ContactData contact) {
        wd.findElement(By.id(String.format("%s", contact.getId()))).click();
        wd.findElement(By.name("remove")).click();
    }
}
