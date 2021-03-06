package java2;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

public class ReflectionTest {

	Set<String> testSet;

	public static void main(String args[]) throws NoSuchFieldException,
			SecurityException {
		ClassTest();
	}

	public static void ClassTest() {

		Class type = null;
		try {
			type = Class
					.forName("database.model.data.bussinesProcessManagement.ProcessActivity");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	private static void testElementTypeOfSet() throws NoSuchFieldException,
			SecurityException {

		ReflectionTest.class.getDeclaredFields();

		Field field = ReflectionTest.class.getDeclaredField("testSet");

		Type genericFieldType = field.getGenericType();

		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			for (Type fieldArgType : fieldArgTypes) {
				Class fieldArgClass = (Class) fieldArgType;
				System.out.println("fieldArgClass = " + fieldArgClass);
			}
		}

	}
}
