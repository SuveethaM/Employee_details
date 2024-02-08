package com.example.demo;


import com.example.demo.controller.EmployeeController;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeApplicationTests {

	@Test
	void testGetAllEmployees() throws Exception {
		EmployeeService employeeService = mock(EmployeeService.class);
		RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
		EmployeeController employeeController = new EmployeeController(employeeService, rabbitTemplate);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

		when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());

		mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
				.andExpect(status().isOk());
		// Add assertions as needed
	}

	@Test
	void testGetEmployeeById() throws Exception {
		EmployeeService employeeService = mock(EmployeeService.class);
		RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
		EmployeeController employeeController = new EmployeeController(employeeService, rabbitTemplate);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

		when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(new EmployeeDTO()));

		mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"))
				.andExpect(status().isOk());
		// Add assertions as needed
	}
	@Test
	void testSaveEmployee() throws Exception {
		EmployeeService employeeService = mock(EmployeeService.class);
		RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
		EmployeeController employeeController = new EmployeeController(employeeService, rabbitTemplate);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(1L);

		when(employeeService.saveEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/employees")
				.content(asJsonString(employeeDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated());
		// Add assertions as needed
	}
	@Test
	void testRabbitEmployee() throws Exception {
		EmployeeService employeeService = mock(EmployeeService.class);
		RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
		EmployeeController employeeController = new EmployeeController(employeeService, rabbitTemplate);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(1L);

		when(employeeService.saveEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO);

		// Convert employeeDTO to JSON
		String jsonEmployeeDTO = asJsonString(employeeDTO);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/employees/rabbirmqpost")
				.content(jsonEmployeeDTO)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		// Perform the request and assert the response
		mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated());  // Assert HTTP 201 status code

		// Optionally, you can assert additional information, such as the response body content or headers
		// For example, if your method returns additional information in the response body, you can assert it like this:
		// .andExpect(jsonPath("$.someProperty").value("expectedValue"));

		// Add assertions as needed
	}


	@Test
	void testDeleteEmployee() throws Exception {
		EmployeeService employeeService = mock(EmployeeService.class);
		RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
		EmployeeController employeeController = new EmployeeController(employeeService, rabbitTemplate);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

		mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
				.andExpect(status().isNoContent());
		// Add assertions as needed
	}


	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
