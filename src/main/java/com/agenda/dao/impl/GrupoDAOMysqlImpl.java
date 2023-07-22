package com.agenda.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.agenda.domain.Grupo;
import com.agenda.factory.ConnectionFactory;

import com.agenda.dao.GrupoDao;

public class GrupoDAOMysqlImpl implements GrupoDao {
	
	@Override
	public Grupo getById(Long id) throws SQLException {
		final Connection connection=ConnectionFactory.getConnection();
		final PreparedStatement pStmt=connection.prepareStatement("SELECT * FROM grupos WHERE id=?");
		Grupo grupo=null;
		
		pStmt.setLong(1, id);
		ResultSet rs=pStmt.executeQuery();
		
		if (!rs.isBeforeFirst() ) {    
		    throw new SQLException("Not Found");
		} 
		rs.next();
		grupo=new Grupo(
			rs.getLong(1),
			rs.getString(2),
			rs.getString(3)
			);
		pStmt.close();
		connection.close();
		return grupo; 
	}
	
	@Override
	public List<Grupo> findAll() throws SQLException {
		final Connection connection=ConnectionFactory.getConnection();
		final String sql="SELECT * FROM grupos";
		List<Grupo> listado=new ArrayList<Grupo>();
		
		Statement stmt=connection.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		
		while(rs.next()) {
			listado.add(new Grupo(
					rs.getLong(1),
					rs.getString(2),
					rs.getString(3)
					));
		}
		connection.close();
		return listado;
	}
	
	@Override
	public void create(Grupo grupo) throws SQLException  {
		final Connection connection=ConnectionFactory.getConnection();
		final PreparedStatement pStmt=connection.prepareStatement("INSERT INTO grupos (nombre,descr) VALUES (?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
		
		pStmt.setString(1,grupo.getNombre());
		pStmt.setString(2,grupo.getDescripcion());
		pStmt.executeUpdate();
		pStmt.close();
		connection.close();

	}
	
	@Override
	public void update(Grupo grupo) throws SQLException {
		final Connection connection=ConnectionFactory.getConnection();
		final PreparedStatement pStmt=connection.prepareStatement("UPDATE grupos SET nombre=?, descr=? WHERE grupos.id=?;"); 
		
		pStmt.setString(1,grupo.getNombre());
		pStmt.setString(2,grupo.getDescripcion());
		pStmt.setLong(3,grupo.getId());
		pStmt.executeUpdate();
		pStmt.close();		
		connection.close();
	}
	
	@Override
	public void delete(Long id) throws SQLException {		
		final Connection connection=ConnectionFactory.getConnection();
		final PreparedStatement pStmt=connection.prepareStatement("DELETE FROM grupos WHERE id=?;");
		pStmt.setLong(1,id);
		pStmt.executeUpdate();
		pStmt.close();		
		connection.close();
	}
	
	@Override
	public List<Grupo> search(String keyword) throws SQLException {
		List<Grupo> listado=new ArrayList<Grupo>();
		final Connection connection=ConnectionFactory.getConnection();
		final PreparedStatement pStmt=connection.prepareStatement("SELECT * FROM grupos WHERE LOWER(nombre) LIKE LOWER(?) OR LOWER(descr) LIKE LOWER(?)");
		keyword=keyword.replace("%",""); // avoid hacky chars
		keyword=String.format("%%%s%%",keyword); // set %keywords% string on Db
		pStmt.setString(1, keyword);
		pStmt.setString(2, keyword);
		
		ResultSet rs=pStmt.executeQuery();
		while(rs.next()) {
			listado.add(new Grupo(
					rs.getLong(1),
					rs.getString(2),
					rs.getString(3)
					));
		}
		pStmt.close();		
		connection.close();
		return listado;
	}
}
