package com.agenda.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.agenda.domain.*;
import com.agenda.factory.ConnectionFactory;
import com.agenda.dao.GrupoContactoDao;

public class GrupoContactoDAOMysqlImpl  implements GrupoContactoDao {
	
	
	@Override
	public List<Grupo> hasGroup(Contacto contacto) {
		List<Grupo> grupos=new ArrayList<Grupo>();
		try(
			Connection connection=ConnectionFactory.getConnection();
			PreparedStatement pStmt=connection.prepareStatement(""
				+ "SELECT g.id,g.nombre,g.descr "
				+ "FROM grupos g "
				+ "INNER JOIN contactos_grupos cg ON cg.grupo=g.id "
				+ "WHERE cg.contacto=? GROUP BY g.id")
		) {
		
			pStmt.setLong(1,contacto.getId());
			ResultSet rs=pStmt.executeQuery();
			
			while(rs.next()) {
				grupos.add(new Grupo(
					rs.getLong(1),
					rs.getString(2),
					rs.getString(3)
				));
			}
		}catch(SQLException e) {
			grupos=null;
		}

		return grupos;
	}
	
	@Override
	public List<Contacto> hasContact(Grupo grupo) {
		List<Contacto> contactos=new ArrayList<Contacto>();
		try(
			Connection connection=ConnectionFactory.getConnection();
			PreparedStatement pStmt=connection.prepareStatement(""
				+ "SELECT c.id,c.nombre,c.apellido,c.telefono,c.email "
				+ "FROM contactos c "
				+ "INNER JOIN contactos_grupos cg ON cg.contacto=c.id "
				+ "WHERE cg.grupo=? GROUP BY c.id")) {
		
			
			
			pStmt.setLong(1,grupo.getId());
			ResultSet rs=pStmt.executeQuery();
			
			while(rs.next()) {
				contactos.add(new Contacto(
					rs.getLong(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5)
				));
			}
		}catch(SQLException e) {
			contactos=null;
		}
		
		return contactos;
	}
	
	@Override
	public void unsetAll(Contacto contacto) {
		try(
			Connection connection=ConnectionFactory.getConnection();
			PreparedStatement pStmt=connection.prepareStatement(""
				+ "DELETE cg FROM contactos_grupos cg WHERE cg.contacto=?")) {
		
			pStmt.setLong(1,contacto.getId());
			pStmt.executeUpdate();
		}catch(SQLException e) {}
	}
	
	@Override
	public void updateGroups(Contacto contacto, List<Grupo> grupos) {
		try(
			Connection connection=ConnectionFactory.getConnection();
			PreparedStatement pStmt=connection.prepareStatement(""
				+ "INSERT INTO contactos_grupos (contacto,grupo) "
				+ "VALUES (?,?)")) {
		
			this.unsetAll(contacto);
			 
			for(Grupo g:grupos) {
				pStmt.setLong(1,contacto.getId());
				pStmt.setLong(2,g.getId());
				pStmt.addBatch();
			}
			pStmt.executeBatch();
		}catch(SQLException e) {}
	}
}	
