/**
 * 
 */
package com.sjsu.cmpe273Server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

/**
 * @author bhargav
 *
 */
@Configuration
public class MongoConfig {

	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {

		MongoClient mongo = new MongoClient("localhost");
		SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongo, "test");
		return simpleMongoDbFactory;

	}
	
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

		// show error, should off on production to speed up performance
		mongoTemplate.setWriteConcern(WriteConcern.SAFE);

		return mongoTemplate;

	}
	
}
