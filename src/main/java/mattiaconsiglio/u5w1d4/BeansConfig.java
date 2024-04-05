package mattiaconsiglio.u5w1d4;

import mattiaconsiglio.u5w1d4.entities.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource("application.properties")
public class BeansConfig {
    @Bean(name = "getSeatConst")
    public double getSeatConst(@Value("${seat.cost}") int seatCost) {
        return seatCost;
    }

    @Bean
    public Topping cheese() {
        return new Topping("Cheese", 92, 0.69);
    }

    @Bean
    public Topping ham() {
        return new Topping("Ham", 35, 0.99);
    }

    @Bean
    public Topping onion() {
        return new Topping("Onion", 22, 0.69);
    }

    @Bean
    public Topping pineapple() {
        return new Topping("Pineapple", 24, 0.79);
    }

    @Bean
    public Topping salami() {
        return new Topping("Salami", 25, 1.09);
    }

    @Bean
    public Topping tomato() {
        return new Topping("Tomato", 20, 0.49);
    }


    @Bean
    @Primary
    public List<Topping> getToppings() {
        return new ArrayList<>(List.of(cheese(), ham(), onion(), pineapple(), salami()));
    }


    @Bean
    public List<Pizza> getPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        List<Topping> basicToppings = List.of(tomato(), cheese());
        pizzas.add(new Pizza("Margherita", basicToppings));
        pizzas.add(new Pizza("Hawaiian Pizza", basicToppings, List.of(ham(), pineapple())));
        pizzas.add(new Pizza("Salami Pizza", basicToppings, List.of(onion(), salami())));

        return pizzas;
    }


    @Bean
    public List<Drink> getDrinks() {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new Drink("CocaCola", 10, 1.0));
        drinks.add(new Drink("Fanta", 10, 1.5));

        return drinks;
    }


    @Bean
    public Menu menu(List<Pizza> pizzas, @Qualifier("getToppings") List<Topping> toppings, List<Drink> drinks) {
        return new Menu(pizzas, toppings, drinks);
    }


    @Bean
    public List<Table> getTables() {
        List<Table> tables = new ArrayList<>();
        tables.add(new Table(1, TableStatus.FREE, 2));
        tables.add(new Table(2, TableStatus.FREE, 4));
        tables.add(new Table(3, TableStatus.FREE, 6));
        tables.add(new Table(4, TableStatus.FREE, 2));
        tables.add(new Table(5, TableStatus.FREE, 2));
        tables.add(new Table(6, TableStatus.FREE, 2));
        tables.add(new Table(7, TableStatus.FREE, 2));
        return tables;
    }


}
