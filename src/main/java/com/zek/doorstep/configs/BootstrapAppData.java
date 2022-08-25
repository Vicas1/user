package com.zek.doorstep.configs;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.zek.doorstep.entity.Authority;
import com.zek.doorstep.entity.Company;
import com.zek.doorstep.entity.Role;
import com.zek.doorstep.entity.Users;
import com.zek.doorstep.repo.AuthorityRepository;
import com.zek.doorstep.repo.CompanyRepository;
import com.zek.doorstep.repo.RoleRepository;
import com.zek.doorstep.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BootstrapAppData implements ApplicationListener<ApplicationReadyEvent> {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final AuthorityRepository authorityRepository;
	private final CompanyRepository companyRepository;
	private final PasswordEncoder passwordEncoder;
	private final Faker faker = new Faker();

	@Value("${app.customerCount}")
	private int orderCount;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {

		Company company = Company.builder().companyName(faker.company().name()).isActive(true).build();
		companyRepository.save(company);
		Role userRole = Role.builder().roleName("ROLE_USER").build();
		Role adminRole = Role.builder().roleName("ROLE_ADMIN").build();

		this.roleRepository.save(userRole);
		this.roleRepository.save(adminRole);

		Authority readOrders = Authority.builder().authority("LIST_ORDERS").build();
		Authority createOrder = Authority.builder().authority("CREATE_ORDER").build();
		Authority deleteOrder = Authority.builder().authority("DELETE_ORDER").build();

		userRole.addAuthority(readOrders);
		userRole.addAuthority(createOrder);
		userRole.addAuthority(deleteOrder);

		adminRole.addAuthority(readOrders);

		this.authorityRepository.save(readOrders);
		this.authorityRepository.save(createOrder);
		this.authorityRepository.save(deleteOrder);

		this.roleRepository.save(userRole);
		this.roleRepository.save(adminRole);

		 Users ee = Users.builder().email("ee@gmail.com")
				  .userName("EK").password(this.passwordEncoder.encode("welcome")).build();
				  ee.setRole(adminRole); 
				  ee.setCompany(company);
				  this.userRepository.save(ee);
				 
				  
		// imperative style of programming //for(int i = 0; i < 10; i ++)

		// declarative style of programming

		IntStream.range(0, orderCount).forEach(index -> {
			Name customerName = faker.name();
			Users user = Users.builder().email(customerName.firstName() + "@" + faker.internet().domainName())
					.userName(customerName.firstName()).password(this.passwordEncoder.encode("welcome")).build();

			/*
			 * IntStream.range(0, faker.number().numberBetween(1, 4)).forEach(cId -> { Order
			 * order = Order.builder().orderDate( faker.date().past(4,
			 * TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
			 * .build();
			 * 
			 * 
			 * IntStream.range(0, faker.number().numberBetween(1, 4)).forEach(oId -> {
			 * LineItem lineItem = LineItem.builder().name(faker.commerce().productName())
			 * .qty(faker.number().numberBetween(2, 5))
			 * .pricePerUnit(faker.number().randomDouble(2, 300, 800)).build();
			 * order.addLineItem(lineItem); });
			 * 
			 * 
			 * 
			 * double totalOrderPrice = order.getLineItems().stream() .map(lineItem ->
			 * lineItem.getQty() * lineItem.getPricePerUnit()) .reduce((price1, price2) ->
			 * price1 + price2).orElse(0d); order.setPrice(totalOrderPrice);
			 * customer.placeOrder(order);
			 * 
			 * //customer.setRole(userRole); });
			 */
			user.setRole(userRole);
			user.setCompany(company);
//			if(index == 0)
//				user.setRole(adminRole);
			this.userRepository.save(user);
		});
		
		 

	}
}