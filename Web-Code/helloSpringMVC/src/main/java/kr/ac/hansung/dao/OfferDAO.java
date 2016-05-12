package kr.ac.hansung.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//@Component�뒗 �씠 �겢�옒�뒪瑜� �옄�룞�쑝濡� 鍮덉쑝濡� �꽕�젙�빐 以��떎.
@Component("offerDAO")
public class OfferDAO {

	private JdbcTemplate jdbcTemplateObject;

	// @Autowired 寃쎌슦 type�씠 媛숈� 寃쎌슦瑜� 泥섎━�븳�떎.
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public int getRowCount() {
		String sqlStatement = "select count(*) from offers";
		return jdbcTemplateObject.queryForObject(sqlStatement, Integer.class);// �븯�굹�쓽
																				// �삤釉뚯젥�듃

	}

	// Querying and returning a single object
	public Offer getOffer(String name) {

		String sqlStatement = "select * from offers where name=?";

		return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { name }, new OfferMapper());

	}

	// Querying and returning multiple object
	public List<Offer> getOffers() {

		String sqlStatement = "select * from offers";

		return jdbcTemplateObject.query(sqlStatement, new OfferMapper()); // Anonymous
																			// Classes

	}
	
	public boolean insert(Offer offer){
		
		String name = offer.getName();
		String email = offer.getEmail();
		String text = offer.getText();
		
		String sqlStatement="insert into offers (name, email, text) values (?,?,?)";
		return (jdbcTemplateObject.update(sqlStatement, new Object[]{name, email, text})==1);
	}

	
	public boolean update(Offer offer){
		
		int id = offer.getId();
		String name = offer.getName();
		String email = offer.getEmail();
		String text = offer.getText();
		
		String sqlStatement="update offers set name=?, email=?, text=? where id=?";
		return (jdbcTemplateObject.update(sqlStatement, new Object[]{name, email, text, id})==1);
	}
	
	public boolean delete (int id) {
		
		String sqlstatement="delete from offers where id=?";
		return(jdbcTemplateObject.update(sqlstatement,new Object[]{id})==1);
	}
	
	

	
}
