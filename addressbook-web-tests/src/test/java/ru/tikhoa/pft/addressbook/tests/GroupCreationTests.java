package ru.tikhoa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;
import ru.tikhoa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        // go to group page
        app.goTo().groupPage();

        // list of groups before
        Groups before = app.group().all();

        // create a group
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);

        // compare before and after size
        assertThat(app.group().count(), equalTo(before.size() + 1));

        // list of groups after
        Groups after = app.group().all();

        // new id is max id, so find max
        //int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        int max = after.stream().mapToInt((g) -> g.getId()).max().getAsInt();

        // compare new and old lists
        assertThat(after, equalTo(before.withAdded(group.withId(max))));
    }

    @Test
    public void testBadGroupCreation() {

        // go to group page
        app.goTo().groupPage();

        // list of groups before
        Groups before = app.group().all();

        // create a group
        GroupData group = new GroupData().withName("test2'");
        app.group().create(group);

        // compare before and after size
        assertThat(app.group().count(), equalTo(before.size()));

        // list of groups after
        Groups after = app.group().all();

        // compare new and old lists
        assertThat(after, equalTo(before));
    }

}
