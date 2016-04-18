package ru.tikhoa.pft.mantis.appmanager;


import org.openqa.selenium.By;

public class UsersHelper extends HelperBase{

    public UsersHelper(ApplicationManager app) {
        super(app);
    }

    public void signup(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void confirmPassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }

    public String resetPassword(String username) {
        click(By.linkText(username));
        String email = wd.findElement(By.name("email")).getAttribute("value");
        click(By.cssSelector("div.border:nth-child(8) > form:nth-child(1) > input:nth-child(3)"));
        return email;
    }
}
