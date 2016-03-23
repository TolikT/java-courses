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
        app.goTo().groupPage();

        // if there are no groups - create one
        if (app.group().list().size() == 0){
            app.group().create(new GroupData().withName("test1"));
        }

    }

    @Test
    public void testGroupDeletion() {

        // list of groups before
        List<GroupData> before = app.group().list();

        // delete a group and go back to group page
        int index = before.size() - 1;
        app.group().delete(index);

        // list of groups after
        List<GroupData> after = app.group().list();

        // compare before and after size
        Assert.assertEquals(before.size() - 1, after.size());

        // old list without removed list
        before.remove(index);

        // compare lists
        Assert.assertEquals(before, after);

    }

}
