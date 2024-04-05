package mattiaconsiglio.u5w1d4.dao;

import lombok.extern.slf4j.Slf4j;
import mattiaconsiglio.u5w1d4.entities.Food;
import mattiaconsiglio.u5w1d4.exceptions.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FoodService {
    @Autowired
    private FoodsDAO foodsDAO;

    public Food save(Food food) {
        Food foundFood = null;
        try {
            foundFood = findFood(food);
            if (foundFood.getClass().equals(food.getClass())) {
                log.info(food.getClass().getSimpleName() + " with name " + food.getName() + " that has " + food.getCalories() + " calories and it's price is " + food.getPrice() + "€ already exists");
                return foundFood;
            }
        } catch (RecordNotFoundException e) {
            food = foodsDAO.save(food);
            log.info(food.getClass().getSimpleName() + " with name " + food.getName() + " that has " + food.getCalories() + " calories and it's price is " + food.getPrice() + "€ saved");
        }
        return food;
    }


    public Food findFood(Food food) {
        return foodsDAO.findFirstByNameAndCaloriesAndPrice(food.getName(), food.getCalories(), food.getPrice()).orElseThrow(() -> new RecordNotFoundException("Food with name " + food.getName() + " that has " + food.getCalories() + " calories and it's price is " + food.getPrice() + "€ not found"));
    }

    public List<Food> findAll() {
        return foodsDAO.findAll();
    }

    public List<Food> findByName(String name) {
        return foodsDAO.findByName(name);
    }

    public List<Food> findByCalories(int calories) {
        return foodsDAO.findByCalories(calories);
    }

    public List<Food> findByPrice(double price) {
        return foodsDAO.findByPrice(price);
    }


}
