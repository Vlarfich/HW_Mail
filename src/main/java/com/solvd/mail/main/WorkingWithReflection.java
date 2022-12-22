package com.solvd.mail.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkingWithReflection {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(WorkingWithFiles.class);

        WorkingWithReflection workingWithReflection = new WorkingWithReflection();
        Set<Class> classes = workingWithReflection.findAllClassesUsingClassLoader("com.solvd.mail.main");

        for (Class c : classes) {
            if(c.getSimpleName().equals("PostOfficeGenerator"))
                continue;
            //Class<?> c = Class.forName("com.solvd.mail.main.PostOfficeGenerator");
            logger.info("\n\n\t****  " + c.getSimpleName() + " ****\n");

            Field[] fields = c.getDeclaredFields();
            for (Field f : fields) {
                logger.info(Modifier.toString(f.getModifiers()) + "  " + f.getType().getSimpleName() + "  " + f.getName());
            }
            logger.info("");

            Constructor[] constructors = c.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                String parameters = Arrays.stream(constructor.getParameters()).map(x -> x.getType().getSimpleName()).collect(Collectors.joining(", "));
                logger.info(Modifier.toString(constructor.getModifiers()) + "  " + c.getSimpleName() + "  (" +
                        parameters + ")");
            }
            logger.info("");


            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                String parameters = Arrays.stream(method.getParameters()).map(x -> x.getType().getSimpleName()).collect(Collectors.joining(", "));
                logger.info(Modifier.toString(method.getModifiers()) + "  " + method.getReturnType().getSimpleName()
                        + "  " + method.getName() + "  (" + parameters + ")");
            }
        }

        try {
            logger.info('\n');
            Class c = Class.forName("com.solvd.mail.main.PostOfficeGenerator");
            Object postOfficeGen = c.newInstance();
            logger.info(postOfficeGen);

            Object postOffice = c.getMethod("getPostOffice").invoke(c.newInstance());
            logger.info(postOffice);


            Class main = Class.forName("com.solvd.mail.main.Main");
            Object menu = main.newInstance();
            main.getMethod("Menu").invoke(menu);


        } catch (ClassNotFoundException e) {
            logger.fatal(e.getMessage());
        } catch (InstantiationException e) {
            logger.fatal(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.fatal(e.getMessage());
        } catch (InvocationTargetException e) {
            logger.fatal(e.getMessage());
        } catch (NoSuchMethodException e) {
            logger.fatal(e.getMessage());
        }

    }


    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
