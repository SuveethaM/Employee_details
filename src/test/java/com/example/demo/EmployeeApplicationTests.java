package com.example.demo;
import com.example.demo.controller.EmployeeController;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeApplicationTests {

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	private final MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	public EmployeeApplicationTests() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	void testGetAllEmployees() throws Exception {
		when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(
				new EmployeeDTO(1L, "John Doe", "IT"),
				new EmployeeDTO(2L, "Jane Doe", "HR")
		));

		mockMvc.perform(get("/employees"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("John Doe"))
				.andExpect(jsonPath("$[1].department").value("HR"));

		verify(employeeService, times(1)).getAllEmployees();
		verifyNoMoreInteractions(employeeService);
	}

	@Test
	void testGetEmployeeById() throws Exception {
		Long employeeId = 1L;
		EmployeeDTO employeeDTO = new EmployeeDTO(employeeId, "John Doe", "IT");

		when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(employeeDTO));

		mockMvc.perform(get("/employees/{id}", employeeId))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("John Doe"))
				.andExpect(jsonPath("$.department").value("IT"));

		verify(employeeService, times(1)).getEmployeeById(employeeId);
		verifyNoMoreInteractions(employeeService);
	}

	@Test
	void testGetEmployeeByIdNotFound() throws Exception {
		Long nonExistingEmployeeId = 99L;

		when(employeeService.getEmployeeById(nonExistingEmployeeId)).thenReturn(Optional.empty());

		mockMvc.perform(get("/employees/{id}", nonExistingEmployeeId))
				.andExpect(status().isNotFound());

		verify(employeeService, times(1)).getEmployeeById(nonExistingEmployeeId);
		verifyNoMoreInteractions(employeeService);
	}

	@Test
	void testSaveEmployee() throws Exception {
		EmployeeDTO employeeDTO = new EmployeeDTO(null, "New Employee", "Finance");
		EmployeeDTO savedEmployeeDTO = new EmployeeDTO(1L, "New Employee", "Finance");

		when(employeeService.saveEmployee(any(EmployeeDTO.class))).thenReturn(savedEmployeeDTO);

		mockMvc.perform(post("/employees")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(employeeDTO)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.name").value("New Employee"))
				.andExpect(jsonPath("$.department").value("Finance"));

		verify(employeeService, times(1)).saveEmployee(any(EmployeeDTO.class));
		verifyNoMoreInteractions(employeeService);
	}

	@Test
	void testDeleteEmployee() throws Exception {
		Long employeeId = 1L;

		mockMvc.perform(delete("/employees/{id}", employeeId))
				.andExpect(status().isNoContent());

		verify(employeeService, times(1)).deleteEmployee(employeeId);
		verifyNoMoreInteractions(employeeService);
	}
}
