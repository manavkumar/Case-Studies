package com.fleet.schedule;

import com.fleet.objects.Charger;
import com.fleet.objects.Truck;

import java.util.*;

public class ChargingSchedule {
    public static Map<Integer, List<Integer>> generateChargingSchedule(List<Truck> trucks,
                                                                       List<Charger> chargers,
                                                                       int timePeriod) {
        Map<Integer, List<Integer>> schedule = new HashMap<>();

        // Sort trucks based on remaining charging time in ascending order
        trucks.sort(Comparator.comparingDouble(truck -> truck.getBatteryCapacity()-truck.getCurrentCharge()));

        // sort chargers in decreasing order of charging rate
        chargers.sort(Comparator.comparingInt(Charger::getChargingRate).reversed());

        // Take set of trucks to avoid assigned duplicate truck to other chargers
        Set<Integer> assignedTruckIds = new HashSet<>();

        for (Charger charger : chargers) {
            int remainingTime = timePeriod;
            List<Integer> truckIds = new ArrayList<>();

            for (Truck truck : trucks) {
                if (assignedTruckIds.contains(truck.getId())) {
                    continue; // Skip already assigned trucks
                }

                int chargingTime = calculateChargingTime(truck, charger, remainingTime);
                if (chargingTime > 0 && remainingTime >= chargingTime) {
                    truckIds.add(truck.getId());
                    remainingTime -= chargingTime;
                    assignedTruckIds.add(truck.getId());

                    // if remaining time will be less or zero it means charger capacity is already utilized
                    if (remainingTime <= 0) {
                        break;
                    }
                }
            }

            schedule.put(charger.getId(), truckIds);
        }
        return schedule;
    }

    private static int calculateChargingTime(Truck truck, Charger charger, int availableTime) {
        double requiredTime = getRemainingChargingTime(truck,charger.getChargingRate());
        return Math.min((int) Math.ceil(requiredTime), availableTime);
    }

    public static double getRemainingChargingTime(Truck truck, int chargingRate) {
        double remainingCapacity = truck.getBatteryCapacity() - truck.getCurrentCharge();
        return remainingCapacity / chargingRate;
    }
}
