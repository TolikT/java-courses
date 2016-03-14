package ru.tikhoa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        // go to group page
        app.getNavigationHelper().goToGroupPage();

        // number of checkboxes before
        int before = app.getGroupHelper().getGroupCount();

        // create a group
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));

        // number of checkboxes after
        int after = app.getGroupHelper().getGroupCount();

        // compare before and after values
        Assert.assertEquals(before + 1, after);

    }

}
