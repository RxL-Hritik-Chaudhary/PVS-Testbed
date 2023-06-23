package com.rxlogix.pvSignalTest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.ListUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rxlogix.testEngine.BrowserSetup;

@CrossOrigin("http://localhost:3000")
@Component
public class ExStatusRepository {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExStatusRepository.class);
	
	@Autowired 
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void deleteAutomationAlerts() {
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource();
	        String deletionQuery = " UPDATE rconfig "
	        		+ " SET is_deleted=1 "
	        		+ " WHERE name like 'Smoke%' ";
	        
	        jdbcTemplate.update(deletionQuery, namedParameters);
		}
		catch(Exception ex) {
			logger.error("Deletion query failed with trace {}",ex);
		}
		
	}

    public List<Map<String, Object>> fetchExecutionStatus(){        
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        //parameters.addValue("name", name);

     /*   String sql = " select * from EX_STATUS "
        		   + " where name = :name OR name LIKE 'Smoke%' " 
        		   + " ORDER BY DATE_CREATED DESC" ; */
        
   /*     String sqlExecutionAggAlert = "SELECT DISTINCT EX_STATUS.CONFIG_ID,EX_STATUS.NAME,EX_STATUS.TYPE,EX_STATUS.FREQUENCY,EX_STATUS.DATE_CREATED, count(agg.id) pe_count, EX_STATUS.EX_STATUS,config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000) products,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000) product_group, EX_STATUS.START_TIME,EX_STATUS.END_TIME, "+
        		"DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) STACK_TRACE "+
        		 "FROM EX_STATUS "+
        		 "JOIN AGG_ALERT agg ON EX_STATUS.CONFIG_ID=agg.ALERT_CONFIGURATION_ID "+
        		 "JOIN RCONFIG config ON config.id = EX_STATUS.CONFIG_ID "+
        		 "where EX_STATUS.TYPE = 'Aggregate Case Alert' and agg.id is not null and EX_STATUS.EX_STATUS = 'COMPLETED' and EX_STATUS.NAME LIKE 'Smoke%' "+
        		 "group by EX_STATUS.CONFIG_ID, EX_STATUS.NAME, EX_STATUS.TYPE, EX_STATUS.FREQUENCY, EX_STATUS.DATE_CREATED, EX_STATUS.EX_STATUS, config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000), config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000), EX_STATUS.START_TIME,EX_STATUS.END_TIME, "+
        		 "DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) "+
        		 "UNION "+
        		 "SELECT DISTINCT EX_STATUS.CONFIG_ID,EX_STATUS.NAME,EX_STATUS.TYPE,EX_STATUS.FREQUENCY,EX_STATUS.DATE_CREATED, 0 pe_count, EX_STATUS.EX_STATUS,config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000) products,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000) product_group,EX_STATUS.START_TIME,EX_STATUS.END_TIME, "+
        		 "DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) STACK_TRACE "+
        		 "FROM EX_STATUS "+
        		  "        JOIN RCONFIG config ON config.id = EX_STATUS.CONFIG_ID "+
        		 "where EX_STATUS.TYPE = 'Aggregate Case Alert' and EX_STATUS.EX_STATUS = 'ERROR' and EX_STATUS.NAME LIKE 'Smoke%' "+
        		 "group by EX_STATUS.CONFIG_ID, EX_STATUS.NAME, EX_STATUS.TYPE, EX_STATUS.FREQUENCY, EX_STATUS.DATE_CREATED, EX_STATUS.EX_STATUS,config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000), config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000),EX_STATUS.START_TIME,EX_STATUS.END_TIME, "+
        		 "DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) "+
        		 "UNION "+
        		 "SELECT DISTINCT EX_STATUS.CONFIG_ID,EX_STATUS.NAME,EX_STATUS.TYPE,EX_STATUS.FREQUENCY,EX_STATUS.DATE_CREATED, count(agg.id) pe_count, EX_STATUS.EX_STATUS,config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000) products,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000) product_group,EX_STATUS.START_TIME,EX_STATUS.END_TIME, "+
        		 "DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) STACK_TRACE "+
        		 "FROM EX_STATUS "+
        		  "        JOIN AGG_ON_DEMAND_ALERT agg ON EX_STATUS.CONFIG_ID=agg.ALERT_CONFIGURATION_ID "+
        		   "       JOIN RCONFIG config ON config.id = EX_STATUS.CONFIG_ID "+
        		 "where EX_STATUS.TYPE = 'Aggregate Case Alert' and agg.id is not null and EX_STATUS.EX_STATUS = 'COMPLETED' and EX_STATUS.NAME LIKE 'Smoke%' "+
        		 "group by EX_STATUS.CONFIG_ID, EX_STATUS.NAME, EX_STATUS.TYPE, EX_STATUS.FREQUENCY, EX_STATUS.DATE_CREATED, EX_STATUS.EX_STATUS, config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000), config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000) ,EX_STATUS.START_TIME,EX_STATUS.END_TIME,DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) ";
        
        String sqlExecutionIcrAlert = "SELECT DISTINCT EX_STATUS.CONFIG_ID,EX_STATUS.NAME,EX_STATUS.TYPE,EX_STATUS.FREQUENCY,EX_STATUS.DATE_CREATED, count(icr.id) pe_count, EX_STATUS.EX_STATUS,config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000) products,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000) product_group, EX_STATUS.START_TIME,EX_STATUS.END_TIME, "+
        		 "DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) STACK_TRACE "+
        		  "FROM EX_STATUS "+
        		  "JOIN  SINGLE_CASE_ALERT icr ON EX_STATUS.CONFIG_ID=icr.ALERT_CONFIGURATION_ID "+
        		 " JOIN RCONFIG config ON config.id = EX_STATUS.CONFIG_ID "+
        		 " where EX_STATUS.TYPE = 'Single Case Alert' and icr.id is not null and EX_STATUS.EX_STATUS = 'COMPLETED' and EX_STATUS.NAME LIKE 'Smoke%' "+
        		  "group by EX_STATUS.CONFIG_ID, EX_STATUS.NAME, EX_STATUS.TYPE, EX_STATUS.FREQUENCY, EX_STATUS.DATE_CREATED, EX_STATUS.EX_STATUS, config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000), config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000), EX_STATUS.START_TIME,EX_STATUS.END_TIME, "+
        		  "DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) "+
        		  "UNION "+
        		  "SELECT DISTINCT EX_STATUS.CONFIG_ID,EX_STATUS.NAME,EX_STATUS.TYPE,EX_STATUS.FREQUENCY,EX_STATUS.DATE_CREATED, 0 pe_count, EX_STATUS.EX_STATUS,config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000) products,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000) product_group,EX_STATUS.START_TIME,EX_STATUS.END_TIME, "+
        		  "DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) STACK_TRACE "+
        		  "FROM EX_STATUS "+
        		   "        JOIN RCONFIG config ON config.id = EX_STATUS.CONFIG_ID "+
        		  "where EX_STATUS.TYPE = 'Single Case Alert' and EX_STATUS.EX_STATUS = 'ERROR' and EX_STATUS.NAME LIKE 'Smoke%' "+
        		  "group by EX_STATUS.CONFIG_ID, EX_STATUS.NAME, EX_STATUS.TYPE, EX_STATUS.FREQUENCY, EX_STATUS.DATE_CREATED, EX_STATUS.EX_STATUS,config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000), config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000),EX_STATUS.START_TIME,EX_STATUS.END_TIME, "+
        		  "DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) "+
        		  "UNION "+
        		  "SELECT DISTINCT EX_STATUS.CONFIG_ID,EX_STATUS.NAME,EX_STATUS.TYPE,EX_STATUS.FREQUENCY,EX_STATUS.DATE_CREATED, count(icr.id) pe_count, EX_STATUS.EX_STATUS,config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000) products,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000) product_group,EX_STATUS.START_TIME,EX_STATUS.END_TIME, "+
        		  "DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) STACK_TRACE "+
        		  "FROM EX_STATUS "+
        		   "        JOIN SINGLE_ON_DEMAND_ALERT icr ON EX_STATUS.CONFIG_ID=icr.ALERT_CONFIGURATION_ID "+
        		    "       JOIN RCONFIG config ON config.id = EX_STATUS.CONFIG_ID "+
        		  "where EX_STATUS.TYPE = 'Single Case Alert' and icr.id is not null and EX_STATUS.EX_STATUS = 'COMPLETED' and EX_STATUS.NAME LIKE 'Smoke%' "+
        		  "group by EX_STATUS.CONFIG_ID, EX_STATUS.NAME, EX_STATUS.TYPE, EX_STATUS.FREQUENCY, EX_STATUS.DATE_CREATED, EX_STATUS.EX_STATUS, config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_GROUP_SELECTION, 3000), config.ADHOC_RUN,DBMS_LOB.substr(config.PRODUCT_SELECTION, 3000) ,EX_STATUS.START_TIME,EX_STATUS.END_TIME,DBMS_LOB.substr(EX_STATUS.STACK_TRACE,1000) ";
        */
        String sqlAggAlert = "select DISTINCT exs.CONFIG_ID,exs.NAME,rc.ADHOC_RUN, sca.NAME,exs.EX_STATUS, count(*) over (PARTITION BY exs.name) as pec_count, " 
        							+ " exs.TYPE,exs.FREQUENCY,exs.DATE_CREATED,DBMS_LOB.substr(rc.PRODUCT_SELECTION, 3000) products,DBMS_LOB.substr(rc.PRODUCT_GROUP_SELECTION, 3000) product_group,exs.START_TIME,exs.END_TIME, DBMS_LOB.substr(exs.STACK_TRACE,1500) STACK_TRACE "
        							+ "from EX_STATUS exs left join RCONFIG rc on exs.CONFIG_ID=rc.ID left join SINGLE_CASE_ALERT sca on exs.CONFIG_ID = sca.ALERT_CONFIGURATION_ID "
        							+ "where exs.TYPE='Single Case Alert' and rc.ADHOC_RUN=0 and rc.IS_DELETED=0 and exs.NAME like 'Smoke%'";

        String sqlAggAdhocAlert = "select DISTINCT exs.CONFIG_ID,exs.NAME,rc.ADHOC_RUN, sca.NAME,exs.EX_STATUS, count(*) over (PARTITION BY exs.name) as pec_count, "
									+ " exs.TYPE,exs.FREQUENCY,exs.DATE_CREATED,DBMS_LOB.substr(rc.PRODUCT_SELECTION, 3000) products,DBMS_LOB.substr(rc.PRODUCT_GROUP_SELECTION, 3000) product_group,exs.START_TIME,exs.END_TIME, DBMS_LOB.substr(exs.STACK_TRACE,1500) STACK_TRACE "
        							+ "from EX_STATUS exs left join RCONFIG rc on exs.CONFIG_ID=rc.ID left join SINGLE_ON_DEMAND_ALERT sca on exs.CONFIG_ID = sca.ALERT_CONFIGURATION_ID "
        							+ "where exs.TYPE='Single Case Alert' and rc.ADHOC_RUN=1 and rc.IS_DELETED=0 and exs.NAME like 'Smoke%'" ;
        		
        String sqlIcrAlert = "select DISTINCT exs.CONFIG_ID,exs.NAME,rc.ADHOC_RUN, sca.NAME,exs.EX_STATUS, count(*) over (PARTITION BY exs.name) as pec_count, "
									+ " exs.TYPE,exs.FREQUENCY,exs.DATE_CREATED,DBMS_LOB.substr(rc.PRODUCT_SELECTION, 3000) products,DBMS_LOB.substr(rc.PRODUCT_GROUP_SELECTION, 3000) product_group,exs.START_TIME,exs.END_TIME, DBMS_LOB.substr(exs.STACK_TRACE,1500) STACK_TRACE "
        							+ "from EX_STATUS exs left join RCONFIG rc on exs.CONFIG_ID=rc.ID left join AGG_ALERT sca on exs.CONFIG_ID = sca.ALERT_CONFIGURATION_ID "
        							+ "where exs.TYPE='Aggregate Case Alert' and rc.ADHOC_RUN=0 and rc.IS_DELETED=0 and exs.NAME like 'Smoke%'";
        		
        String sqlIcrAdhocAlert = "select DISTINCT exs.CONFIG_ID,exs.NAME,rc.ADHOC_RUN, sca.NAME,exs.EX_STATUS, count(*) over (PARTITION BY exs.name) as pec_count, "
									+ " exs.TYPE,exs.FREQUENCY,exs.DATE_CREATED,DBMS_LOB.substr(rc.PRODUCT_SELECTION, 3000) products,DBMS_LOB.substr(rc.PRODUCT_GROUP_SELECTION, 3000) product_group,exs.START_TIME,exs.END_TIME, DBMS_LOB.substr(exs.STACK_TRACE,1500) STACK_TRACE "
        							+ "from EX_STATUS exs left join RCONFIG rc on exs.CONFIG_ID=rc.ID left join AGG_ON_DEMAND_ALERT sca on exs.CONFIG_ID = sca.ALERT_CONFIGURATION_ID "
        							+ "where exs.TYPE='Aggregate Case Alert' and rc.ADHOC_RUN=1 and rc.IS_DELETED=0 and exs.NAME like 'Smoke%'";
        		

        
       List<Map<String, Object>> aggExeStatus = jdbcTemplate.queryForList(
    		   sqlAggAlert, parameters);
       List<Map<String, Object>> aggAdhocExeStatus = jdbcTemplate.queryForList(
    		   sqlAggAdhocAlert, parameters);
       List<Map<String, Object>> icrExeStatus = jdbcTemplate.queryForList(
    		   sqlIcrAlert, parameters);
       List<Map<String, Object>> icrAdhocExeStatus = jdbcTemplate.queryForList(
    		   sqlIcrAdhocAlert, parameters);
        
       List<Map<String, Object>> allAlertsExeStatus = new ArrayList<Map<String, Object>>(aggExeStatus);
       allAlertsExeStatus.addAll(aggAdhocExeStatus);
       allAlertsExeStatus.addAll(icrExeStatus);
       allAlertsExeStatus.addAll(icrAdhocExeStatus);

       
       return allAlertsExeStatus;
    }
}
