package mattiaconsiglio.u5w1d4.entities;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
//@ToString
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "foods")
public abstract class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    protected UUID uuid;
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

