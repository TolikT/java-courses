package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }

}
