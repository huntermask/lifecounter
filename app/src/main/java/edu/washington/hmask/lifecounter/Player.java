package edu.washington.hmask.lifecounter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huntermask on 4/18/15.
 */
public class Player implements Parcelable {
    private int lifeCount = 20;
    private String name = "Player X";

    public Player(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int lifeCount) {
        this.name = name;
        this.lifeCount = lifeCount;
    }

    public Player(Parcel in) {
        String[] data = new String[2];

        in.readStringArray(data);

        this.lifeCount = Integer.parseInt(data[0]);
        this.name = data[1];
    }

    public Player() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {Integer.toString(this.lifeCount), this.name});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public int getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
