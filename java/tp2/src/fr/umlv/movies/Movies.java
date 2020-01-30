package fr.umlv.movies;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Movies {
	
/* Question 3
	
	public static void main(String[] args) {
		Path path = Path.of("./movies.txt");
		try {
			Stream<String> s = Files.lines(path);
			s.forEach(l -> System.out.println(l));
		} catch (IOException e) {
			System.out.println("error : " + e);
		}
	}
*/
	
/* Question 4
	
	public static void main(String[] args) {
		Path path = Path.of("./movies.txt");
		try {
			Stream<String> s = Files.lines(path);
			s.forEach(l -> System.out.println(l));
			s.close();
		} catch (IOException e) {
			System.out.println("error : " + e);
		}
	}
*/
	
/* Question 5

    public static void main(String[] args) throws IOException {
		var path = Path.of("./movies.txt");
		Stream<String> s = null;
		
		try {
			s = Files.lines(path);
		} catch (IOException e) {
			return;
		} finally {
			s.close();
		}
	}
*/

/* Question 6&7

	public static void main(String[] args) throws IOException {
		var path = Path.of("./movies.txt");
		Stream<String> s;
		
		try {
			s = Files.lines(path);
		} catch (IOException e) {
			return;
		}
		
		try {
			s.forEach(l -> System.out.println(l));
		} finally {
			s.close();
		}
	}
*/
	
/* Question 8

	public static void main(String[] args) throws IOException {
		var path = Path.of("./movies.txt");
				
		try(Stream<String> s = Files.lines(path)) {
			s.forEach(l -> System.out.println(l));
		}
	}
*/

/* Exercice 2 */

/* Question 1
	
	public Map<String, List<String>> actorsByMovie(Path path) throws IOException {
		try (var lines = Files.lines(path)) {
			var moviesMap = new HashMap<String, List<String>>();
			lines.forEach(line -> {
				var tab = line.split(";");
				moviesMap.put(tab[0], Arrays.stream(tab).skip(1).collect(Collectors.toList()));
			});
			return moviesMap;
		}
	}
*/
	
	public static Map<String, List<String>> actorsByMovie(Path path) throws IOException {
		try (var lines = Files.lines(path)) {
			return lines
					.map(line -> line.split(";"))
					.collect(Collectors.toUnmodifiableMap(
							tab -> tab[0],
							tab -> Arrays.stream(tab).skip(1).collect(Collectors.toList())
					));
		}
	}

	public static int numberOfUniqueActors(Map<String, List<String>> m) {
		var actors = m.entrySet().stream().flatMap(movie -> movie.getValue());
	}

	public static void main(String[] args) {
		Path path = Path.of("./movies.txt");

		try {
			var map = Movies.actorsByMovie(path);
			System.out.println(numberOfUniqueActors(map));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
