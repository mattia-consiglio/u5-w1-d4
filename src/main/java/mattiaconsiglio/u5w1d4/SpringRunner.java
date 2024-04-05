package mattiaconsiglio.u5w1d4;


import mattiaconsiglio.u5w1d4.dao.FoodService;
import mattiaconsiglio.u5w1d4.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpringRunner implements CommandLineRunner {
    @Autowired
    private List<Table> tables;
    @Autowired
    private List<Pizza> pizzas;

    @Autowired
    private FoodService foodService;

    private final List<Order> orders = new ArrayList<>();

    public SpringRunner(List<Table> tables, List<Pizza> pizzas) {
        this.tables = tables;
        this.pizzas = pizzas;
    }

    @Override
    public void run(String... args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5W1D4Application.class);
        Menu menu = ctx.getBean(Menu.class);

        // add foods to db
        Topping tomato = (Topping) foodService.save((Topping) ctx.getBean("tomato"));
        Topping cheese = (Topping) foodService.save((Topping) ctx.getBean("cheese"));
        Topping ham = (Topping) foodService.save((Topping) ctx.getBean("ham"));
        Topping onion = (Topping) foodService.save((Topping) ctx.getBean("onion"));
        Topping pineapple = (Topping) foodService.save((Topping) ctx.getBean("pineapple"));
        Topping salami = (Topping) foodService.save((Topping) ctx.getBean("salami"));


        Pizza pizza1 = new Pizza("Margherita", List.of(tomato, cheese));
        Pizza pizza2 = new Pizza("Pepperoni", List.of(tomato, cheese), List.of(onion, salami));
        Pizza pizza3 = new Pizza("Hawaiian", List.of(tomato, cheese), List.of(ham, pineapple));

        foodService.save(pizza1);
        foodService.save(pizza2);
        foodService.save(pizza3);


        menu.displayMenu();


        double seatCost = (double) ctx.getBean("getSeatConst");
        System.out.println("Seat cost: " + seatCost);

        System.out.println("Tables:");
        tables.forEach(System.out::println);

        orders.add(new Order(1, OrderStatus.IN_PROGRESS, List.of(pizzas.get(0), pizzas.get(0)), 6, LocalTime.of(19, 0), List.of(tables.get(0), tables.get(1)), seatCost));
        orders.add(new Order(2, OrderStatus.IN_PROGRESS, List.of(pizzas.get(1), pizzas.get(2)), 4, LocalTime.of(20, 0), List.of(tables.get(2), tables.get(3)), seatCost));
        orders.add(new Order(3, OrderStatus.IN_PROGRESS, List.of(pizzas.get(2), pizzas.get(1)), 2, LocalTime.of(21, 0), List.of(tables.get(4), tables.get(5)), seatCost));

        System.out.println("Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }

        ctx.close();

    }
}
