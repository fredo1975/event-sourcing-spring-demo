package com.progressivecoder.es.eventsourcingaxonspringboot;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressivecoder.es.eventsourcingaxonspringboot.dto.commands.AccountCreateDTO;
import com.progressivecoder.es.eventsourcingaxonspringboot.dto.commands.MoneyCreditDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventSourcingAxonSpringBootApplicationTests {
	@Autowired
	private MockMvc mvc;
	@Test
	public void contextLoads() {
	}
	@Test
	public void testCreateAccount() throws Exception  {
		AccountCreateDTO accountCreateDTO = new AccountCreateDTO();
		accountCreateDTO.setCurrency("USD");
		accountCreateDTO.setStartingBalance(0d);
		ObjectMapper mapper = new ObjectMapper();
		String accountCreateDTOJsonString = mapper.writeValueAsString(accountCreateDTO);
		String accountNum = (String) mvc.perform(MockMvcRequestBuilders
				.post("/bank-accounts")
				.contentType(MediaType.APPLICATION_JSON).content(accountCreateDTOJsonString)).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn().getAsyncResult();
		
		MoneyCreditDTO moneyCreditDTO = new MoneyCreditDTO();
		moneyCreditDTO.setCreditAmount(15000d);
		moneyCreditDTO.setCurrency("USD");
		String moneyCreditDTOJsonString = mapper.writeValueAsString(moneyCreditDTO);
		String result = (String) mvc.perform(MockMvcRequestBuilders
				.put("/bank-accounts/credits/"+accountNum)
				.contentType(MediaType.APPLICATION_JSON).content(moneyCreditDTOJsonString)).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn().getAsyncResult();
		System.out.println("result="+result);
		mvc.perform(MockMvcRequestBuilders.get("/bank-accounts/"+accountNum+"/events")
				.contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

	}
	
}

