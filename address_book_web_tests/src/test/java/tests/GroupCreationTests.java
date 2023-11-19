package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Utils;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class GroupCreationTests extends TestBase{

    //get list of groups from DB using Hibernate library
    @ParameterizedTest
    @MethodSource("randomGroups")
    public void createGroupsHbmTest(GroupData group) {
        List<GroupData> oldGroups = appMan.initHbm().getGroupList();
        appMan.initGroupHelper().createGroup(group);
        List<GroupData> newGroups = appMan.initHbm().getGroupList();
        var extraGroups = newGroups.stream().filter(g -> !oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        var expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.add(group.withId(newId));
        Assertions.assertEquals(Set.copyOf(expectedGroups), Set.copyOf(newGroups));
    }

    //get list of groups from DB using jdbc library and SQL
    @ParameterizedTest
    @MethodSource("randomGroups")
    public void createGroupsJdbcTest(GroupData group) {
        List<GroupData> oldGroups = appMan.initJdbcHelper().getGroupList();
        appMan.initGroupHelper().createGroup(group);
        List<GroupData> newGroups = appMan.initJdbcHelper().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) ->
        {return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));};
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size()-1).id();
        var expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.add(group.withId(maxId));
        expectedGroups.sort(compareById);
        Assertions.assertEquals(expectedGroups, newGroups);
    }

    public static Stream<GroupData> randomGroups() {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(Utils.randomString(6))
                .withHeader(Utils.randomString(5))
                .withFooter(Utils.randomString(8));
        return Stream.generate(randomGroup).limit(2);
    }


    //get list of groups from UI (getting attribute 'text' from elements)
    @ParameterizedTest
    @MethodSource("groupsProvider")
    public void createMultipleGroupsTest(GroupData group) {
        List<GroupData> oldGroups = appMan.initGroupHelper().getList();
        appMan.initGroupHelper().createGroup(group);
        List<GroupData> newGroups = appMan.initGroupHelper().getList();
        Comparator<GroupData> compareById = (o1, o2) ->
        {return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));};
        newGroups.sort(compareById);
        var expectedGroups = new ArrayList<>(oldGroups);
        expectedGroups.add(group.withId(newGroups.get(newGroups.size()-1).id()).withHeader("").withFooter(""));
        expectedGroups.sort(compareById);
        Assertions.assertEquals(expectedGroups, newGroups);
    }

    public static List<GroupData> groupsProvider() throws IOException {
        var result = new ArrayList<GroupData>();
//        for(var name: List.of("", "name")){
//            for(var header: List.of("", "header")){
//                for(var footer: List.of("", "footer")){
//                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
//                }
//            }
//        }

//        Ways of reading a file and save it in the variable 'data':
//        1. read and save the whole file using java.io.File library:
//        var data = new File(fileName);

//        2. read and save the whole file using java.nio.file.Files library:
//        var data = Files.readString(Paths.get(fileName));

//        3. read by strings
        var data = "";
        try (var reader = new FileReader("groups.json");
             var bReader = new BufferedReader(reader);){
            var line = bReader.readLine();
            while (line!=null){
                data = data + line;
                line = bReader.readLine();
            }
        }
//        Analyzes data and converts strings into objects of specified type:
        ObjectMapper objectMapper = new ObjectMapper();
        var value = objectMapper.readValue(data, new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
        return result;
    }


    @ParameterizedTest
    @MethodSource("negativeGroupsProvider")
    public void cannotCreateGroupTest(GroupData group) {
        List<GroupData> oldGroups = appMan.initGroupHelper().getList();
        appMan.initGroupHelper().createGroup(group);
        List<GroupData> newGroups = appMan.initGroupHelper().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }

    public static List<GroupData> negativeGroupsProvider() {
        return new ArrayList<>(List.of(
                new GroupData("", "name'", "","")));
    }



}
