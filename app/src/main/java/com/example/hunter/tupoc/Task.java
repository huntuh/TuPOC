package com.example.hunter.tupoc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hunter on 11/20/2017.
 */

public class Task implements Parcelable{

    private String taskName;
    private String dueDate;
    private int priority;

    public Task(String name, String date, String priority)
    {
        taskName = name;
        dueDate = date;
        this.priority = Integer.parseInt(priority);
    }

    protected Task(Parcel in) {
        taskName = in.readString();
        dueDate = in.readString();
        priority = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(taskName);
        parcel.writeString(dueDate);
        parcel.writeInt(priority);
    }

    public String getTaskName()
    {
        return taskName;
    }

    public String getDueDate()
    {
        return dueDate;
    }

    public int getPriority()
    {
        return priority;
    }



}