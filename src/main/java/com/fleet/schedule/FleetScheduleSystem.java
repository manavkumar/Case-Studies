package com.fleet.schedule;


import com.fleet.objects.Charger;
import com.fleet.objects.Truck;

import java.util.*;


public class FleetScheduleSystem {
    public static void main(String[] args) {

        // List of Trucks for charging
        List<Truck> trucks = new ArrayList<>();
        trucks.add(new Truck(1, 150, 90));   // 60
        trucks.add(new Truck(2, 100, 60));   //40
        trucks.add(new Truck(3, 90, 30));    // 60
        trucks.add(new Truck(4, 70, 50));    //20
        trucks.add(new Truck(5, 70, 60));   //10

        // List of Chargers available
        List<Charger> chargers = new ArrayList<>();
        chargers.add(new Charger(1, 5));
        chargers.add(new Charger(2, 10));

        int timePeriod = 8; // Number of hours available for charging

        // Algorithm to generateChargingSchedule
        Map<Integer, List<Integer>> schedule = ChargingSchedule.generateChargingSchedule(trucks, chargers, timePeriod);

        // Print the schedule
        for (Map.Entry<Integer, List<Integer>> entry : schedule.entrySet()) {
            int chargerId = entry.getKey();
            List<Integer> truckIds = entry.getValue();
            System.out.println(chargerId + ": " + truckIds);
        }
    }
}