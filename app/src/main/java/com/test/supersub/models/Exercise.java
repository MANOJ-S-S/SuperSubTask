package com.test.supersub.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exercise {

    @SerializedName("restTime")
    @Expose
    private Integer restTime;
    @SerializedName("sets")
    @Expose
    private Integer sets;
    @SerializedName("reps")
    @Expose
    private Integer reps;
    @SerializedName("difficulty")
    @Expose
    private Integer difficulty;
    @SerializedName("calories")
    @Expose
    private Integer calories;
    @SerializedName("increments")
    @Expose
    private Increments increments;

    public Integer getRestTime() {
        return restTime;
    }

    public void setRestTime(Integer restTime) {
        this.restTime = restTime;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Increments getIncrements() {
        return increments;
    }

    public void setIncrements(Increments increments) {
        this.increments = increments;
    }

}