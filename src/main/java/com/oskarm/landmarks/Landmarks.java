package com.oskarm.landmarks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;


@RestController
public class Landmarks {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String template = "Hello %s";

    @RequestMapping(value="/landmark", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<LandmarkModel> getLandmark(@RequestParam(value="name", defaultValue="World") String name){
        return jdbcTemplate.query(
                "SELECT id, name, city FROM landmarks WHERE city = ?", new Object[] { "Leeds" },
                (rs, rowNum) -> new LandmarkModel(rs.getLong("id"), rs.getString("name"), rs.getString("city"))
        );
    }

}
