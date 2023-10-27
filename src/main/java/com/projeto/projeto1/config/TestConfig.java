package com.projeto.projeto1.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projeto.projeto1.entities.Category;
import com.projeto.projeto1.entities.Order;
import com.projeto.projeto1.entities.OrderItem;
import com.projeto.projeto1.entities.Product;
import com.projeto.projeto1.entities.User;
import com.projeto.projeto1.entities.enums.OrderStatus;
import com.projeto.projeto1.repositories.CategoryRepository;
import com.projeto.projeto1.repositories.OrderItemRepository;
import com.projeto.projeto1.repositories.OrderRepository;
import com.projeto.projeto1.repositories.ProductRepository;
import com.projeto.projeto1.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics"); 
		Category cat2 = new Category(null, "Books"); 
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "O Senhor dos Anéis", "J. R. R. Tolkien.", 90.5, ""); 
		Product p2 = new Product(null, "Smart TV", "pra acessar fotos e vídeos que estão em seus outros aparelhos.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "O MacBook Pro é um notebook topo de linha da Apple.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "Um PC Gamer é um computador pessoal especialmente projetado para jogos", 1200.0, ""); 
		Product p5 = new Product(null, "O Pequeno Príncipe", "Antoine de Saint-Exupéry.", 100.99, "");
				
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
				
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.WAITING_PAYMENT, u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
	}

	
}
