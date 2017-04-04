package com.spring.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component("offersDao")
public class OffersDAO {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	// retrieves all offer objects from the offers table of the database
	public List<Offer> getOffers() {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "Sue");
		// returns a list of offer objects
		return jdbc.query("select * from offers", new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("offerText"));

				return offer;
			}

		});
	}
	
	public boolean update(Offer offer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("update offers set name=:name, offerText=:text, email=:email where id=:id", params) == 1;
		
		
	}
	
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		// update returns number of rows affected by query. 
		// In this case it should be 1
		return jdbc.update("delete from offers where id = :id", params) == 1;
		
	}
	
	public boolean create(Offer offer) {
		
		// returns true if creation succeeds and false if it does not
		// creates set of params to replace placeholders in sql from actual bean properties
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("insert into offers (name, email, offerText) values (:name, :email, :text)", params) == 1;
	}
	
	@Transactional
	public int[] create(List<Offer> offers) {
		// utility method to extract placeholder values from a list of beans
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("insert into offers (id, name, email, offerText) values (:id,:name, :email, :text)", params);
	
		
	}

	public Offer getOffer(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from offers where id = :id", params, new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Offer offer = new Offer();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("offerText"));
				return offer;
			}

		});

	}
}
