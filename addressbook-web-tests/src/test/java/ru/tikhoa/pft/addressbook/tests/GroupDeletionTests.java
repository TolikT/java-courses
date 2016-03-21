package ru.tikhoa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){

        // go to group page
        app.getNavigationHelper().goToGroupPage();

        // if there are no groups - create one
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

    }

    @Test
    public void testGroupDeletion() {

        // list of groups before
        List<GroupData> before = app.getGroupHelper().getGroupList();

        // delete a group and go back to group page
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();

        // list of groups after
        List<GroupData> after = app.getGroupHelper().getGroupList();

        // compare before and after size
        Assert.assertEquals(before.size() - 1, after.size());

        // old list without removed list
        before.remove(before.size() - 1);

        // compare lists
        Assert.assertEquals(before, after);

    }

}
