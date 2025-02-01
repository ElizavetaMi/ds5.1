package ru.netology.javaqa.javaqamnv.services;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; // Импортируем Assertions

class FreelancerServiceTest {

    @Test
    void testCalculateRestMonths() {
        FreelancerService service = new FreelancerService(); // создаем экземпляр класса

        // Пример с income = 10000, expenses = 3000, threshold = 20000
        int result = service.calculateRestMonths(10000, 3000, 20000);
        assertEquals(3, result, "The number of rest months should be 3");

        // Пример с income = 100000, expenses = 60000, threshold = 150000
        result = service.calculateRestMonths(100000, 60000, 150000);
        assertEquals(2, result, "The number of rest months should be 2");


    }
}

