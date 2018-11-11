package com.grachev.test;

import com.grachev.test.data.Incrementer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IncrementerTest {

    // локальный тест для проверки счетчика

    private Incrementer incrementer;

    @Before
    public void setIncrementer() throws Exception {
        this.incrementer = new Incrementer(10, 15);
    }

    @Test
    public void max() throws Exception {
        assertEquals(5, incrementer.calcNumber(10));
    }

    @Test
    public void increment() throws Exception {
        assertEquals(10, incrementer.calcNumber(0));
    }
}
