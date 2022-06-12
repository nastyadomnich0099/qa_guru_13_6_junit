package junit;

import com.google.common.reflect.ClassPath;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleJUnit {
    public static void main(String[] args) throws Exception {
        Set<? extends Class<?>> classes = ClassPath.from(ClassLoader.getPlatformClassLoader())
                .getAllClasses()
                .stream()
                .map(classInfo -> classInfo.load())
                .collect(Collectors.toSet());

        for (Class<?> aClass : classes){
            Method[] methods = aClass.getDeclaredMethods();
            for (Method method : methods){
               Test testAnnotation = method.getAnnotation(Test.class);
                if (testAnnotation != null){
                    method.setAccessible(true);
                    try{
                        method.invoke(aClass.getDeclaredConstructor().newInstance());
                    }catch (Throwable t){
                        if (AssertionError.class.isAssignableFrom(t.getCause().getClass())){
                            System.out.println("Test" + method.getName() + "yellow");
                        }else{
                            System.out.println("Test" + method.getName() + "red");
                        }
                        continue;
                    }
                    System.out.println("Test" + method.getName() + "green");
                }

            }
        }


    }
}
