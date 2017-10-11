package ua.nure.draban.Practice6.part3;

import java.util.HashSet;
import java.util.Set;

public class Parking {
    private static final int MAX_SEATS = 20;
    private Object[] places = new Object[MAX_SEATS];
    private Set<Object> cars = new HashSet<>();

    public boolean carParking(int index, Object car) {
        boolean result = false;
        boolean newCar = (car != null) && (!cars.contains(car));
        boolean freePlace = (places[index] == null);
        if (newCar) {
            if (freePlace) {
                places[index] = car;
                cars.add(car);
                System.out.println("car is parking");
                result = true;
            } else {
                for (int i = index + 1; i < places.length; i++) {
                    carParking(i, car);
                }
            }
        }
        return result;
    }

    public boolean carGoOut(Object car) {
        boolean result = false;
        if (car != null && cars.contains(car)) {
            for (int i = 0; i < places.length; i++) {
                if (car.equals(places[i])) {
                    places[i] = null;
                    cars.remove(car);
                    System.out.println("car is go out");
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public void stateParking() {
        for (Object iter : places) {
            System.out.print(iter + " ");
        }
        System.out.println();
    }
}

class Car {
    private String name;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return !(name != null ? !name.equals(car.name) : car.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }
}
