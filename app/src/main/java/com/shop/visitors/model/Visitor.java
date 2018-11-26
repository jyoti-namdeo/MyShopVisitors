package com.shop.visitors.model;

public final class Visitor {
    private int id;
    private String name;
    private long arriveTime;
    private long leaveTime;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getArriveTime() {
        return arriveTime;
    }

    public long getLeaveTime() {
        return leaveTime;
    }
}
