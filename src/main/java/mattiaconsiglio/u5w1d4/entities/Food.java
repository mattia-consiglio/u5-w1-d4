package mattiaconsiglio.u5w1d4.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    protected UUID uid;
    protected String name;
    protected int calories;
    protected double price;

    public Food(String name) {
        this.name = name;
    }

    public Food(String name, int calories, double price) {
        this.name = name;
        this.calories = calories;
        this.price = price;
    }

}

