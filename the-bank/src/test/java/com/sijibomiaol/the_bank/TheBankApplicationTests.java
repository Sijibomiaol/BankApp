package com.sijibomiaol.the_bank;

import com.sijibomiaol.the_bank.dto.BankResponse;
import com.sijibomiaol.the_bank.dto.CustomerRequest;
import com.sijibomiaol.the_bank.entity.Customer;
import com.sijibomiaol.the_bank.repository.CustomerRepository;
import com.sijibomiaol.the_bank.service.CustomerService;
import com.sijibomiaol.the_bank.service.CustomerServiceImpl;
import com.sijibomiaol.the_bank.service.EmailService;
import com.sijibomiaol.the_bank.utils.AccountUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Optional;

import static com.sijibomiaol.the_bank.ENUM.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Nested
@SpringBootTest
class TheBankApplicationTests {


	@Test
	void contextLoads() {
	}

}
