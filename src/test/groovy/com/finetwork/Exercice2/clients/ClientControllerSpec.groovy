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

    private ClientController controller

    void setup() throws Exception {
        controller = new ClientController(clientService)
        mvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    def "get all clients"() {
        given: "a valid request to get clients"
        String page = 0
        String size = 10
        String order = "asc"
        and: "database is not empty"
        List<Client> ListOfClients = new ArrayList<>(List.of(new Client(1,"21695536G","JB", "ordibeldaa#gmail.com", 666666666, new Date(), new Date())));

        Pageable pageable = PageRequest.of(Integer.parseInt(page),Integer.parseInt(size));

        Page<Client> pageResponse = new PageImpl<>(ListOfClients as List<Client>, pageable, 1)

        1 * clientService.getAllClients(Integer.parseInt(page),Integer.parseInt(size),order) >> pageResponse

        expect: "Status is 200 and the response has correct message"
        mvc.perform(MockMvcRequestBuilders.get("/clients/all").queryParam("page", page).queryParam("size", size).queryParam("order", order))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
    }

    def "get all empty clients"() {
        given: "a valid request to get clients"
        String page = 0
        String size = 10
        String order = "asc"
        and: "database IS empty"
        List<Client> ListOfClients = new ArrayList<>();

        Pageable pageable = PageRequest.of(Integer.parseInt(page),Integer.parseInt(size));

        Page<Client> pageResponse = new PageImpl<>(ListOfClients as List<Client>, pageable, 1)

        1 * clientService.getAllClients(Integer.parseInt(page),Integer.parseInt(size),order) >> pageResponse

        expect: "Status is 200 and the response has correct message"
        mvc.perform(MockMvcRequestBuilders.get("/clients/all").queryParam("page", page).queryParam("size", size).queryParam("order", order))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
    }
}
