package ru.tikhoa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        // go to group page
        app.goTo().groupPage();

        // list of groups before
        Set<GroupData> before = app.group().all();

        // create a group
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);

        // list of groups after
        Set<GroupData> after = app.group().all();

        // compare before and after size
        Assert.assertEquals(before.size() + 1, after.size());

        // new id is max id, so find max
        //int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        int max = after.stream().mapToInt((g) -> g.getId()).max().getAsInt();

        // compare new and old lists using sort
        group.withId(max);
        before.add(group);
        Assert.assertEquals(after, before);
    }

}
