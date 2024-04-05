package mattiaconsiglio.u5w1d4.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public abstract class Food {
    protected String name;
    protected int calories;
    protected double price;

    public Food(String name) {
        this.name = name;
    }

    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }
}
