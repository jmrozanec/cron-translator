package com.cronutils.descriptor.dataset;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class DatasetCreator2 {
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, String> hours = hours();
        Set<String> samples = new HashSet<>();
        int last = 0;

        while(samples.size()<1000000){
            samples.add(transform(hours, createSample1()));
            samples.add(transform(hours, createSample2()));
            samples.add(transform(hours, createSample3()));
            samples.add(transform(hours, createSample4()));
            samples.add(transform(hours, createSample5()));
            samples.add(transform(hours, createSample6()));
            if(samples.size()%1000==0 && samples.size()>last){
                last = samples.size();
                System.out.println(last);
            }
        }
        try (PrintWriter out = new PrintWriter("dataset_en.psv")) {
            samples.forEach(out::println);
        }
    }

    private static Map<String, String> hours(){
        Map<String, String> hour = new HashMap<>();
        hour.put("00:00", "midnight");
        hour.put("00:15", "quarter past midnight");
        hour.put("00:30", "half past midnight");
        hour.put("00:45", "quarter to one AM");
        hour.put("01:00", "one o'clock AM");
        hour.put("01:15", "quarter past one AM");
        hour.put("01:30", "half past one AM");
        hour.put("01:45", "quarter to two AM");
        hour.put("02:00", "two o'clock AM");
        hour.put("02:15", "quarter past two AM");
        hour.put("02:30", "half past two AM");
        hour.put("02:45", "quarter to three AM");
        hour.put("03:00", "three o'clock AM");
        hour.put("03:15", "quarter past three AM");
        hour.put("03:30", "half past three AM");
        hour.put("03:45", "quarter to four AM");
        hour.put("04:00", "four o'clock AM");
        hour.put("04:15", "quarter past four AM");
        hour.put("04:30", "half past four AM");
        hour.put("04:45", "quarter to five AM");
        hour.put("05:00", "five o'clock AM");
        hour.put("05:15", "quarter past five AM");
        hour.put("05:30", "half past five AM");
        hour.put("05:45", "quarter to six AM");
        hour.put("06:00", "six o'clock AM");
        hour.put("06:15", "quarter past six AM");
        hour.put("06:30", "half past six AM");
        hour.put("06:45", "quarter to seven AM");
        hour.put("07:00", "seven o'clock AM");
        hour.put("07:15", "quarter past seven AM");
        hour.put("07:30", "half past seven AM");
        hour.put("07:45", "quarter to eight AM");
        hour.put("08:00", "eight o'clock AM");
        hour.put("08:15", "quarter past eight AM");
        hour.put("08:30", "half past eight AM");
        hour.put("08:45", "quarter to nine AM");
        hour.put("09:00", "nine o'clock AM");
        hour.put("09:15", "quarter past nine AM");
        hour.put("09:30", "half past nine AM");
        hour.put("09:45", "quarter to ten AM");
        hour.put("10:00", "ten o'clock AM");
        hour.put("10:15", "quarter past ten AM");
        hour.put("10:30", "half past ten AM");
        hour.put("10:45", "quarter to eleven AM");
        hour.put("11:00", "eleven o'clock AM");
        hour.put("11:15", "quarter past eleven AM");
        hour.put("11:30", "half past eleven AM");
        hour.put("11:45", "quarter to twelve AM");
        hour.put("12:00", "twelve o'clock AM");
        hour.put("12:15", "quarter past twelve AM");
        hour.put("12:30", "half past twelve AM");
        hour.put("12:45", "quarter to one PM");

        hour.put("13:00", "one o'clock PM");
        hour.put("13:15", "quarter past one PM");
        hour.put("13:30", "half past one PM");
        hour.put("13:45", "quarter to two PM");
        hour.put("14:00", "two o'clock PM");
        hour.put("14:15", "quarter past two PM");
        hour.put("14:30", "half past two PM");
        hour.put("14:45", "quarter to three PM");
        hour.put("15:00", "three o'clock PM");
        hour.put("15:15", "quarter past three PM");
        hour.put("15:30", "half past three PM");
        hour.put("15:45", "quarter to four PM");
        hour.put("16:00", "four o'clock PM");
        hour.put("16:15", "quarter past four PM");
        hour.put("16:30", "half past four PM");
        hour.put("16:45", "quarter to five PM");
        hour.put("17:00", "five o'clock PM");
        hour.put("17:15", "quarter past five PM");
        hour.put("17:30", "half past five PM");
        hour.put("17:45", "quarter to six PM");
        hour.put("18:00", "six o'clock PM");
        hour.put("18:15", "quarter past six PM");
        hour.put("18:30", "half past six PM");
        hour.put("18:45", "quarter to seven PM");
        hour.put("19:00", "seven o'clock PM");
        hour.put("19:15", "quarter past seven PM");
        hour.put("19:30", "half past seven PM");
        hour.put("19:45", "quarter to eight PM");
        hour.put("20:00", "eight o'clock PM");
        hour.put("20:15", "quarter past eight PM");
        hour.put("20:30", "half past eight PM");
        hour.put("20:45", "quarter to nine PM");
        hour.put("21:00", "nine o'clock PM");
        hour.put("21:15", "quarter past nine PM");
        hour.put("21:30", "half past nine PM");
        hour.put("21:45", "quarter to ten PM");
        hour.put("22:00", "ten o'clock PM");
        hour.put("22:15", "quarter past ten PM");
        hour.put("22:30", "half past ten PM");
        hour.put("22:45", "quarter to eleven PM");
        hour.put("23:00", "eleven o'clock PM");
        hour.put("23:15", "quarter past eleven PM");
        hour.put("23:30", "half past eleven PM");
        hour.put("23:45", "quarter to twelve PM");
        return hour;
    }

    private static Set<String> months(){
        Set<String> months = new HashSet<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        return months;
    }

    private static Set<String> dow(){
        Set<String> dow = new HashSet<>();
        dow.add("Monday");
        dow.add("Tuesday");
        dow.add("Wednesday");
        dow.add("Thursday");
        dow.add("Friday");
        dow.add("Saturday");
        dow.add("Sunday");
        return dow;
    }

    private static Map<String, String> domn(){
        Map<String, String> down = new HashMap<>();
        down.put("1", "first");
        down.put("2", "second");
        down.put("3", "third");
        down.put("4", "fourth");
        down.put("5", "fifth");
        return down;
    }

    private static String randomYear(){
        return randomNumber(1970, 2099);
    }

    private static String randomMinute(){
        return randomNumber(0, 59);
    }

    private static String randomNumber(int start, int end){
        int number = start + (int)(Math.random() * ((end - start) + 1));
        return String.format("%s", number);
    }

    private static String createSample1(){
        Map<String, String> hour = hours();
        Set<String> months = months();
        Set<String> dow = dow();
        Map<String, String> down = domn();

        String entry = "at HOUR_KEY at MONTH_KEY month DOW_KEY DOWN_KEY of every month | Every DOWN_VAL DOW_VAL in MONTH_VAL at HOUR_VAL";
        String hourk = randomKey(hour.keySet());
        String monthk = randomKey(months);
        String dowk = randomKey(dow);
        String downk = randomKey(down.keySet());

        return entry
                .replaceAll("HOUR_KEY", hourk).replaceAll("HOUR_VAL", hour.get(hourk))
                .replaceAll("MONTH_KEY", monthk).replaceAll("MONTH_VAL", monthk)
                .replaceAll("DOW_KEY", dowk).replaceAll("DOW_VAL", dowk)
                .replaceAll("DOWN_KEY", downk).replaceAll("DOWN_VAL", down.get(downk));

    }

    private static String createSample2(){
        Map<String, String> hour = hours();
        Map<String, String> down = domn();
        String hourk = randomKey(hour.keySet());
        String downk = randomKey(down.keySet());
        String year = randomYear();
        String entry = "at HOUR_KEY at DOY_KEY day at YEAR_KEY year | at HOUR_VAL on DOY_VAL day in YEAR_VAL";
        return entry
                .replaceAll("HOUR_KEY", hourk).replaceAll("HOUR_VAL", hour.get(hourk))
                .replaceAll("YEAR_KEY", year).replaceAll("YEAR_VAL", year)
                .replaceAll("DOY_KEY", downk).replaceAll("DOY_VAL", down.get(downk));

    }

    private static String createSample3(){
        Map<String, String> hour = hours();
        String hourk = randomKey(hour.keySet());
        String entry = "at HOUR_KEY | at HOUR_VAL o'clock";
        return entry.replaceAll("HOUR_KEY", hourk).replaceAll("HOUR_VAL", hour.get(hourk));
    }

    private static String createSample4(){
        Map<String, String> hour = hours();
        Set<String> dow = dow();
        String hourk = randomKey(hour.keySet());
        String dowk1 = randomKey(dow);
        String dowk2 = randomKey(dow);
        while(dowk1.equals(dowk2)){
            dowk2 = randomKey(dow);
        }

        String entry = "at HOUR_KEY every day between DOW_KEY1 and DOW_KEY2 | every day at HOUR_VAL between DOW_VAL1 and DOW_VAL2";
        return entry
                .replaceAll("HOUR_KEY", hourk).replaceAll("HOUR_VAL", hour.get(hourk))
                .replaceAll("DOW_KEY1", dowk1).replaceAll("DOW_VAL1", dowk1)
                .replaceAll("DOW_KEY2", dowk2).replaceAll("DOW_VAL2", dowk2);
    }

    public static String createSample5(){
        Map<String, String> hour = hours();
        Set<String> dow = dow();
        String hourk1 = randomKey(hour.keySet());
        String hourk2 = randomKey(hour.keySet());
        while(hourk1.equals(hourk2)){
            hourk2 = randomKey(hour.keySet());
        }
        String dowk1 = randomKey(dow);
        String dowk2 = randomKey(dow);
        while(dowk1.equals(dowk2)){
            dowk2 = randomKey(dow);
        }
        String entry = "every second between 0 and 59 every minute between HOUR_KEY1 and HOUR_KEY2 every day between DOW_KEY1 and DOW_KEY2 | every minute between HOUR_VAL1 and HOUR_VAL2 between DOW_VAL1 and DOW_VAL2";
        return entry
                .replaceAll("HOUR_KEY1", hourk1).replaceAll("HOUR_VAL1", hour.get(hourk1))
                .replaceAll("HOUR_KEY2", hourk2).replaceAll("HOUR_VAL2", hour.get(hourk2))
                .replaceAll("DOW_KEY1", dowk1).replaceAll("DOW_VAL1", dowk1)
                .replaceAll("DOW_KEY2", dowk2).replaceAll("DOW_VAL2", dowk2);
    }

    public static String createSample6(){
        Map<String, String> hour = hours();
        Set<String> dow = dow();
        String minute = randomMinute();
        String hourk1 = randomKey(hour.keySet());
        String hourk2 = randomKey(hour.keySet());
        while(hourk1.equals(hourk2)){
            hourk2 = randomKey(hour.keySet());
        }
        String dowk1 = randomKey(dow);
        String dowk2 = randomKey(dow);
        while(dowk1.equals(dowk2)){
            dowk2 = randomKey(dow);
        }
        String entry = "at MIN_KEY minute every hour between HOUR_KEY1 and HOUR_KEY2 every day between DOW_KEY1 and DOW_KEY2 | at minute MIN_VAL every day between DOW_VAL1 and DOW_VAL2 between HOUR_VAL1 and HOUR_VAL2";
        return entry
                .replaceAll("MIN_KEY", minute).replaceAll("MIN_VAL", minute)
                .replaceAll("HOUR_KEY1", hourk1).replaceAll("HOUR_VAL1", hour.get(hourk1))
                .replaceAll("HOUR_KEY2", hourk2).replaceAll("HOUR_VAL2", hour.get(hourk2))
                .replaceAll("DOW_KEY1", dowk1).replaceAll("DOW_VAL1", dowk1)
                .replaceAll("DOW_KEY2", dowk2).replaceAll("DOW_VAL2", dowk2);
    }

    private static String randomKey(Set<String>keys){
        int idx = (int)Math.round(Math.random()*(keys.size()-1));
        return new ArrayList<>(keys).get(idx);
    }

    private static String transform(Map<String, String> hours, String string){
        for(Map.Entry<String, String> entry : hours.entrySet().stream().sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey())).collect(Collectors.toList())){
            string = string.replaceAll(entry.getKey(), entry.getValue());
        }
        return string;
    }
}
