package fr.umlv.java.inside;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.WrongMethodTypeException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExampleTests {

    @Test
    void getAStaticHelloByReflexion() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Example> c = Example.class;
        var method = c.getDeclaredMethod("aStaticHello", int.class);
        method.setAccessible(true);
        assertEquals("question 3", method.invoke(null, 3));
    }

    @Test
    void getAnInstanceHelloByReflexion() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Example> c = Example.class;
        var method = c.getDeclaredMethod("anInstanceHello", int.class);
        method.setAccessible(true);
        assertEquals("question 3", method.invoke(new Example(), 3));
    }

    @Test
    void getAStaticHelloWithLookup() throws Throwable {
        var lookup = MethodHandles.lookup();
        var privateLookup = MethodHandles.privateLookupIn(Example.class, lookup);
        var method = privateLookup.findStatic(Example.class, "aStaticHello", MethodType.methodType(String.class, int.class));
        assertEquals("question 4", (String)method.invokeExact(4));
    }

    @Test
    void getAnInstanceHelloWithLookup() throws Throwable {
        var lookup = MethodHandles.lookup();
        var privateLookup = MethodHandles.privateLookupIn(Example.class, lookup);
        var method = privateLookup.findVirtual(Example.class, "anInstanceHello", MethodType.methodType(String.class, int.class));
        assertEquals("question 5", (String)method.invokeExact(new Example(), 5));
    }

    @Test
    void insertArgumentsInAnInstanceHelloLookup() throws Throwable {
        var lookup = MethodHandles.lookup();
        var privateLookup = MethodHandles.privateLookupIn(Example.class, lookup);
        var method = privateLookup.findVirtual(Example.class, "anInstanceHello", MethodType.methodType(String.class, int.class));
        method = MethodHandles.insertArguments(method,1,6);
        assertEquals("question 6", (String)method.invokeExact(new Example()));
    }

    @Test
    void dropArgumentsInAnInstanceHelloLookup() throws Throwable {
        var lookup = MethodHandles.lookup();
        var privateLookup = MethodHandles.privateLookupIn(Example.class, lookup);
        var method = privateLookup.findVirtual(Example.class, "anInstanceHello", MethodType.methodType(String.class, int.class));
        method = MethodHandles.dropArguments(method, 1, String.class);
        assertEquals("question 7", (String)method.invokeExact(new Example(), "droppedArgument", 7));
    }

    @Test
    void getInstanceHelloWithLookupAsTypeInteger() throws Throwable {
        var lookup = MethodHandles.lookup();
        var privateLookup = MethodHandles.privateLookupIn(Example.class, lookup);
        var method = privateLookup.findVirtual(Example.class, "anInstanceHello", MethodType.methodType(String.class, int.class));
        method = method.asType(MethodType.methodType(String.class, Example.class, Integer.class));
        assertEquals("question 8", (String)method.invokeExact(new Example(), Integer.valueOf(8)));
    }

    @Test
    void getInstanceHelloWithLookupAndConstantReturnedValue() throws Throwable {
        var lookup = MethodHandles.lookup();
        var privateLookup = MethodHandles.privateLookupIn(Example.class, lookup);
        var method = privateLookup.findVirtual(Example.class, "anInstanceHello", MethodType.methodType(String.class, int.class));
        method = MethodHandles.constant(String.class, "question 9");
        assertEquals("question 9", (String)method.invokeExact());
    }

    @Test
    void getInstanceHelloWithGuardWithTest() throws Throwable {
        var lookup = MethodHandles.lookup();
        var privateLookup = MethodHandles.privateLookupIn(Example.class, lookup);
        var method = privateLookup.findVirtual(Example.class, "anInstanceHello", MethodType.methodType(String.class, int.class));
//        var target = 5;
//        var fallback = 5;
//        method = MethodHandles.guardWithTest(method, target, fallback);
        assertEquals("question 8", (String)method.invokeExact());
    }
}
