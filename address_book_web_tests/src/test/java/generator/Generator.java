package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import common.Utils;
import model.GroupData;

import java.util.ArrayList;

public class Generator {
    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-n"})
    int count;

    public static void main(String[] args) {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if (type.equals("groups")){
            return generateGroups();
        } else if (type.equals("contacts")){
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Unknown type " + type);
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for(int i=1; i<=count; i++ ){
            result.add(new GroupData()
                    .withName(Utils.randomString(i*10))
                    .withHeader(Utils.randomString(i*5))
                    .withFooter(Utils.randomString(i*4)));
        }
        return result;
    }

    private Object generateContacts() {
        return null;
    }

    private void save(Object data) {
    }
}