package com.superheroes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superheroes.SuperheroesApplication;
import com.superheroes.controller.entity.SuperheroDTO;
import com.superheroes.entity.Publisher;
import com.superheroes.entity.Superhero;
import com.superheroes.repository.SuperheroesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SuperheroesApplication.class)
@WebAppConfiguration
@IntegrationTest("localhost:8080")
public class SuperheroesIntegrationTest {

    @Autowired
    private SuperheroesRepository superheroesRepository;

    RestTemplate template = new TestRestTemplate();

    @Before
    public void setUp() throws Exception {
        superheroesRepository.deleteAll();

        Superhero batman = new Superhero();
        batman.setName("Batman");
        batman.setPublisher(Publisher.DC);
        batman.setSkills(asList("Fly", "Be awesome"));
        batman.setAllies(new HashSet<>());
        batman.setPseudonym("Dark night");
        batman.setFirstAppearance(new SimpleDateFormat("yyyy-MM-dd").parse("2015-01-01"));

        Superhero robin = new Superhero();
        robin.setName("Robin");
        robin.setPublisher(Publisher.DC);
        robin.setSkills(asList("Nothing specially", "Just a human"));
        robin.setAllies(new HashSet<>());
        robin.setPseudonym("Robin");
        robin.setFirstAppearance(new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01"));

        batman.getAllies().add(robin);
        robin.getAllies().add(batman);
        superheroesRepository.save(batman);
        superheroesRepository.save(robin);
    }

    @Test
    public void testGetAllSuperheroes() throws Exception {

        ResponseEntity<String> response = template.getForEntity("http://localhost:8080/superheroes", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List list = new ObjectMapper().readValue(response.getBody(), List.class);

        assertEquals(2, list.size());
        HashMap<String, Object> batman = (HashMap<String, Object>) list.get(0);
        HashMap<String, Object> robin = (HashMap<String, Object>) list.get(1);
        validateBatman(batman);
        validateRobin(robin);
    }

    @Test
    public void testGetBatman() throws Exception {

        ResponseEntity<String> response = template.getForEntity("http://localhost:8080/superheroes/batman", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        HashMap batman = new ObjectMapper().readValue(response.getBody(), HashMap.class);

        validateBatman(batman);
    }

    @Test
    public void testWhenGetNonExistingSuperheroExpectNotFound() throws Exception {
        ResponseEntity<String> response = template.getForEntity("http://localhost:8080/superheroes/batmanx", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateNewSuperheroExpectCreated() throws Exception {
        SuperheroDTO ironman = new SuperheroDTO();
        ironman.setName("Ironman");
        ironman.setPseudonym("Ironman");
        ironman.setPublisher("MARVEL");
        ironman.setSkills(asList("Genius", "Playboy", "Philantropist"));

        ResponseEntity<String> response = template.postForEntity("http://localhost:8080/superheroes", ironman, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ResponseEntity<String> ironmanResponse = template.getForEntity("http://localhost:8080/superheroes/ironman", String.class);
        SuperheroDTO ironmanDto = new ObjectMapper().readValue(ironmanResponse.getBody(), SuperheroDTO.class);

        assertEquals(ironmanDto, ironman);
    }

    private void validateBatman(HashMap<String, Object> batman) {
        assertEquals("Batman", batman.get("name"));
        assertEquals("Dark night", batman.get("pseudonym"));
        assertEquals("DC", batman.get("publisher"));
        assertEquals("Fly", ((List)batman.get("skills")).get(0));
        assertEquals("Be awesome", ((List)batman.get("skills")).get(1));
        assertEquals("2015-01-01", batman.get("firstAppearance"));
        assertEquals("Robin", ((Map)((List)batman.get("allies")).get(0)).get("name"));
    }

    private void validateRobin(HashMap<String, Object> robin) {
        assertEquals("Robin", robin.get("name"));
        assertEquals("Robin", robin.get("pseudonym"));
        assertEquals("DC", robin.get("publisher"));
        assertEquals("Nothing specially", ((List)robin.get("skills")).get(0));
        assertEquals("Just a human", ((List)robin.get("skills")).get(1));
        assertEquals("2016-01-01", robin.get("firstAppearance"));
        assertEquals("Batman", ((Map)((List)robin.get("allies")).get(0)).get("name"));
    }
}
