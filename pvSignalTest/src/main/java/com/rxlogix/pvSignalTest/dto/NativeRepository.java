package com.rxlogix.pvSignalTest.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class NativeRepository {
    @Autowired 
    private NamedParameterJdbcTemplate jdbcTemplate;

    public NativeQueryDTO runmyquery(Integer i){        
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("defaultName", i);

        String sql = " select c.name, count(o) as orderCount  "
                   + " from customers c, orders o "
                   + " where c.id = o.customer_id and c.id = :customerId "
                   + " group by c.name ";
        
        String sql1 = " select * from CATEGORY " 
        			+ " where default_name = :defaultName" ;
        

        return (NativeQueryDTO)jdbcTemplate.queryForObject(
            sql1, parameters, BeanPropertyRowMapper.newInstance(NativeQueryDTO.class));  
    }
}