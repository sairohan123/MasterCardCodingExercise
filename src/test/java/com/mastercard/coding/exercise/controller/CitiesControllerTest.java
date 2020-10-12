package com.mastercard.coding.exercise.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CitiesController.class)
class CitiesControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testWithBostonAndNewark() throws Exception {
		mvc.perform(get("/connected?origin=Boston&destination=Newark")).andExpect(content().string("Yes"));
	}

	@Test
	public void testWithBostonAndPhiladelphia() throws Exception {
		mvc.perform(get("/connected?origin=Boston&destination=Philadelphia")).andExpect(content().string("Yes"));
	}

	@Test
	public void testWithPhiladelphiaAndAlbany() throws Exception {
		mvc.perform(get("/connected?origin=Philadelphia&destination=Albany")).andExpect(content().string("No"));
	}

	@Test
	public void testWitXandY() throws Exception {
		mvc.perform(get("/connected?origin=X&destination=Y")).andExpect(content().string("No"));
	}
}