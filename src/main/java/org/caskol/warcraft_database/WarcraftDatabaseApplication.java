package org.caskol.warcraft_database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarcraftDatabaseApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(WarcraftDatabaseApplication.class, args);
    }
}
