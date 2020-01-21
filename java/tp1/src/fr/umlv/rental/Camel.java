package fr.umlv.rental;

import java.util.Objects;

public class Camel extends AbstractVehicule {

    public Camel(int birthDate) {
        super(birthDate);
    }

    @Override
    public String toString() {
        return "camel " + super.getYear();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Camel)) {
            return false;
        }
        Camel c = (Camel)obj;
        return c.getYear() == getYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYear());
    }
}
