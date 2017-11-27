package com.fsd.coreservices.demo.time;

import com.fsd.coreservices.demo.entity.Custom_Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Calendar;

/**
 */
@Service
public class ClockSerivceImpl implements ClockService {
    @Autowired
    Custom_Clock customClock;

    /**
     * @return The calendar instance.
     */
    @Override
    public Calendar getCalendar() {
        return customClock.getCalendar();
    }

    /**
     * @param dateStr The date
     * @throws ParseException The parsing exception
     */
    @Override
    public void setCalendar(String dateStr) throws ParseException {
        customClock.setCalendar(dateStr);
    }

    /**
     * resets the calendar.
     */
    @Override
    public void resetCalendar() {
        customClock.resetCalendar();
    }

    /**
     * Displays the current time
     */
    @Override
    public void displayCurrentTime() {
        System.out.println("The set time for LMS is: " + customClock.getCalendar().getTime());
    }
}
