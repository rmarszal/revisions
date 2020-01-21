package fr.umlv.rental;

abstract class AbstractVehicule implements Vehicule {

    private final int year;

    public AbstractVehicule(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}
