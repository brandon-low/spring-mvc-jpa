package hello.sample.test.basic;


import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import hello.sample.test.basic.web.GreetingController;
import hello.sample.test.basic.web.GreetingService;
import hello.sample.test.basic.web.Sample;

@WebMvcTest(GreetingController.class)
public class WebMockTest {

	private static final Logger LOG = LoggerFactory.getLogger(WebMockTest.class);
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GreetingService service;

	//@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		when(service.greet()).thenReturn("Hello, Mock");
		this.mockMvc.perform(get("/greeting/greeting")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, Mock")));
	}
	
	/** Test GET */
	
	//@Test
	public void getAllSamplesAPI() throws Exception 	{
	  this.mockMvc.perform( MockMvcRequestBuilders.
		      get("/greeting/getall")  //)
		     .accept(MediaType.APPLICATION_JSON))
		      .andDo(print())
		      .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
		      .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", containsString("Go You")))
		      .andExpect(status().isOk())	 ;
		    
		      //.andExpect(MockMvcResultMatchers.jsonPath("$.list").exists())
		      //.andExpect(MockMvcResultMatchers.jsonPath("$.list[*].id").isNotEmpty());
	  /*
	  .andExpect(jsonPath("$[0].firstName", is("Ligza")))
	  .andExpect(jsonPath("$[1].firstName", is("Vekrir")))
	  */
	}
	
	//@Test	// works
	public void getAllSamplesAPI2() throws Exception 	{
		MvcResult result =  this.mockMvc.perform(MockMvcRequestBuilders.
		      get("/greeting/getall") 		//)
		     .accept(MediaType.APPLICATION_JSON))
			 .andDo(print())
		      .andExpect(status().isOk()) 
		      .andReturn() ;
		dumpResult(result);
		
		//assertTrue(actual.size() > 0);
		assertTrue(true);
		
	}
	
	@Test   // works
	public void getSampleByIdAPI() throws Exception 	{
	  this.mockMvc.perform( MockMvcRequestBuilders.
		      get("/greeting/sample/{id}", 1)
		      .accept(MediaType.APPLICATION_JSON))
		      .andDo(print())
		      .andExpect(status().isOk())
		      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}
	

	
	//@Test
	public void getSampleByIdJSONAPI() throws Exception 	{
		MvcResult result = this.mockMvc.perform( MockMvcRequestBuilders.
			      get("/greeting/sampleJson/{id}", 1)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			     // .andExpect(status().isOk())
			      .andReturn();
			      //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
		dumpResult(result);
		assertTrue(true);
	}
	
	//@Test
	public void saveSampleAPI() throws Exception {
	  this.mockMvc.perform( MockMvcRequestBuilders
	      .post("/greeting/save")
	      .content(asJsonString(new Sample(10, "firstName4", "email4@mail.com")))
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10));
	}
	 
	private static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
	/*
	 * PUT
	 */
	/*
	 * Test
public void updateEmployeeAPI() throws Exception 
{
  mvc.perform( MockMvcRequestBuilders
      .put("/employees/{id}", 2)
      .content(asJsonString(new EmployeeVO(2, "firstName2", "lastName2", "email2@mail.com")))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName2"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastName2"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email2@mail.com"));
}
	 */
	
	/*
	 * DELETE
	 */
	/*
	@Test
	public void deleteEmployeeAPI() throws Exception 
	{
	  mvc.perform( MockMvcRequestBuilders.delete("/employees/{id}", 1) )
	        .andExpect(status().isAccepted());
	}
	*/
	
	private void dumpResult(MvcResult result) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		System.out.println("************ DUMP *******************");
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(result.getModelAndView());
		ModelAndView model = result.getModelAndView();
		if (model != null) {
			System.out.println("ViewName=" + model.getViewName());
			System.out.println("Model="+model.getModel());
			System.out.println("************ END DUMP *******************");
			
		}
		
		/*
		 * .andExpect(status().isOk())
.andExpect(model().hasNoErrors())
.andExpect(model().size(2))
.andExpect(model().attributeExists(“form”))
.andExpect(model().attributeExists(“entities”))
.andExpect(model().attribute(“entities”, hasItem(EntityName.EDITION)))
.andExpect(model().attribute(“entities”, hasSize(EntityName.values().length)));
		 */
		// this uses a TypeReference to inform Jackson about the Lists's generic type
		//List<Sample> actual = mapper.readValue(result.getResponse().getContentAsString(), 
		//			new TypeReference<List<Sample>>() {});
		    
	}
	
}
