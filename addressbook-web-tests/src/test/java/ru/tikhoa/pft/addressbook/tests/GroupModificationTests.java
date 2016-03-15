package ru.tikhoa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{

    @Test
    public void testModificationGroup() {

        // go to group page
        app.getNavigationHelper().goToGroupPage();

        // if there are no groups - create one
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        // list of checkboxes before
        List<GroupData> before = app.getGroupHelper().getGroupList();

        // modify a group and go back to group page
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "test1", "test2", "test3");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        // list of checkboxes after
        List<GroupData> after = app.getGroupHelper().getGroupList();

        // compare before and after size
        Assert.assertEquals(before.size(), after.size());

        // check using sets
        before.remove(before.size() - 1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
