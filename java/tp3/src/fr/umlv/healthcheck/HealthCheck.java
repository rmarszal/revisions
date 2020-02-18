package fr.umlv.healthcheck;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class HealthCheck {

    @FunctionalInterface
    public interface URIFinder {

        Optional<URI> find();

//        static URIFinder fromArguments(String[] args) {
//            Objects.requireNonNull(args);
//            return () -> {
//                if (args.length == 0) {
//                    return Optional.empty();
//                }
//                try {
//                    return Optional.of(URI.create(args[0]));
//                } catch (IllegalArgumentException e) {
//                    return Optional.empty();
//                }
//            };
//        }

        static URIFinder fromArguments(String[] args) {
            Objects.requireNonNull(args);
            return () -> {
                try {
                    return Optional.of(args)
                            .filter(a -> a.length != 0)
                            .map(a -> URI.create(a[0]));
                } catch (IllegalArgumentException e) {
                    return Optional.empty();
                }
            };
        }

        static URIFinder fromURI(String s) {
            Objects.requireNonNull(s);
            return () -> {
                try {
                    return Optional.of(URI.create(s));
                } catch (IllegalArgumentException e) {
                    return Optional.empty();
                }
            };
        }

        static URIFinder fromMapGetLike(String s, Function<Map<String, String>, String> f) {
            Objects.requireNonNull(s);
            Objects.requireNonNull(f);
            return () -> {
                try {
                    return Optional.of();
                } catch (IllegalArgumentException e) {
                    return Optional.empty();
                }
            }
        }

        default URIFinder or(URIFinder fallBackURIFinder) {
            Objects.requireNonNull(fallBackURIFinder);
            return this.find().isPresent() ? this : fallBackURIFinder;
        }
    }

    public static boolean healthCheck(URI uri) throws InterruptedException {
        var client = HttpClient.newBuilder().build();
        var request = HttpRequest.newBuilder(uri).build();
        try {
            var code = client.send(request, HttpResponse.BodyHandlers.discarding()).statusCode();
            return code == 200;
        } catch (IOException e) {
            return false;
        }
    }

}
