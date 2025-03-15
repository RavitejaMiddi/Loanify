package com.loanify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.loanify.entities.Customer;
import com.loanify.entities.LoanApplication;
import com.loanify.exceptions.LoanApplicationNotFoundException;
import com.loanify.repositories.ICustomerRepository;
import com.loanify.repositories.ILoanApplicationRepository;
import com.loanify.services.ILoanApplicationService;



@SpringBootTest
class LoanApplicationServiceTest {
	
	@Autowired
	ILoanApplicationService loanApplicationService;
	
	@MockBean
	ICustomerRepository customerRepository;
	
	@MockBean
	ILoanApplicationRepository loanApplicationRepository;

	public static LoanApplication loanApplication, loanApplication1, obj;
	public static Customer customer,customer1;

	@BeforeAll
	public static void setUp() {
		customer = new Customer();
		customer.setUserId(1);
		customer.setCustomerName("Sita");
		customer.setDateOfBirth(LocalDate.of(1996, 02, 14));
		customer.setGender("Female");
		customer.setEmailId("sita@gmail.com");
		customer.setMobileNumber("7898789887");
		customer.setNationality("Indian");
		customer.setPanNumber("213BP2P");
		customer.setAadharNumber("528545691236");
		customer.setUsername("Sita");
		customer.setPassword("1234");
		
		loanApplication = new LoanApplication(customer,5000000,10);
		loanApplication.setApplicationId(3);

		customer1 = new Customer();
		customer1.setUserId(2);
		customer1.setCustomerName("Sita");
		customer1.setDateOfBirth(LocalDate.of(1991, 5, 15));
		customer1.setGender("female");
		customer1.setEmailId("sita@gmail.com");
		customer1.setMobileNumber("7898789887");
		customer1.setNationality("Indian");
		customer1.setPanNumber("213BP2P");
		customer1.setAadharNumber("528545691236");
		customer1.setUsername("Sita");
		customer1.setPassword("1234");
		
		loanApplication1 = new LoanApplication(customer1,100000,10);
		loanApplication1.setApplicationId(4);		

	}
	
	@Test
	@DisplayName("Test case for Get all correct loan applications")
	void testGetAllLoanApplicationPositive() throws Exception {
		List<LoanApplication> list = new ArrayList<>();
		list.add(loanApplication);
		list.add(loanApplication1);
		loanApplicationService.retrieveAllLoanApplication();
		when(loanApplicationRepository.findAll()).thenReturn(list);
		assertEquals(list.size(), loanApplicationService.retrieveAllLoanApplication().size());

	}

	@Test
	@DisplayName("Test case for get all invalid loan applications")
	void testGetAllLoanApplicationNegative() throws Exception {
		List<LoanApplication> list = new ArrayList<>();
		list.add(loanApplication);
		list.add(loanApplication1);
		loanApplicationService.retrieveAllLoanApplication();
		when(loanApplicationRepository.findAll()).thenReturn(list);
		assertNotEquals(3, loanApplicationService.retrieveAllLoanApplication().size());

	}

	@Test
	@DisplayName("Test case for get loan application by correct id")
	void testGetLoanApplicationPositive() throws Exception {
		
		obj = new LoanApplication();
		when(loanApplicationRepository.findById(3)).thenReturn(Optional.of(obj));
		loanApplication = loanApplicationService.retrieveLoanApplication(3);
		assertEquals(obj.toString(), loanApplication.toString());
	}

	@Test
	@DisplayName("Test case for get loan application by invalid id")
	void testGetLoanApplicationNegative() throws Exception {
		
		when(loanApplicationRepository.findById(3)).thenReturn(Optional.of(loanApplication));
		loanApplication = loanApplicationService.retrieveLoanApplication(3);
		assertNotEquals(loanApplication1.toString(), loanApplication.toString());
	}

	@Test
	@DisplayName("Test case for delete LoanApplication")
	void testDeleteLoanApplicationPositive() throws Exception {
		doNothing().when(loanApplicationRepository).deleteById(3);
		when(loanApplicationRepository.findById(3)).thenReturn(Optional.of(loanApplication));
		assertEquals(loanApplication, loanApplicationService.deleteLoanApplication(3));

	}

	@Test
	@DisplayName("Test case for delete wrong LoanApplication")
	void testDeleteLoanApplicationNegative() throws Exception {
		doNothing().when(loanApplicationRepository).deleteById(3);
		when(loanApplicationRepository.findById(3)).thenReturn(Optional.of(loanApplication));
		assertThrows(LoanApplicationNotFoundException.class,()-> loanApplicationService.deleteLoanApplication(1001));

	}

	
	
}
