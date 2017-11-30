package com.example.hunter.tupoc;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.text.DateFormat;

/**
 * Created by Hunter on 11/20/2017.
 */

public class Task {

    private String taskName;
    private Date dueDate;
    private int priority;
    private DateFormat df;

    public Task(String name, String date, String priority) throws ParseException
    {
        taskName = name;
        dueDate = df.parse(date);
        this.priority = Integer.parseInt(priority);
    }


    public String getTaskName()
    {
        return taskName;
    }

    public Date getDueDate()
    {
        return dueDate;
    }

    public int getPriority()
    {
        return priority;
    }



}