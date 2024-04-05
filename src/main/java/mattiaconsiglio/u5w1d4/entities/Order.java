package mattiaconsiglio.u5w1d4.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class Order {
    private int orderNumber;
    private OrderStatus status;
    private List<Food> foods;
    private int seatsNumber;
    private LocalTime time;
    private List<Table> tables;
    private double totalPrice;
    private double seatCost;

    public Order(int orderNumber, OrderStatus status, List<Food> foods, int seatsNumber, LocalTime time, List<Table> tables, double seatCost) {
        this.orderNumber = orderNumber;
        this.status = status;
        this.foods = foods;
        this.seatsNumber = seatsNumber;
        this.time = time;
        this.tables = tables;
        this.seatCost = seatCost;
        this.totalPrice = this.calculateTotalPrice();
    }

    public double calculateTotalPrice() {
        double total = seatCost * seatsNumber;
        for (Food food : foods) {
            total += food.getPrice();
        }
        total = Math.round(total * 100.0) / 100.0;
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", status=" + status +
                ", foods=" + foods +
                ", seatsNumber=" + seatsNumber +
                ", time=" + time +
                ", tables=" + tables +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
