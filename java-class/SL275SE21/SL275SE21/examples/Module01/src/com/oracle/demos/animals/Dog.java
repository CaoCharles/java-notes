package com.oracle.demos.animals;

public class Dog {

    Ball ball;

    void fetch() {
        while (ball == null) {
            keepLooking();
        }
    }

    void makeNoise() {
        if (ball != null) {
            dropBall();
        } else {
            bark(); }
    }

    void keepLooking() { }

    void dropBall() { }

    void bark() { }
}
