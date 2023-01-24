package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MockMvc mvc;
	Gson gson = new Gson();

	private JsonObject createCorrectPostRequest(String name, String lastName) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", name);
		jsonObject.addProperty("lastName", lastName);
		return jsonObject;
	}

	private JsonObject createInCorrectPostRequest(String name) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", name);
		return jsonObject;
	}

	@Test
	public void postShouldReturnOkWhenCorrectData() throws Exception {
		mvc
				.perform(post("/newEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createCorrectPostRequest( "Jan", "Kowalski").toString()))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void postShouldReturnBadRequestWhenIncorrectData() throws Exception {
		mvc
				.perform(post("/newEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createInCorrectPostRequest( "Jan").toString()))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}


	@Test
	public void getShouldReturnOk() throws Exception {
		String firstName = "Jan";
		String lastName = "Kowalski";

		mvc
				.perform(post("/newEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createCorrectPostRequest(firstName, lastName).toString()))
				.andDo(print());

		mvc
				.perform(get("/getEmployes"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString(firstName)))
				.andExpect(content().string(containsString(lastName)));
	}

}
