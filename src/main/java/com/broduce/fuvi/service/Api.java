package com.broduce.fuvi.service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.broduce.fuvi.service.model.Category;
import com.broduce.fuvi.service.model.Item;

import snaq.db.ConnectionPool;

public class Api {

	private static final Logger logger = Logger.getLogger(Api.class);
	static ConnectionPool pool;

	public static ConnectionPool getPool() {
		if (pool == null) {

			Properties properties = new Properties();
			try {
				properties.load(Api.class
						.getResourceAsStream("/dbpool.properties"));
			} catch (Exception e) {
				Logger.getLogger("Api").error(
						"Can not load database.properties");
			}
			try {
				Class<?> c = Class.forName("com.mysql.jdbc.Driver");
				Driver driver = (Driver) c.newInstance();
				DriverManager.registerDriver(driver);
				pool = new ConnectionPool("fuvi-pool", 20, 50, 3600,
						properties.getProperty("poolname.url",
								"jdbc:mysql://localhost:3306/fuvi"),
						properties.getProperty("poolname.user", "root"),
						properties.getProperty("poolname.password", ""));
			} catch (Exception e) {
				logger.error("init driver error", e);
			}
		}
		return pool;
	}

	public Api() {
	}

	public List<Item> getItemList(int categoryId, long from, int page, int size)
			throws Exception {
		List<Item> result = new ArrayList<Item>();
		Connection con = getPool().getConnection();
		PreparedStatement statement = null;
		try {
			if (from > 0) {
				statement = con
						.prepareStatement("SELECT * FROM item WHERE cat_id = ? AND id <= ? ORDER BY id DESC LIMIT ? OFFSET ?");
				int idx = 0;
				statement.setInt(++idx, categoryId);
				statement.setLong(++idx, from);
				statement.setInt(++idx, size);
				statement.setInt(++idx, page * size);
			} else {
				statement = con
						.prepareStatement("SELECT * FROM item WHERE cat_id = ? ORDER BY id DESC LIMIT ? OFFSET ?");
				int idx = 0;
				statement.setInt(++idx, categoryId);
				statement.setInt(++idx, size);
				statement.setInt(++idx, page * size);
			}
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Item photo = new Item();
				photo.setId(rs.getLong("id"));
				photo.setSrc(rs.getString("src"));
				photo.setSapo(rs.getString("sapo"));
				photo.setCover(rs.getString("cover"));
				photo.setLink(rs.getString("link"));
				photo.setTitle(rs.getString("title"));
				photo.setCtime(rs.getTimestamp("ctime").getTime());
				result.add(photo);
			}
			rs.close();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (statement != null)
				statement.close();
			if (con != null)
				con.close();
		}
		return result;
	}

	public List<Category> getCategoryList() throws Exception {
		List<Category> result = new ArrayList<Category>();
		Connection con = getPool().getConnection();
		try (PreparedStatement statement = con
				.prepareStatement("SELECT * FROM category")) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Category photo = new Category();
				photo.setId(rs.getInt("id"));
				photo.setTitle(rs.getString("title"));
				result.add(photo);
			}
			rs.close();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return result;
	}

}
