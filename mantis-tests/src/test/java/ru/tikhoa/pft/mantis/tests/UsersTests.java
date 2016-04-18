package ru.tikhoa.pft.mantis.tests;


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.tikhoa.pft.mantis.Model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class UsersTests extends TestBase{

    @BeforeClass
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String user = "user" + now;
        String password = "password";
        app.users().signup(user, email);
        confirmPasswordByLink(password, email, 2);
        assertTrue(app.newSession().login(user, password));
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException {
        String password = "password";
        app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.goTo().manageUsers();
        String username = app.getDriver().findElement(By.cssSelector(".row-1 > td:nth-child(1) > a:nth-child(1)")).getText();
        String email = app.users().resetPassword(username);
        confirmPasswordByLink(password, email, 1);
        assertTrue(app.newSession().login(username, password));

    }

    public void confirmPasswordByLink(String password, String email, int count) throws MessagingException, IOException {
        List<MailMessage> mailMessages = app.mail().waitForMail(count, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.users().confirmPassword(confirmationLink, password);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterClass(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
