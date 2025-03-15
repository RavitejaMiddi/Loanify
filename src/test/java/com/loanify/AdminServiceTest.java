package com.loanify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.loanify.entities.Admin;
import com.loanify.repositories.IAdminRepository;
import com.loanify.services.AdminService;

@SpringBootTest
class AdminServiceTest {

	public static Admin admin, admin1, admin2;

	@BeforeAll
	public static void setUp() {
		admin = new Admin();
		admin1 = new Admin();
		admin2 = new Admin();
		
		admin.setUserId(1);
		admin.setAdminName("asddf");
		admin.setAdminContact("554");
		admin.setPassword("Saishf@");
		admin.setUsername("om");
		
		admin1.setAdminName("asd");
		admin1.setAdminContact("s7");
		admin1.setPassword("jshs@1");
		admin1.setUsername("bcx");
		
		admin2.setAdminName("qwe");
		admin2.setAdminContact("tyu");
		admin2.setPassword("uio@1");
		admin2.setUsername("mjk");
	}

	@Autowired
	AdminService adminService;

	@MockBean
	IAdminRepository adminRepository;

	@Test
	@DisplayName("positive test case of add admin")
	void testAddAdminValidTest() throws Exception {
		when(adminRepository.save(admin)).thenReturn(admin);
		assertEquals(admin, adminService.addAdmin(admin));

	}

	@Test
	@DisplayName("negative test case of add admin")
	void testAddAdminNotValidTest() throws Exception {
		when(adminRepository.save(admin)).thenReturn(admin);
		assertNotEquals(admin1, adminService.addAdmin(admin));
	}

	@Test
	@DisplayName("positive test case for get admin")
	void testGetAdminPositive() throws Exception {
		when(adminRepository.findById(1)).thenReturn(Optional.of(admin1));
		admin = adminService.getAdmin(1);
		assertEquals(admin1.toString(), admin.toString());

	}

	@Test
	@DisplayName("negative test case for get admin")
	void testGetAdminNegative() throws Exception {
		when(adminRepository.findById(2)).thenReturn(Optional.of(admin1));
		admin = adminService.getAdmin(2);
		assertNotEquals(admin2.toString(), admin.toString());

	}

	@Test
	@DisplayName("positive test case of get all admin")
	void testGetAllAdminive() throws Exception {
		List<Admin> list = new ArrayList<>();
		list.add(admin1);
		list.add(admin2);
		adminService.getAllAdmin();
		when(adminRepository.findAll()).thenReturn(list);
		assertEquals(list.size(), adminService.getAllAdmin().size());
	}

	@Test
	@DisplayName("negative test case of get all admin")
	void testGetAllAdminNegative() throws Exception {
		List<Admin> list = new ArrayList<>();
		list.add(admin1);
		list.add(admin2);
		adminService.getAllAdmin();
		when(adminRepository.findAll()).thenReturn(list);
		assertNotEquals(3, adminService.getAllAdmin().size());
	}

	

}
