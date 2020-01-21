package fr.umlv.rental;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarRental {

    private final ArrayList<Vehicule> rentedCars;

    public CarRental() {
        this.rentedCars = new ArrayList<Vehicule>();
    }

//	@Override
//	public String toString() {
//		if (rentedCars.size() == 0) {
//			return "";
//		}
//		StringBuilder str = new StringBuilder();
//		for (Vehicule v : rentedCars) {
//			str.append(v.toString());
//			str.append("\n");
//		}
//		return str.substring(0, str.length() - 1);
//	}

    @Override
    public String toString() {
        return rentedCars.stream()
                .map(v -> v.toString())
                .collect(Collectors.joining("\n"));
    }

    public void add(Vehicule v) {
        this.rentedCars.add(Objects.requireNonNull(v));
    }

    public void remove(Vehicule v) {
        if (v == null) {
            throw new NullPointerException();
        }
        if (!rentedCars.contains(v)) {
            throw new IllegalStateException("Cannot remove a vehicule which is not in the list");
        }
        this.rentedCars.remove(v);
    }

    public List<Vehicule> findAllByYear(int year) {
        return rentedCars.stream()
                .filter(v -> v.getYear() == year)
                .collect(Collectors.toList());
    }

    public int insuranceCostAt(int year) {
        int cost = 0;
        for (Vehicule v : rentedCars) {
            if (year - v.getYear() < 0) {
                throw new IllegalArgumentException("Vehicule year is superior than specified year");
            }
            if (v instanceof Car) {
                cost += (year - v.getYear()) < 10 ? 200 : 500;
            } else if (v instanceof Camel) {
                cost += (year - v.getYear()) * 100;
            } else {
                continue;
            }
        };
        return cost;
    }

    public Optional<Car> findACarByModel(String model) {
        if (model == null) {
            throw new NullPointerException();
        }
        return rentedCars.stream()
                .filter(v -> v instanceof Car)
                .map(car -> (Car)car)
                .filter(car -> car.getModel() == model)
                .findAny();
    }

    public static void main(String[] args) {
        var rental = new CarRental();
        rental.add(new Car("ford mustang", 2014));
        rental.add(new Camel(2010));
        System.out.println(rental.findAllByYear(2012));
    }
}
