package com.oskarm.landmarks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class LandmarksApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(LandmarksApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LandmarksApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {

		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE landmarks IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE landmarks(" +
				"id SERIAL, name VARCHAR(255), city VARCHAR(255))");

        ArrayList<Object[]> records = new ArrayList<>();

        records.add(new String[] {"Fountains Abbey & Studley Royal Water Garden", "Harrogate"});
        records.add(new String[] {"Henry Moore Institute", "Leeds"});
        records.add(new String[] {"The Deep", "Hull"});
        records.add(new String[] {"RHS Harlow Carr", "Harrogate"});
        records.add(new String[] {"York Minster", "York"});
        records.add(new String[] {"Salts Mills", "Shipley"});
        records.add(new String[] {"Yorkshire Wildlife Park", "Doncaster"});
        records.add(new String[] {"Brimham Rocks", "Harrogate"});
        records.add(new String[] {"The Hepworth", "Wakefield"});
        records.add(new String[] {"Kirkstall Abbey", "Leeds"});
        records.add(new String[] {"Betty’s", "Harrogate"});
        records.add(new String[] {"Captain Cook Memorial Museum", "Whitby"});
        records.add(new String[] {"Ripon Cathedral", "Harrogate"});
        records.add(new String[] {"Skipton Castle", "Skipton"});
        records.add(new String[] {"Forbidden Corner", "Leyburn"});
        records.add(new String[] {"Bronte Parsonage Museum", "Hamworth"});
        records.add(new String[] {"Clifford’s Tower", "York"});
        records.add(new String[] {"Malham Cove", "Skipton"});
        records.add(new String[] {"Flamingo Land", "Malton"});
        records.add(new String[] {"Castle Howard", "York"});
        records.add(new String[] {"Yorkshire Sculpture Park", "Wakefield"});
        records.add(new String[] {"York Dungeon", "York"});
        records.add(new String[] {"White Scar Caves", "Carnforth"});
        records.add(new String[] {"North Yorkshire Moors Railway", "Pickering"});
        records.add(new String[] {"Bolton Castle", "Leyburn"});
        records.add(new String[] {"National Science and Media Museum", "Bradford"});
        records.add(new String[] {"Leeds Art Gallery", "Leeds"});
        records.add(new String[] {"Bolton Abbey", "Skipton"});
        records.add(new String[] {"Bolton Abbey", "Skipton"});
        records.add(new String[] {"York Art Gallery", "York"});
        records.add(new String[] {"National Railway Museum", "York"});

		log.info(Arrays.toString(records.toArray()));
		records.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		// Uses JdbcTemplate's batchUpdate operation to bulk load data
		jdbcTemplate.batchUpdate("INSERT INTO landmarks(name, city) VALUES (?,?)", records);

		log.info("Querying for landmark records where city = 'Leeds':");
		jdbcTemplate.query(
				"SELECT id, name, city FROM landmarks WHERE city = ?", new Object[] { "Leeds" },
				(rs, rowNum) -> new LandmarkModel(rs.getLong("id"), rs.getString("name"), rs.getString("city"))
		).forEach(landmark -> log.info(landmark.toString()));
	}
}
