package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbIntegrityException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			
			st.setInt(1, id);
			rs  = st.executeQuery();
			if (rs.next()) {	
				Department department = instantiateDepartment(rs);
				return instantiateSeller(rs, department);
			}
			else {
				return  null;
			}
			
			
			
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("DepartmentId"),rs.getString("DepName"));
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List <Seller> sellers = new ArrayList<>();
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName"
					+ " FROM seller INNER JOIN department"
					+ " ON seller.DepartmentId = department.Id"
					+ " ORDER BY Name");
			
			rs  = st.executeQuery();
			
			Map<Integer,Department> map = new HashMap<>();
			while (rs.next()) {	
				Department dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(dep.getId(), dep);
				}
				sellers.add(instantiateSeller(rs, dep));
			}
				return sellers;
			
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List <Seller> sellers = new ArrayList<>();
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName"
					+ " FROM seller INNER JOIN department"
					+ " ON seller.DepartmentId = department.Id"
					+ " WHERE DepartmentId = ?"
					+ " ORDER BY Name");
			
			
			st.setInt(1, department.getId());
			rs  = st.executeQuery();
			
			Map<Integer,Department> map = new HashMap<>();
			while (rs.next()) {	
				Department dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(dep.getId(), dep);
				}
				sellers.add(instantiateSeller(rs, dep));
			}
				return sellers;
			
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		return new Seller(rs.getInt("id"),
				rs.getString("name"), 
				rs.getString("email"), 
				rs.getDate("birthDate"), 
				rs.getDouble("baseSalary"),
				department);
	}
}
