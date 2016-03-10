package ru.tikhoa.pft.tenantmgmt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateTenantTests {
    WebDriver wd;
    String tenant = randomString();
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void testTenantCreation() {
        goTo(wd);
        for (int i=0; i< 200; i++) {
            createTenant();
        }
    }

    private void createTenant() {
        type(wd, By.xpath("//div[@class='modal-body']/form/div[1]/input"), "bunchie");
        type(wd, By.xpath("//div[@class='modal-body']/form/div[2]/input"), "bunchie");
        click(wd, By.xpath("//div[@class='modal-body']//button[.='Sign In']"));
        click(wd, By.xpath("//tm-manager/nav/div/ul/li[1]/button"));
        type(wd, By.name("tenantId"), tenant);
        type(wd, By.name("zoneName"), tenant);
        type(wd, By.xpath("//div[@class='container']/form/div[7]/input"), tenant);
        type(wd, By.name("tenantName"), tenant);
        type(wd, By.name("companyWebsite"), tenant + ".com");
        type(wd, By.name("firstName"), "Anatoly");
        type(wd, By.name("lastName"), "Tikhomirov");
        type(wd, By.name("phone"), "213344354");
        type(wd, By.name("eMail"), "anatoly.tikhomirov@emc.com");
        type(wd, By.name("country"), "Russia");
        type(wd, By.name("streetAddress1"), "Spb");
        type(wd, By.name("streetAddress2"), "Spb");
        type(wd, By.name("city"), "Spb");
        type(wd, By.name("state"), "Spb");
        type(wd, By.name("zipCode"), "111111");
        click(wd, By.xpath("//select[@id='timeZoneSelect']//option[396]"));
        type(wd, By.name("adminLogin"), tenant);
        type(wd, By.name("adminEmail"), "anatoly.tikhomirov@emc.com");
        click(wd, By.xpath("//div[@class='container']//button[normalize-space(.)='Save']"));
        click(wd, By.xpath("//div[@class='modal-body']//button[.='Sign In']"));
    }

    private static void click(WebDriver wd, By locator) {
        wd.findElement(locator).click();
    }

    private static void type(WebDriver wd, By locator, String input) {
        click(wd, locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(input);
    }

    private static void goTo(WebDriver wd) {
        wd.get("https://tenant-management.bunchie.cf.lss.emc.com/tenant-management-api/ui");
    }

    public static String randomString(){
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

}
