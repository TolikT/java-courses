package ru.tikhoa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        // go to group page
        app.getNavigationHelper().goToGroupPage();

        // list of checkboxes before
        List<GroupData> before = app.getGroupHelper().getGroupList();

        // create a group
        GroupData group = new GroupData("test1", null, null);
        app.getGroupHelper().createGroup(group);

        // list of checkboxes after
        List<GroupData> after = app.getGroupHelper().getGroupList();

        // compare before and after size
        Assert.assertEquals(before.size() + 1, after.size());

        // new id is max id, so find max
        int max = 0;
        for (GroupData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }

        // compare new and old lists excluding the order
        group.setId(max);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
