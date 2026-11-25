package com.evstation.app;

/**
 * Main application bootstrap for VoltGrid V2 EV Charging & Reservation System.
 */
public class EVStationApp {

    public static void main(String[] args) {
        System.out.println("Initializing VoltGrid V2 Hub...");
        MenuController controller = new MenuController();
        controller.run();
    }
}
