package emailclient;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

class DateToday {

    public static String dateTodayMD() {
        // this method returns today's date in MM/dd format

        DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd");
        LocalDateTime now = LocalDateTime.now();

        return date.format(now);
    }

    public static String dateTodayYMD() {
        // this method returns today's date in yyyy/MM/dd format

        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        return date.format(now);
    }

}
