package ru.tikhoa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.tikhoa.pft.addressbook.model.GroupData;
import ru.tikhoa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType()); // List<GroupData>.class
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {

        // go to group page
        app.goTo().groupPage();

        // list of groups before
        Groups before = app.group().all();

        // create a group
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
