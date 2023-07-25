package com.agenda.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.agenda.domain.Contacto;
import com.agenda.factory.ConnectionFactory;

import com.agenda.dao.ContactoDao;

public class ContactoDAOMysqlImpl implements ContactoDao {
	
	@Override
	public Contacto getById(Long id) {
		Contacto contacto=null;
		try(
			Connection connection=ConnectionFactory.getConnection();
			PreparedStatement pStmt=connection.prepareStatement("SELECT * FROM contactos WHERE id=?")
		) {
			pStmt.setLong(1, id);
			ResultSet rs=pStmt.executeQuery();
				
			if (!rs.isBeforeFirst() ) {    
			    return null;
			} 
			rs.next();
			contacto=new Contacto(
				rs.getLong(1),
				rs.getString(2),
				rs.getString(3),
				rs.getString(4),
				rs.getString(5)
				);
		
		}catch(SQLException e) {
			contacto=null;
		}
		return contacto; 
	}
	
	@Override
	public List<Contacto> findAll() {
		final String sql="SELECT * FROM contactos";
		List<Contacto> listado=null;
		try(
			Connection connection=ConnectionFactory.getConnection();
			Statement stmt=connection.createStatement()
		){
			listado=new ArrayList<Contacto>();
			ResultSet rs=stmt.executeQuery(sql);
		
			while(rs.next()) {
				listado.add(new Contacto(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5)
						));
			}
		}catch(SQLException e) {
			listado=null;
		}
		
		return listado;
	}
	
	@Override
	public Contacto create(Contacto contacto) {
		try(
				Connection connection=ConnectionFactory.getConnection();
				PreparedStatement pStmt=connection.prepareStatement("INSERT INTO contactos (nombre,apellido,telefono,email) VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)
		){
			Long id=0l;
			pStmt.setString(1,contacto.getNombre());
			pStmt.setString(2,contacto.getApellido());
			pStmt.setString(3,contacto.getTelefono());
			pStmt.setString(4,contacto.getEmail());
			pStmt.executeUpdate();
			ResultSet rs=pStmt.getGeneratedKeys();
			if(rs.next()) {
				id=rs.getLong(1);
			}
			contacto.setId(id);
		}catch(SQLException e) {
			contacto=null;
		}
		return contacto;
	}
	
	@Override
	public void update(Contacto contacto) {
		try(
			Connection connection=ConnectionFactory.getConnection();
			PreparedStatement pStmt=connection.prepareStatement("UPDATE contactos SET nombre=?, apellido=?,telefono=?, email=? WHERE contactos.id=?;")
		){
		
			pStmt.setString(1,contacto.getNombre());
			pStmt.setString(2,contacto.getApellido());
			pStmt.setString(3,contacto.getTelefono());
			pStmt.setString(4,contacto.getEmail());
			pStmt.setLong(5,contacto.getId());
			pStmt.executeUpdate();
		}catch(SQLException e) {}
	}
	
	@Override
	public void delete(Long id) {		
		
		try(
			Connection connection=ConnectionFactory.getConnection();
			PreparedStatement pStmt=connection.prepareStatement("DELETE FROM contactos WHERE id=?;")
		){
			pStmt.setLong(1,id);
			pStmt.executeUpdate();
		}catch(SQLException e) {}
		
	}
	
	@Override
	public List<Contacto> search(String keyword) {
		List<Contacto> listado=new ArrayList<Contacto>();
		try(
			Connection connection=ConnectionFactory.getConnection();
			PreparedStatement pStmt=connection.prepareStatement("SELECT * FROM contactos WHERE LOWER(nombre) LIKE LOWER(?) OR LOWER(apellido) LIKE LOWER(?)")
		){
			keyword=keyword.replace("%",""); // avoid hacky chars
			keyword=String.format("%%%s%%",keyword); // set %keywords% string on Db
			pStmt.setString(1, keyword);
			pStmt.setString(2, keyword);
			
			// https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			// when pStmt close ResultSet close too
			ResultSet rs=pStmt.executeQuery();
			while(rs.next()) {
				listado.add(new Contacto(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5)
						));
			}
		}catch(SQLException e) {
			listado=null;
		}
		
		return listado;
	}
}
