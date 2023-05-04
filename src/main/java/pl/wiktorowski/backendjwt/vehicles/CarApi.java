package pl.wiktorowski.backendjwt.vehicles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wiktorowski.backendjwt.items.Book;

import java.util.ArrayList;
import java.util.List;

@RestController

public class CarApi {

    @GetMapping("/api/cars")

    public List<Car> get() {

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Fiat", "Panda", 2010, "red"));
        carList.add(new Car("Skoda", "Fabia", 2020, "green"));
        carList.add(new Car("BMW", "X6", 2018, "blue"));
        carList.add(new Car("BMW", "X5", 2015, "white"));
        carList.add(new Car("BMW", "Z4", 2022, "black"));


        return carList;


    }
}
