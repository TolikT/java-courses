package ru.tikhoa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {

        // go to group page
        app.getNavigationHelper().goToGroupPage();

        // number of checkboxes before
        int before = app.getGroupHelper().getGroupCount();

        // if there are no groups - create one
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }

        // delete a group and go back to group page
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();

        // number of checkboxes after
        int after = app.getGroupHelper().getGroupCount();

        // compare before and after values
        Assert.assertEquals(before - 1, after);
    }

}
