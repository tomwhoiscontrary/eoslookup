package io.pivotal.labs.eoslookup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CompatibilityControllerTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CameraRepository cameraRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldShowAPageOfLensInformation() throws Exception {
        String responseContent = makeRequest(get("/"));

        assertThat(responseContent, containsString("<h1>Lens Information</h1>"));
    }

    @Test
    public void shouldShowAListOfCamerasOnThePageOfLensInformation() throws Exception {
        cameraRepository.deleteAll();
        cameraRepository.save(new Camera("Canon 123"));
        cameraRepository.save(new Camera("Canon A"));
        cameraRepository.save(new Camera("Canon EOS"));

        String responseContent = makeRequest(get("/"));

        assertThat(responseContent, allOf(
                containsString("<li>Canon 123</li>"),
                containsString("<li>Canon A</li>"),
                containsString("<li>Canon EOS</li>")));
    }

    private String makeRequest(MockHttpServletRequestBuilder request) throws Exception {
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        return result.getResponse().getContentAsString();
    }

}
