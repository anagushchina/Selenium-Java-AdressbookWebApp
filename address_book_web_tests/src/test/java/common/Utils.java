package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class Utils {
    public static String randomString(int n){
        var rnd = new Random();
        var result = "";
        for(int i = 1; i<=n; i++){
            result = result + (char)('a'+ rnd.nextInt(26));
        }
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
