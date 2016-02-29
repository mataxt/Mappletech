package testrest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import dao.EventDAO;
import model.Event;
import model.User;
import restController.EventRestController;
import vm.EventVM;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EventDAO.class)
public class TestEventRest{
	
	
	private Event first;
	private User firstUser;
	private Event second;
	private User secondUser;
	private Date currentDate;
	
	private MockMvc mockMvc;
	private EventRestController eventRest;
	
	@Before
	public void setUp()
	{
		firstUser = new User();
		firstUser.setUsername("Fernando");
		
		secondUser = new User();
		secondUser.setUsername("Rami");
		
		currentDate = new Date(System.currentTimeMillis());
		first = new Event();
		first.setCreator(firstUser);
		first.setDate(currentDate);
		first.setDescription("testDesc");
		first.setEventID(1);
		first.setTitle("testTitle");
		
		second = new Event();
		second.setCreator(secondUser);
		second.setDate(currentDate);
		second.setDescription("secondTestDesc");
		second.setEventID(2);
		second.setTitle("secondTestTitle");
		eventRest = new EventRestController();
		mockMvc = MockMvcBuilders.standaloneSetup(eventRest).build();
		
	}
	
	@Test
	public void getAllEventsTest() throws Exception
	{
		PowerMockito.mockStatic(EventDAO.class);
		//when(EventDAO.getAllEvents()).thenReturn(Arrays.asList(first,second));
		BDDMockito.given(EventDAO.getAllEvents()).willReturn(Arrays.asList(first,second));
		mockMvc.perform(get("/event/getAll"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$",hasSize(2)))
		.andExpect(jsonPath("$[0].title",is(first.getTitle())))
		.andExpect(jsonPath("$[0].creator",is(first.getCreator().getUsername())))
		.andExpect(jsonPath("$[0].date",is(first.getDate().toString())))
		.andExpect(jsonPath("$[0].description",is(first.getDescription())))
		.andExpect(jsonPath("$[1].title",is(second.getTitle())))
		.andExpect(jsonPath("$[1].creator",is(second.getCreator().getUsername())))
		.andExpect(jsonPath("$[1].date",is(second.getDate().toString())))
		.andExpect(jsonPath("$[1].description",is(second.getDescription())));
	}
}
