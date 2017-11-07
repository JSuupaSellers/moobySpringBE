package com.example.demo.entity;

/**
 * Created by Joshua on 10/29/2017.
 */
public class FriendUpdatedResponse {
    private boolean isAdded;
    private boolean isRemoved;

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }
}
