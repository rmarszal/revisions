package fr.umlv.rental;

import java.util.Objects;

public class Car extends AbstractVehicule {
	
	private String model;
	
	public Car(String model, int manufacturingYear) {
		super(manufacturingYear);
		this.model = Objects.requireNonNull(model);
	}
	
	@Override
	public String toString() {
		return this.model + " " + super.getYear();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Car)) {
			return false;
		}
		Car car = (Car)obj;
		return car.getYear() == getYear() && car.model.equals(model);
	}
	
	@Override
	public int hashCode() {
		return getYear() ^ model.hashCode();
	}
	
	public String getModel() {
		return this.model;
	}
}