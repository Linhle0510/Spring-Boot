package jpa.test.junittest.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityJobCount {

    public String city;
    public String job;
    public Long count;

}