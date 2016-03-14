package ru.tikhoa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        // go to group page
        app.getNavigationHelper().goToGroupPage();

        // list of checkboxes before
        List<GroupData> before =app.getGroupHelper().getGroupList();

        // create a group
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));

        // list of checkboxes after
        List<GroupData> after =app.getGroupHelper().getGroupList();

        // compare before and after size
        Assert.assertEquals(before.size() + 1, after.size());

    }

}
