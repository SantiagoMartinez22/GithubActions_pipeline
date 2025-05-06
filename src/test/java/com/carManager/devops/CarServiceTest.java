package com.carManager.devops;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    void testSaveCar() {
        Car testCar = new Car();
        testCar.setMake("Tesla");
        testCar.setModel("Model 3");
        testCar.setYear(2024);

        when(carRepository.save(testCar)).thenReturn(testCar);

        Car savedCar = carService.saveCar(testCar);

        assertEquals("Tesla", savedCar.getMake());
        verify(carRepository, times(1)).save(testCar);
    }

    @Test
    void testGetAllCars() {
        when(carRepository.findAll()).thenReturn(List.of(new Car(), new Car()));

        List<Car> cars = carService.getAllCars();

        assertEquals(2, cars.size());
        verify(carRepository, times(1)).findAll();
    }
}
