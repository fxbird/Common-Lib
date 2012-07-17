package com.xdg.test;

import com.xdg.util.ReflectUtil;
import junit.framework.TestCase;

public class TestReflectUtil extends TestCase {

    public void testGetFieldType(){
        Class type=ReflectUtil.getFieldType(Person.class,"age");
        System.out.println(type==int.class);

    }
}

class Person{
    private String name;
    private int age;
}