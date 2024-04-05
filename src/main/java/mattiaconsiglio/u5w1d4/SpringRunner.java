package mattiaconsiglio.u5w1d4;


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

    private List<Order> orders = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5W1D4Application.class);
        Menu menu = ctx.getBean(Menu.class);


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
