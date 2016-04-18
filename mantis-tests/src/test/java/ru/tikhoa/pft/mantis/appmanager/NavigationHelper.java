package ru.tikhoa.pft.mantis.appmanager;

import org.openqa.selenium.By;


public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUsers() {
        click(By.linkText("Manage Users"));
    }
}
