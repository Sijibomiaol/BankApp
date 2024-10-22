import com.sijibomiaol.the_bank.dto.BankResponse;
import com.sijibomiaol.the_bank.dto.CustomerRequest;
import com.sijibomiaol.the_bank.entity.Customer;
import com.sijibomiaol.the_bank.repository.CustomerRepository;
import com.sijibomiaol.the_bank.service.CustomerServiceImpl;
import com.sijibomiaol.the_bank.service.EmailService;
import com.sijibomiaol.the_bank.utils.AccountUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Optional;

import static com.sijibomiaol.the_bank.ENUM.Role.ROLE_USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @Test
    public  void testCreateAccount_Succes() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("John");
        customerRequest.setLastName("Doe");
        customerRequest.setOtherName("A.");
        customerRequest.setPhone("08166299417");
        customerRequest.setEmail("aderinlewom@gmail.com");
        customerRequest.setAddress("Lagos Nigeria");
        customerRequest.setPassword("Cashit");
        customerRequest.setStateofOrigin("Lagos");
        customerRequest.setRole(ROLE_USER);
        customerRequest.setAge(30);

        when(customerRepository.findByAccountNumber(any())).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> {
            Customer customer = invocation.getArgument(0);
            customer.setAccountNumber("generated-account-number"); // Mock generated account number
            return customer;
        });

        when(passwordEncoder.encode(anyString())).thenReturn("encoded-password");

        BankResponse response = customerService.createAccount(customerRequest);
        assertEquals(AccountUtils.ACCOUNT_CREATION_SUCCESS, response.getResponseCode());
        assertEquals("Account creation successful!", response.getResponseMessage());
        assertNotNull(response.getInfo());
        assertEquals("generated-account-number", response.getInfo().getAccountNumber());
        assertEquals("John A. Doe", response.getInfo().getAccountName());
        assertEquals(BigDecimal.ZERO, response.getInfo().getAccountBalance());

        // Check that the password is encoded
        assertEquals("encoded-password", customerRepository.findByAccountNumber("generated-account-number").get().getPassword());

        verify(customerRepository).save(any(Customer.class)); // Ensure save was called

        verify(emailService).sendEMailAlert(any()); // Ensure email alert was sent
    }
    @Test
    void testCreateAccount_AccountExists() {
        // Given
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setPhone("1234567890");

        Customer existingCustomer = new Customer();
        existingCustomer.setAccountNumber("existing-account-number");

        // When
        when(customerRepository.findByAccountNumber(existingCustomer.getAccountNumber())).thenReturn(Optional.of(existingCustomer));

        BankResponse response = customerService.createAccount(customerRequest);

        // Then
        assertEquals(AccountUtils.ACCOUNT_EXISTS_CODE, response.getResponseCode());
        assertEquals(AccountUtils.ACCOUNT_EXISTS_MESSAGE, response.getResponseMessage());
        verify(customerRepository, never()).save(any()); // Ensure save was not called
    }

    @Test
    void testCreateAccount_InvalidRequest() {
        // Given
        CustomerRequest customerRequest = new CustomerRequest(); // No fields set

        // When
        BankResponse response = customerService.createAccount(customerRequest);

        // Then
        assertEquals("All fields are required and must be valid", response.getResponseMessage());
        verify(customerRepository, never()).save(any()); // Ensure save was not called
    }
}



