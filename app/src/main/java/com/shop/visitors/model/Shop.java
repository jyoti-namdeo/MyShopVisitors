package com.shop.visitors.model;

import java.util.List;

public final class Shop {
    private int id;
    private String name;
    private long openTime;
    private long closeTime;
    private List<Visitor> visitors;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getOpenTime() {
        return openTime;
    }

    public long getCloseTime() {
        return closeTime;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }
}
