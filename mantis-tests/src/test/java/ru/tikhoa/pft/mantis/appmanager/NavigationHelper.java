package ru.tikhoa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUsers() {
        //wd.get("http://localhost:8080/mantisbt-1.2.19/manage_proj_create_page.php");
        click(By.linkText("Manage Users"));
    }
}
