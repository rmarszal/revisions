package fr.umlv.java.inside;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class JsonUtils {
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface JSONProperty {
		String value() default "";
	}

	public static String toJSON(Object o) {
		return Arrays.stream(o.getClass().getMethods())
				//.filter(method -> method.getName().startsWith("get"))
				//.filter(not(method -> method.getDeclaringClass() == Object.class))
				.filter(method -> method.isAnnotationPresent(JSONProperty.class))
				.sorted(Comparator.comparing(Method::getName))
				.map(method -> propertyName(method.getName()) + " : " + callGetter(o, method))
				.collect(Collectors.joining(",", "{", "}"));
	}
	
	// Should be private, but have to be public for tests.
	public static Object callGetter(Object o, Method getter) {
		try {
			return getter.invoke(o);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			var cause = e.getCause();
			if (cause instanceof RuntimeException) {
				throw (RuntimeException)cause;
			}
			throw new UndeclaredThrowableException(cause);
		}
	}
	
	private static String propertyName(String name) {
		return Character.toLowerCase(name.charAt(3)) + name.substring(4);
	}
}
