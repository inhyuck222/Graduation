package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OfferMapper implements RowMapper<Offer> {

	
		public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Offer offer =new Offer();
			
			offer.setId(rs.getInt("id"));
			offer.setEmail(rs.getString("email"));
			offer.setPassword(rs.getString("password"));
			offer.setCompany(rs.getString("company"));
			
			return offer;
			
		}
		
	}


