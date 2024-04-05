package mattiaconsiglio.u5w1d4.dao;

import mattiaconsiglio.u5w1d4.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FoodsDAO extends JpaRepository<Food, UUID> {
    public List<Food> findByName(String name);

    public List<Food> findByCalories(int calories);


    public List<Food> findByPrice(double price);


    public Optional<Food> findFirstByNameAndCaloriesAndPrice(String name, int calories, double price);
}
