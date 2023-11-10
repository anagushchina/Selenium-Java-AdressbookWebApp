package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static String randomString(int n){
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26); //generates random numbers
        var result = Stream.generate(randomNumbers)
                .limit(n) //generates n random numbers
                .map(i -> 'a' + i)//each random number (i) converts in another number: code of char 'a' (97) plus i
                .map(i -> Character.toString(i))//converts number i into character and then string containing 1 char
                .collect(Collectors.joining());//joins all strings in one string
        return result;
    }

    public static String randomNumber(int n){
        var rnd = new Random();
        var result = "";
        for(int i = 1; i<=n; i++){
            result = result + rnd.nextInt(9);
        }
        return result;
    }

    public static String randomFile(String directoryPath){
        var fileNameList = new File(directoryPath).list();
        var index = new Random().nextInt(fileNameList.length);
        var filePath = Paths.get(directoryPath, fileNameList[index]).toString();
        return filePath;
    }
}
