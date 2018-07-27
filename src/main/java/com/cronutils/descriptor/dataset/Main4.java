package com.cronutils.descriptor.dataset;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.field.CronFieldName;
import com.cronutils.model.field.expression.Between;
import com.cronutils.model.field.expression.On;
import com.cronutils.model.field.value.IntegerFieldValue;
import com.cronutils.parser.CronParser;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Main4 {

        public static void main(String[] args) {
            CronParser cronParser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
            Cron cron = cronParser.parse("0 9 1-7 ? * 1-5");

            CronDescriptor descriptor = CronDescriptor.instance(Locale.UK);
            System.out.println(descriptor.describe(cron));

            String desc = "at minute MIN_VAL every day between DOW_VAL and DOW_VAL between HOUR_VAL and HOUR_VAL";
            int minute = ((On) cron.retrieveFieldsAsMap().get(CronFieldName.MINUTE).getExpression()).getTime().getValue();
            int dow1 = ((IntegerFieldValue)((Between) cron.retrieveFieldsAsMap().get(CronFieldName.DAY_OF_WEEK).getExpression()).getFrom()).getValue();
            int dow2 = ((IntegerFieldValue)((Between) cron.retrieveFieldsAsMap().get(CronFieldName.DAY_OF_WEEK).getExpression()).getTo()).getValue();
            int hour1 = ((IntegerFieldValue)((Between) cron.retrieveFieldsAsMap().get(CronFieldName.HOUR).getExpression()).getFrom()).getValue();
            int hour2 = ((IntegerFieldValue)((Between) cron.retrieveFieldsAsMap().get(CronFieldName.HOUR).getExpression()).getTo()).getValue();

            desc = desc
                    .replaceAll("MIN_VAL", ""+minute)
                    .replaceAll("DOW_VAL and DOW_VAL", String.format("%s and %s", dow(dow1), dow(dow2)))
                    .replaceAll("HOUR_VAL and HOUR_VAL", String.format("%s and %s", hour(hour1), hour(hour2)));
            System.out.println(desc);
        }

        private static String dow(int down){
            int diff = -1;
            return DayOfWeek.of(down + diff < 1 ? 7 : down + diff).getDisplayName(TextStyle.FULL, Locale.UK);
        }

        private static String hour(int hour){
            Map<String, String> hours = hours();
            return hours.get(String.format("%02d:%02d", hour, 0));
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

}
