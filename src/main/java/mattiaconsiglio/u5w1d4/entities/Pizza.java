package mattiaconsiglio.u5w1d4.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Pizza extends Food {

    @ManyToMany
    @JoinTable(
            name = "pizza_topping",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "topping_id")
    )
    private List<Topping> toppings = new ArrayList<>();

    public Pizza(String name, List<Topping> basicToppings) {
        this(name, basicToppings, true);
    }

    public Pizza(String name, List<Topping> basicToppings, List<Topping> toppings) {
        this(name, basicToppings, false);
        this.toppings.addAll(toppings);
        this.calories += toppings.stream().mapToInt(Topping::getCalories).sum();
        this.price += toppings.stream().mapToDouble(Topping::getPrice).sum();
        roundPrice();
    }

    private Pizza(String name, List<Topping> basicToppings, boolean isCalledFromThreeParamConstructor) {
        super(name);
        this.toppings.addAll(basicToppings);
        this.calories = 100 + basicToppings.stream().mapToInt(Topping::getCalories).sum();
        this.price = 10 + basicToppings.stream().mapToDouble(Topping::getPrice).sum();
        if (!isCalledFromThreeParamConstructor) {
            roundPrice();
        }
    }

    private void roundPrice() {
        this.price = Math.round(this.price * 100.0) / 100.0;
    }


    @Override
    public String getName() {
        return this.name + " (" + this.toppings.stream().map(Topping::getName).collect(Collectors.joining(", ")) + ")";
    }
}
