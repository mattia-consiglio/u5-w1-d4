package mattiaconsiglio.u5w1d4.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Menu {
    List<Pizza> pizzas;
    List<Topping> toppings;
    List<Drink> drinks;

    private void displayHeader(String section, int maxNameTabs, int maxCaloriesTabs) {
        System.out.println();
        System.out.println(section);
        this.displayLine("Name", "Calories", "Price", maxNameTabs, maxCaloriesTabs);
    }

    private void displayLine(@NotNull String name, @NotNull String calories, String price, int maxNameTabs, int maxCaloriesTabs) {
        int nameTabs = maxNameTabs - (name.length() / 4);
        int caloriesTabs = maxCaloriesTabs - (calories.length() / 4);
        String nameString = name + "\t".repeat(Math.max(0, nameTabs));
        System.out.println(nameString + "\t" + calories + "\t".repeat(Math.max(0, caloriesTabs)) + "\t" + price);
    }


    public void displayMenu() {
        List<Food> foods = new ArrayList<>();
        foods.addAll(pizzas);
        foods.addAll(toppings);
        foods.addAll(drinks);

        List<String> names = new ArrayList<>(foods.stream().map(Food::getName).toList());
        names.add("Name");
        List<String> calories = new ArrayList<>(foods.stream().map(f -> String.valueOf(f.getCalories())).toList());
        calories.add("Calories");
        int longestName = names.stream().mapToInt(String::length).max().orElse(0);
        int longestCalories = calories.stream().mapToInt(String::length).max().orElse(0);
        int maxNameTabs = (int) Math.ceil((double) longestName / 4);
        int maxCaloriesTabs = (int) Math.ceil((double) longestCalories / 4);


        displayHeader("Pizzas", maxNameTabs, maxCaloriesTabs);
        pizzas.forEach(f -> displayLine(f.getName(), String.valueOf(f.getCalories()), String.valueOf(f.getPrice()), maxNameTabs, maxCaloriesTabs));
        displayHeader("Toppings", maxNameTabs, maxCaloriesTabs);
        toppings.forEach(f -> displayLine(f.getName(), String.valueOf(f.getCalories()), String.valueOf(f.getPrice()), maxNameTabs, maxCaloriesTabs));
        displayHeader("Drinks", maxNameTabs, maxCaloriesTabs);
        drinks.forEach(f -> displayLine(f.getName(), String.valueOf(f.getCalories()), String.valueOf(f.getPrice()), maxNameTabs, maxCaloriesTabs));
    }
}
