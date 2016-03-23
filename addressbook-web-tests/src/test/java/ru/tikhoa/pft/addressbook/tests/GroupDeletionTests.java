package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;
import ru.tikhoa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

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
    public void testGroupDeletion() {

        // list of groups before
        Groups before = app.group().all();

        // preconditions are present, set is not empty
        // random group
        GroupData deletedGroup = before.iterator().next();

        // delete a group and go back to group page
        app.group().delete(deletedGroup);

        // list of groups after
        Groups after = app.group().all();

        // compare before and after size
        assertThat(after.size(), equalTo(before.size() - 1));

        // compare sets of groups
        assertThat(after, equalTo(before.without(deletedGroup)));

    }

}
