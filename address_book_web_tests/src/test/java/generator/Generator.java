package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.Utils;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();

    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if (type.equals("groups")) {
            return generateGroups();
        } else if (type.equals("contacts")) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Unknown type " + type);
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 1; i <= count; i++) {
            result.add(new GroupData()
                    .withName(Utils.randomString(i * 10))
                    .withHeader(Utils.randomString(i * 5))
                    .withFooter(Utils.randomString(i * 4)));
        }
        return result;
    }

    private Object generateContacts() {
        return null;
    }

    private void save(Object data) throws IOException {
        if (format.equals("json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

//            writing data in file using jackson library:
//            objectMapper.writeValue(new File(output), data);

//            writing data in file using standard java library:
            var json = objectMapper.writeValueAsString(data);
            try (var writer = new FileWriter(output)) {
                writer.write(json);
            }
        }
        else if (format.equals("yaml")) {
            var yamlMapper = new YAMLMapper();
            yamlMapper.writeValue(new File(output), data);
        }
        else if (format.equals("xml")) {
            var xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File(output), data);
        }
        else {
            throw new IllegalArgumentException("Unknown file format " + format);
        }
    }




}
