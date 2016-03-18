package ru.tikhoa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        // go to group page
        app.getNavigationHelper().goToGroupPage();

        // list of groups before
        List<GroupData> before = app.getGroupHelper().getGroupList();

        // create a group
        GroupData group = new GroupData("test1", null, null);
        app.getGroupHelper().createGroup(group);

        // list of groups after
        List<GroupData> after = app.getGroupHelper().getGroupList();

        // compare before and after size
        Assert.assertEquals(before.size() + 1, after.size());

        // new id is max id, so find max
        int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

        // compare new and old lists using sort
        group.setId(max);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }

}
