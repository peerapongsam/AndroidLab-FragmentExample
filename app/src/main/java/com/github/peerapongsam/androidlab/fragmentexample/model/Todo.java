package com.github.peerapongsam.androidlab.fragmentexample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by peerapong on 5/22/17.
 */

public class Todo implements Parcelable {

    /**
     * userId : 1
     * id : 1
     * title : delectus aut autem
     * completed : false
     */

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeByte(this.completed ? (byte) 1 : (byte) 0);
    }

    public Todo() {
    }

    protected Todo(Parcel in) {
        this.userId = in.readInt();
        this.id = in.readInt();
        this.title = in.readString();
        this.completed = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel source) {
            return new Todo(source);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    @Override
    public String toString() {
        return "Todo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
