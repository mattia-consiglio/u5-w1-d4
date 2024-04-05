package mattiaconsiglio.u5w1d4.entities;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Drink extends Food {
    public Drink(String name, int calories, double price) {
        super(name, calories, price);
    }
}
