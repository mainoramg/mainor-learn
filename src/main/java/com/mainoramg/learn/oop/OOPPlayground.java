package com.mainoramg.learn.oop;

import com.mainoramg.learn.oop.models.Animal;
import com.mainoramg.learn.oop.models.Dog;

public class OOPPlayground {

    public static void main(String[] args) {
        Animal animal = new Dog();
        System.out.println("animal sound: "+animal.getSound());
    }
}
