package com.sintraqos.portfolioproject.Statics;

import lombok.Getter;

/**
 * Use for storing time, since this gives more flexibility for what's needed
 */
@Getter
public class Time {
    private int hour;
    private int minute;

    /**
     * Create a new Time object with specified times
     * @param hour the current hour
     * @param minute the current minute
     */
    public Time(int hour, int minute) {
        addHour(hour);
        addMinute(minute);
    }

    /**
     * Create a new Time object with specified times
     * @param time add the time of this object to the current time
     */
    public void addTime(Time time){
        this.hour += time.hour;
        this.minute += time.minute;
    }

    /**
     * Add the given hours to the current stored hours
     * @param hour add the hour(s) given to the current hours
     */
    public void addHour(int hour){
        this.hour += hour;
    }

    /**
     * Add the given minutes to the current stored minutes
     * @param minute add the minute(s) given to the current minutes,
     *               if the stored minutes reaches, or exceeds 60,
     *               add an hour to the stored time and add the remaining minutes
     */
    public void addMinute(int minute){
        this.minute += minute;

        if(this.minute >= 60){
            int remainingMinutes = this.minute - 60;
            this.minute = 0;
            addHour(1);
            addMinute(remainingMinutes);
        }
    }

    @Override
    public String toString() {
        return hour + ":" + minute;
    }
}
