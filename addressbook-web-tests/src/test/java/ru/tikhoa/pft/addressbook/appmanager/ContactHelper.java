package ru.tikhoa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.tikhoa.pft.addressbook.model.ContactData;

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
        type(By.name("nickname"), contactData.getNicktname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address2"), contactData.getAddress());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void deleteContact(){
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void initContactModification(int number){
        String locator = "//table[@id='maintable']/tbody/tr[" + Integer.toString(number + 1) + "]/td[8]/a/img";
        click(By.xpath(locator));
    }

    public boolean isThereAContact(){
        return isElementPresent(By.cssSelector("#maintable > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(8) > a:nth-child(1) > img:nth-child(1)"));
    }

    public void createContact(ContactData contactData) {
        fillContactForm(contactData, true);
        submitContactCreation();
    }

    // return list of contacts on the page
    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        WebElement table = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(rows.get(i).findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, name, lastName, null, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
