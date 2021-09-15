package jpa.test.junittest.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobInCity {
    private String city;
    private String job;
}