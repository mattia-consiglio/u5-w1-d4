package mattiaconsiglio.u5w1d4;

import mattiaconsiglio.u5w1d4.entities.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class U5W1D4ApplicationTests {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	@Nested
	class FoodTests {

		private double roundPrice(double price) {
			return Math.round(price * 100.0) / 100.0;
		}

		@Test
		void testDrinkCorrectCreation() {
			Drink drink = new Drink("Coffee", 5, 1.40);
			assertInstanceOf(Food.class, drink, "Drink should inherit from Food");
			assertEquals("Coffee", drink.getName(), "Drink name should be Coffee");
			assertEquals(5, drink.getCalories(), "Drink calories should be 5");
			assertEquals(1.40, drink.getPrice(), "Drink price should be 1.40");
		}

		@Test
		void testToppingCorrectCreation() {
			Topping topping = new Topping("Cheese", 92, 0.69);
			assertInstanceOf(Food.class, topping, "Topping should inherit from Food");
			assertEquals("Cheese", topping.getName(), "Topping name should be Cheese");
			assertEquals(92, topping.getCalories(), "Topping calories should be 92");
			assertEquals(0.69, topping.getPrice(), "Topping price should be 0.69");
		}

		@Test
		void testPizzaCorrectCreation() {
			Topping tomatoTopping = new Topping("Tomato", 20, 0.49);
			Topping cheeseTopping = new Topping("Cheese", 92, 0.69);
			Pizza pizza = new Pizza("Margherita", List.of(tomatoTopping, cheeseTopping));
			assertInstanceOf(Food.class, pizza, "Pizza should inherit from Food");
			assertAll(
					() -> assertEquals("Margherita (Tomato, Cheese)", pizza.getName(), "Pizza name should be Margherita (Tomato, Cheese)"),
					() -> assertEquals(100 + 20 + 92, pizza.getCalories(), "Pizza calories should be 100 + sum of toppings calories"),
					() -> assertEquals(10.0 + 0.49 + 0.69, pizza.getPrice(), "Pizza price should be 10.0 + sum of toppings price"),
					() -> assertEquals(List.of(tomatoTopping, cheeseTopping), pizza.getToppings(), "Pizza toppings should be the same as the ones passed in the constructor")
			);
		}


		@ParameterizedTest
		@MethodSource("listOfPizzas")
		void testPizzaCorrectParameterizedCreation(Pizza pizza, String name, int calories, double price, List<Topping> toppings) {
			assertInstanceOf(Food.class, pizza, "Pizza should inherit from Food");
			assertAll(
					() -> assertEquals(name, pizza.getName(), "Pizza name should be " + name),
					() -> assertEquals(calories, pizza.getCalories(), "Pizza calories should be 100 + sum of toppings calories"),
					() -> assertEquals(price, pizza.getPrice(), "Pizza price should be 10.0 + sum of toppings price"),
					() -> assertEquals(toppings, pizza.getToppings(), "Pizza toppings should be the same as the ones passed in the constructor")
			);
		}

		public Stream<Arguments> listOfPizzas() {
			Topping tomatoTopping = new Topping("Tomato", 20, 0.49);
			Topping cheeseTopping = new Topping("Cheese", 92, 0.69);
			Topping onionTopping = new Topping("Onion", 22, 0.69);
			Topping pineappleTopping = new Topping("Pineapple", 24, 0.79);
			Topping salamiTopping = new Topping("Salami", 25, 1.09);
			Topping hamTopping = new Topping("Ham", 23, 0.79);
			Pizza margherita = new Pizza("Margherita", List.of(tomatoTopping, cheeseTopping));
			Pizza hawaiianPizza = new Pizza("Hawaiian Pizza", List.of(tomatoTopping, cheeseTopping), List.of(hamTopping, pineappleTopping));
			Pizza salamiPizza = new Pizza("Salami Pizza", List.of(tomatoTopping, cheeseTopping), List.of(onionTopping, salamiTopping));
			return Stream.of(
					Arguments.of(margherita, "Margherita (Tomato, Cheese)", 100 + 20 + 92, roundPrice(10.0 + 0.49 + 0.69), List.of(tomatoTopping, cheeseTopping)),
					Arguments.of(hawaiianPizza, "Hawaiian Pizza (Tomato, Cheese, Ham, Pineapple)", 100 + 20 + 92 + 23 + 24, roundPrice(10.0 + 0.49 + 0.69 + 0.79 + 0.79), List.of(tomatoTopping, cheeseTopping, hamTopping, pineappleTopping)),
					Arguments.of(salamiPizza, "Salami Pizza (Tomato, Cheese, Onion, Salami)", 100 + 20 + 92 + 22 + 25, roundPrice(10.0 + 0.49 + 0.69 + 0.69 + 1.09), List.of(tomatoTopping, cheeseTopping, onionTopping, salamiTopping))
			);
		}
	}

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}

	@Test
	void testDisplayMenu() {
		Menu menu = getMenu();
		menu.displayMenu();
		String lineSeparator = System.lineSeparator();
		assertEquals(lineSeparator +
				"Pizzas" + lineSeparator +
				"Name\t\t\t\t\t\t\t\t\t\t\t\tCalories\tPrice" + lineSeparator +
				"Margherita (Tomato, Cheese)\t\t\t\t\t\t\t212\t\t\t11.18" + lineSeparator +
				"Hawaiian Pizza (Tomato, Cheese, Ham, Pineapple)\t\t271\t\t\t12.96" + lineSeparator +
				"Salami Pizza (Tomato, Cheese, Onion, Salami)\t\t259\t\t\t12.96" + lineSeparator +
				lineSeparator +
				"Toppings" + lineSeparator +
				"Name\t\t\t\t\t\t\t\t\t\t\t\tCalories\tPrice" + lineSeparator +
				"Cheese\t\t\t\t\t\t\t\t\t\t\t\t92\t\t\t0.69" + lineSeparator +
				"Ham\t\t\t\t\t\t\t\t\t\t\t\t\t35\t\t\t0.99" + lineSeparator +
				"Onion\t\t\t\t\t\t\t\t\t\t\t\t22\t\t\t0.69" + lineSeparator +
				"Pineapple\t\t\t\t\t\t\t\t\t\t\t24\t\t\t0.79" + lineSeparator +
				"Salami\t\t\t\t\t\t\t\t\t\t\t\t25\t\t\t1.09" + lineSeparator +
				lineSeparator +
				"Drinks" + lineSeparator +
				"Name\t\t\t\t\t\t\t\t\t\t\t\tCalories\tPrice" + lineSeparator +
				"CocaCola\t\t\t\t\t\t\t\t\t\t\t10\t\t\t1.0" + lineSeparator +
				"Fanta\t\t\t\t\t\t\t\t\t\t\t\t10\t\t\t1.5" + lineSeparator, outputStreamCaptor.toString());
	}


	@NotNull
	private static Menu getMenu() {
		Topping tomatoTopping = new Topping("Tomato", 20, 0.49);
		Topping cheeseTopping = new Topping("Cheese", 92, 0.69);
		Topping onionTopping = new Topping("Onion", 22, 0.69);
		Topping pineappleTopping = new Topping("Pineapple", 24, 0.79);
		Topping salamiTopping = new Topping("Salami", 25, 1.09);
		Topping hamTopping = new Topping("Ham", 35, 0.99);
		Pizza margherita = new Pizza("Margherita", List.of(tomatoTopping, cheeseTopping));
		Pizza hawaiianPizza = new Pizza("Hawaiian Pizza", List.of(tomatoTopping, cheeseTopping), List.of(hamTopping, pineappleTopping));
		Pizza salamiPizza = new Pizza("Salami Pizza", List.of(tomatoTopping, cheeseTopping), List.of(onionTopping, salamiTopping));
		Drink cocacola = new Drink("CocaCola", 10, 1.0);
		Drink fanta = new Drink("Fanta", 10, 1.5);


		List<Pizza> pizzas = List.of(margherita, hawaiianPizza, salamiPizza);
		List<Topping> toppings = List.of(cheeseTopping, hamTopping, onionTopping, pineappleTopping, salamiTopping);
		List<Drink> drinks = List.of(cocacola, fanta);

		Menu menu = new Menu(pizzas, toppings, drinks);
		return menu;
	}
}
