package edu.washington.hmask.lifecounter;

/**
 * Created by huntermask on 4/18/15.
 */
public class Player {
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

    public Player() {
    }

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
