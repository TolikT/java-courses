package ru.tikhoa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;
import ru.tikhoa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        // go to group page and create group if there is no groups
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testModificationGroup() {

        // list of groups before
        Groups before = app.db().groups();

        // preconditions are present, set is not empty
        // random group
        GroupData modifiedGroup = before.iterator().next();

        // modify a group and go back to group page
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group);

        // compare before and after size
        assertThat(app.group().count(), equalTo(before.size()));

        // list of groups after
        Groups after = app.db().groups();

        // check
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

        verifyGroupListInUI();

    }



}
