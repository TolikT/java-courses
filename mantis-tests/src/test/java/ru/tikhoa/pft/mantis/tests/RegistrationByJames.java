package ru.tikhoa.pft.mantis.tests;


import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.tikhoa.pft.mantis.Model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationByJames extends TestBase{

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost", now);
        String user = "user" + now;
        String password = "password";

        // create user on mail server before his registration
        app.james().createUser(user, password);

        app.users().signup(user, email);
        confirmPasswordByLink(user, password, email);
        assertTrue(app.newSession().login(user, password));
    }

    public void confirmPasswordByLink(String user, String password, String email) throws MessagingException, IOException{
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 300000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.users().confirmPassword(confirmationLink, password);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
