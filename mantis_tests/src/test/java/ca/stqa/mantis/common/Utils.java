package ca.stqa.mantis.common;

import ca.stqa.mantis.model.MailMessage;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.regex.Pattern;
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


    public static String extractUrl(List<MailMessage> messages) {
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
        return url;
    }
}
