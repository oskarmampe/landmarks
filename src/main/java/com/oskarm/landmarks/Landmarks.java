package com.oskarm.landmarks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.net.URI;
import java.util.HashMap;
import java.util.List;


@RestController
public class Landmarks {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String template = "Hello %s";

    @RequestMapping(value="/landmark", params = "format=json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LandmarkModel>> getAllJsonLandmark(){
        return ResponseEntity.ok(jdbcTemplate.query(
                "SELECT id, name, city FROM landmarks",
                (rs, rowNum) -> new LandmarkModel(rs.getLong("id"), rs.getString("name"), rs.getString("city"))
        ));
    }
    @RequestMapping(value="/landmark", params = "format=xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody
    ResponseEntity<List<LandmarkModel>> getAllXMLLandmark(){
        return ResponseEntity.ok(jdbcTemplate.query(
                "SELECT id, name, city FROM landmarks",
                (rs, rowNum) -> new LandmarkModel(rs.getLong("id"), rs.getString("name"), rs.getString("city"))
        ));
    }

    @RequestMapping(value="/landmark/city/{city}", params = "format=json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LandmarkModel>> getJsonCityLandmark(@PathVariable String city){
        return ResponseEntity.ok(jdbcTemplate.query(
                "SELECT id, name, city FROM landmarks WHERE city = ?", new Object[] { city },
                (rs, rowNum) -> new LandmarkModel(rs.getLong("id"), rs.getString("name"), rs.getString("city"))
        ));
    }
    @RequestMapping(value="/landmark/city/{city}", params = "format=xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody
    ResponseEntity<List<LandmarkModel>> getXMLCityLandmark(@PathVariable String city){
        return ResponseEntity.ok(jdbcTemplate.query(
                "SELECT id, name, city FROM landmarks WHERE city = ?", new Object[] { city },
                (rs, rowNum) -> new LandmarkModel(rs.getLong("id"), rs.getString("name"), rs.getString("city"))
        ));
    }

    @RequestMapping(value="/landmark/id/{id}", params = "format=json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LandmarkModel> getJsonLandmark(@PathVariable long id){
        return ResponseEntity.ok(jdbcTemplate.query(
                "SELECT id, name, city FROM landmarks WHERE id = ?", new Object[] { id },
                (rs, rowNum) -> new LandmarkModel(rs.getLong("id"), rs.getString("name"), rs.getString("city"))
        ).get(0));
    }

    @RequestMapping(value="/landmark/id/{id}", params = "format=xml", produces = MediaType.APPLICATION_XML_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LandmarkModel> getXMLLandmark(@PathVariable long id){
        return ResponseEntity.ok(jdbcTemplate.query(
                "SELECT id, name, city FROM landmarks WHERE id = ?", new Object[] { id },
                (rs, rowNum) -> new LandmarkModel(rs.getLong("id"), rs.getString("name"), rs.getString("city"))
        ).get(0));
    }

    @RequestMapping(value="/landmark/update", params = "format=json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<HashMap> updateJsonLandmark(LandmarkModel landmark){
        int result = jdbcTemplate.update(
                "UPDATE landmarks SET name = ?, city = ? WHERE id = ?", new Object[] {landmark.getName(), landmark.getCity(), landmark.getId()});
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "200");
        map.put("message", "Object successfully updated");
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value="/landmark/update", params = "format=xml", produces = MediaType.APPLICATION_XML_VALUE, method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<HashMap> updateXMLLandmark(LandmarkModel landmark){
        jdbcTemplate.update(
                "UPDATE landmarks SET name = ?, city = ? WHERE id = ?", new Object[] {landmark.getName(), landmark.getCity(), landmark.getId()});
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "200");
        map.put("message", "Object successfully updated!");
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value="/landmark/create", params = "format=json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<HashMap> createJsonLandmark(@RequestParam String name, @RequestParam String city){
        jdbcTemplate.update(
                "INSERT INTO landmarks (name, city) VALUES (?, ?)", new Object[] {name, city});
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "201");
        map.put("message", "Object successfully created!");
        return new ResponseEntity<>(map, null, HttpStatus.CREATED);
    }

    @RequestMapping(value="/landmark/create", params = "format=xml", produces = MediaType.APPLICATION_XML_VALUE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<HashMap> createXMLLandmark(@RequestParam String name, @RequestParam String city){
        jdbcTemplate.update(
                "INSERT INTO landmarks (name, city) VALUES (?, ?)", new Object[] {name, city});
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "201");
        map.put("message", "Object successfully created!");
        return new ResponseEntity<>(map, null, HttpStatus.CREATED);
    }

    @RequestMapping(value="/landmark/delete/{id}", params = "format=json", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<HashMap> deleteJsonLandmark(@PathVariable long id){
        jdbcTemplate.update(
                "DELETE FROM landmarks WHERE id = ?", new Object[] { id }
        );
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "200");
        map.put("message", "Object successfully deleted!");
        return new ResponseEntity<HashMap>(map, null, HttpStatus.OK);
    }

    @RequestMapping(value="/landmark/delete/{id}", params = "format=xml", produces = MediaType.APPLICATION_XML_VALUE, method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<HashMap> deleteXMLLandmark(@PathVariable long id){
        jdbcTemplate.update(
                "DELETE FROM landmarks WHERE id = ?", new Object[] { id }
        );
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "200");
        map.put("message", "Object successfully deleted!");
        return new ResponseEntity<HashMap>(map, null, HttpStatus.OK);
    }



}
