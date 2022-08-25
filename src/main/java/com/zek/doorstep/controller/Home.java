/**
 * 
 */
package com.zek.doorstep.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author EK
 *
 */
@Controller
public class Home {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = Arrays.asList("5 - LTL", "10 - LTL", "15 - LTL", "20 - LTL", "25 - LTL", "35 - LTL", "45 - LTL");

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);

        return "home.html"; //view
    }

    // /hello?name=kotlin
    @GetMapping("/test")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") 
			String name, Model model) {

        model.addAttribute("message", name);

        return "home.html"; //view
    }
    
    @Autowired JdbcTemplate jt;
    
    @GetMapping("/db")
    public String db() throws SQLException {

    	StringBuilder sb  = new StringBuilder();
    	sb.append(jt.getDataSource().getConnection().getMetaData().getDatabaseProductName());
    	sb.append(jt.queryForList("select * from users"));
        return sb.toString();
    }

}