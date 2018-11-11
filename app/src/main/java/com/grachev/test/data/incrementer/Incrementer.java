package com.grachev.test.data.incrementer;

public class Incrementer {

    // класс для расчета числа

    private int increment;
    private int maximum;

    public Incrementer (int increment, int maximum) {
        this.increment = increment;
        this.maximum = maximum;
    }

    public int calcNumber (int number) {
        // увеличиваем число на инкремент
        int newNumber = number + increment;

        if (newNumber>maximum) {
            // при прохождении через максимум
            newNumber = newNumber - maximum;
        }

        return newNumber;
    }

}
