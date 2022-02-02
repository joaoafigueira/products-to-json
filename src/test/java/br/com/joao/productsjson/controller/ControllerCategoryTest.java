package br.com.joao.productsjson.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import br.com.joao.productsjson.model.Category;
import br.com.joao.productsjson.repository.RepositoryCategory;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { ControllerCategory.class })
class ControllerCategoryTest {

	private final String BASE_URL = "/categories/";

	private MockMvc mockMvc;

	@MockBean
	private RepositoryCategory mockRepository;

	@Autowired
	private ControllerCategory controllerCategory;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controllerCategory).build();
	}

	@Test
	public void shouldReturn409WhenAlreadyExistsCategory() throws Exception {

		Category categoryData = new Category();
		categoryData.setId((long) 1);
		categoryData.setCategoryName("BOOKS");

		when(mockRepository.findByCategoryName("BOOKS")).thenReturn(categoryData);

		String category = "{ \"categoryName\": \"BOOKS\"}";

		mockMvc.perform(post((BASE_URL)).contentType(MediaType.APPLICATION_JSON).content(category))
				.andExpect(status().isConflict());
	}

}
