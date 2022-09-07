package com.finetwork.Exercice2.clients

import com.finetwork.Exercice2.clients.model.Client
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.nio.charset.StandardCharsets

class ClientControllerSpec extends Specification {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private ClientService clientService = Mock(ClientService.class)

    private MockMvc mvc

    private List<Client> listOfCharacters = new ArrayList<>(List.of(new Client(1,"21695536G","JB", "ordibeldaa#gmail.com", 666666666, new Date(), new Date())));

    private Pageable pageable = PageRequest.of(0, 10);

    private Page<Client> pageResponse = new PageImpl<>(listOfCharacters as List<Client>, pageable, 1)

    void setup() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new ClientController(clientService)).build()
    }

    def "get all clients"() {
        given: "a valid request to get clients"
        String page = "0"
        String size = "10"
        String order = "asc"
        and: "there are clients in the database"
        1 * clientService.getAllClients(page,size,order) >> pageResponse

        expect: "Status is 200 and the response has correct message"
        mvc.perform(MockMvcRequestBuilders.get("/clients/all").queryParam("page", page).queryParam("size", size).queryParam("order", order))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
    }
}
