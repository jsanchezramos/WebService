package helpers;

import model.Time;

import java.sql.Timestamp;

public class TokenHelper {

    private static final int MAX_AUTH = 2;

    public static Time getTimeObject(){
        return new Time(new Timestamp(System.currentTimeMillis()));
    }

    public static int checkDifferentsSecons(Timestamp timestamp){
        Timestamp timestamp2 = getCurrent();

        long milliseconds = timestamp2.getTime() - timestamp.getTime();
        int seconds = (int) milliseconds / 1000;
        seconds = (seconds % 3600) % 60;
        return seconds;
    }

    public static int checkDifferentsMinutes(Timestamp timestamp){
        Timestamp timestamp2 = getCurrent();

        long milliseconds = timestamp2.getTime() - timestamp.getTime();
        int seconds = (int) milliseconds / 1000;
        int minutes = (seconds % 3600) / 60;
        return minutes;
    }

    public static Timestamp getCurrent(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static Boolean checkMaxAuth(int numAuth){
        return numAuth <= MAX_AUTH;
    }
}
