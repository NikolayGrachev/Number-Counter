package com.grachev.test.data;

/**
 * Класс для расчета значения следующего числа.
 * Создан для написания локального теста IncrementerTest @link com.grachev.test.IncrementerTest
 */
public class Incrementer {

    /**
     * Параметр инкремент, величина, которая прибавляется к текущему значению
     * */
    private int increment;
    /** Максимальное значение числа, если попытаться еще увеличить значение, то счетчик обнулится
     * */
    private int maximum;

    /**
     * Конструктор для инициализации Инкрементора
     * */
    public Incrementer (int increment, int maximum) {
        this.increment = increment;
        this.maximum = maximum;
    }


    /**
     * Функция вычисления следующего значения счетчика
     * К счетчику прибавляется инкремент, а при прохождении через максимум счетчик обнуляется
     *
     * @param number - текущее значение счетчика
     * @return измененное значение счетчика
     */
    public int calcNumber (int number) {
        // увеличиваем число на инкремент
        int newNumber = number + increment;

        if (newNumber>maximum) {
            // при прохождении через максимум
            newNumber = 0;
        }
        return newNumber;
    }

}
