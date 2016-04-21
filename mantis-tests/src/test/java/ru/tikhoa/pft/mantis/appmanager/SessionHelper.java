package ru.tikhoa.pft.mantis.appmanager;

import org.openqa.selenium.By;


public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) throws InterruptedException {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.xpath("/html/body/div[3]/form/table/tbody/tr[6]/td/input"));
    }
}
