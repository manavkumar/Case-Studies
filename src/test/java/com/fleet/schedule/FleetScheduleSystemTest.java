package com.fleet.schedule;

import com.fleet.objects.Charger;
import com.fleet.objects.Truck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class FleetScheduleSystemTest {

    private List<Truck> trucks;
    private List<Charger> chargers;
    private int timePeriod;

    @BeforeEach
    void setUp() {
        // Initialize the trucks, chargers, and timePeriod before each test
        trucks = Arrays.asList(
                new Truck(1, 150, 90),
                new Truck(2, 100, 60),
                new Truck(3, 90, 30),
                new Truck(4, 70, 50),
                new Truck(5, 70, 60)
        );

        chargers = Arrays.asList(
                new Charger(1, 5),
                new Charger(2, 10)
        );

        timePeriod = 8;
    }

    @Test
    void testGenerateChargingSchedule() {
        Map<Integer, List<Integer>> schedule = ChargingSchedule.generateChargingSchedule(trucks, chargers, timePeriod);
        // Verify the schedule for each charger
        Assertions.assertEquals(Arrays.asList(3), schedule.get(1));
        Assertions.assertEquals(Arrays.asList(5, 4,2,1), schedule.get(2));
    }

    @Test
    void testGenerateChargingSchedule_AllTrucksFullyCharged() {
        // Set up trucks with full charge
        trucks.get(0).setCurrentCharge(150);
        trucks.get(1).setCurrentCharge(100);
        trucks.get(2).setCurrentCharge(90);
        trucks.get(3).setCurrentCharge(70);
        trucks.get(4).setCurrentCharge(70);

        Map<Integer, List<Integer>> schedule = ChargingSchedule.generateChargingSchedule(trucks, chargers, timePeriod);

        Assertions.assertEquals(Arrays.asList(), schedule.get(1));
        Assertions.assertEquals(Arrays.asList(), schedule.get(2));
    }
}

