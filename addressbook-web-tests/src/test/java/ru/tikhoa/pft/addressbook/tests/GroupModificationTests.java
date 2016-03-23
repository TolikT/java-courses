package ru.tikhoa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){

        // go to group page
        app.goTo().groupPage();

        // if there are no groups - create one
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1"));
        }

    }

    @Test
    public void testModificationGroup() {

        // list of groups before
        Set<GroupData> before = app.group().all();

        // preconditions are present, set is not empty
        // random group
        GroupData modifiedGroup = before.iterator().next();

        // modify a group and go back to group page
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group);

        // list of groups after
        Set<GroupData> after = app.group().all();

        // compare before and after size
        Assert.assertEquals(before.size(), after.size());

        // check using sort
        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);

    }

}
