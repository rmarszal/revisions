package fr.umlv.java.inside;

import java.util.Objects;
import org.junit.jupiter.api.Test;

import fr.umlv.java.inside.JsonUtils.JSONProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for JsonUtils methods.
 */
public class JsonUtilsTest {

	static class Alien {
		private final String planet;
		private final int age;

		public Alien(String planet, int age) {
			if (age <= 0) {
				throw new IllegalArgumentException("Too young...");
			}
			this.planet = Objects.requireNonNull(planet);
			this.age = age;
		}

		@JSONProperty("getPlanet")
		public String getPlanet() {
			return planet;
		}
		
		@JSONProperty("getAge")
		public int getAge() {
			return age;
		}
	}
	
	static class Person {
		
		private final String firstName;
		private final String lastName;

		public Person(String firstName, String lastName) {
		    this.firstName = Objects.requireNonNull(firstName);
		    this.lastName = Objects.requireNonNull(lastName);
		}
		
		@JSONProperty("getFirstName")
		public String getFirstName() {
		    return firstName;
		}
		
		@JSONProperty("getLastName")
		public String getLastName() {
		    return lastName;
		}
	}
	
	@Test
    void test1() {
        assertEquals("{firstName : Rémi,lastName : Marszal}", JsonUtils.toJSON(new Person("Rémi", "Marszal")));
	}
	
	@Test
    void test2() {
        assertEquals("{age : 2048,planet : Tatooin}", JsonUtils.toJSON(new Alien("Tatooin", 2048)));
	}
}
