package com.fleet.objects;

public class Truck {
    private int id;
    private int batteryCapacity;
    private int currentCharge;

    public Truck(int id, int batteryCapacity, int currentCharge) {
        this.id = id;
        this.batteryCapacity = batteryCapacity;
        this.currentCharge = currentCharge;
    }

    public int getId() {
        return id;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public int getCurrentCharge() {
        return currentCharge;
    }
    public void setCurrentCharge(int currentCharge) {
        this.currentCharge = currentCharge;
    }

}
