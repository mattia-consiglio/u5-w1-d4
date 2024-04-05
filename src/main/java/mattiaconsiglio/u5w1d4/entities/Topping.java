package mattiaconsiglio.u5w1d4.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Topping extends Food {
    @ManyToMany(mappedBy = "toppings")
    private List<Pizza> pizzas = new ArrayList<>();

    public Topping(String name, int calories, double price) {
        super(name, calories, price);
    }

    @Override
    public String toString() {
        return "Topping{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", price=" + price +
                '}';
    }
}
