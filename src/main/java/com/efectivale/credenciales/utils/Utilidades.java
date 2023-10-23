package com.efectivale.credenciales.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.efectivale.credenciales.dbconfig.PoolConexion;
import com.efectivale.credenciales.models.CredencialDetalle;
import com.efectivale.credenciales.models.CredencialesResponse;
import com.efectivale.credenciales.models.LoginRequest;
import com.efectivale.credenciales.models.Usuario;

@Component
public class Utilidades {

	@Autowired
	PoolConexion pool;

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public String generador(char[] chars) throws Exception{
		String credencialCodificada = null;
		try {
			StringBuilder sb = new StringBuilder(64);
			Random random = new Random();
			for (int i = 0; i < 64; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb.append(c);
			}			
			credencialCodificada = Base64.getEncoder().encodeToString(sb.toString().getBytes());			
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
		return credencialCodificada;
	}

	public List<CredencialesResponse> obtenerCredenciales() throws Exception {

		List<CredencialesResponse> list = null;
		try {
			con = pool.getConnection();
			ps = con.prepareStatement(Constantes.QUERY);
			rs = ps.executeQuery();

			if (rs.next()) {
				list = new ArrayList<CredencialesResponse>();
				while (rs.next()) {

					CredencialesResponse credencial = new CredencialesResponse();
					
					credencial.setCredencialconsumersecret(
							rs.getString(Constantes.HeadersCredenciales.CREDENCIAL_SECRET));					
					list.add(credencial);
				}
				
			}
			return list;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			pool.close(con, ps, rs);
		}
	}

	public Usuario obtenerUsuario(Usuario user) throws Exception {

		Usuario usr = null;
		try {
			con = pool.getConnection();
			ps = con.prepareStatement("select usuarioid from usuarios where usuarionombre= ? and usuariopassword = ?");
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getPassword());
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				usr = new Usuario();
				usr.setId(rs.getInt("usuarioid"));
			}
			

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			pool.close(con, ps, rs);
		}
		return usr;

	}
	
	public boolean obtenerUsuario(int id) throws Exception {

		boolean flag = false;
		try {
			con = pool.getConnection();
			ps = con.prepareStatement("select usuarioid from usuarios where usuarioid= ?");
			ps.setInt(1, id);
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
			

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			pool.close(con, ps, rs);
		}
		return flag;

	}
	

	public Usuario obtenerUsuario(LoginRequest login) throws Exception {

		Usuario usr = null;
		try {
			con = pool.getConnection();
			ps = con.prepareStatement("select usuarioid from usuarios where usuarionombre= ? and usuariopassword = ?");
			ps.setString(1, login.getNombre());
			ps.setString(2, login.getPassword());
			System.out.println(ps.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				usr = new Usuario();
				usr.setId(rs.getInt("usuarioid"));

			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			pool.close(con, ps, rs);
		}

		return usr;
	}
	
	// verificar si existe API
	public boolean verificaAPI(int id_api) throws Exception {
		boolean flag = false;
		try {
		con  = pool.getConnection();
		ps = con.prepareStatement("select api_id from apis where api_id = ? ");
		ps.setInt(1, id_api);
		
		rs = ps.executeQuery();
		
		while(rs.next()) {
			flag = true;
		}
		
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}finally {
			pool.close(con, ps, rs);
		}
		return flag;
	}

	public CredencialDetalle obtenerCredencialDetalle(int userId, int api_id) throws Exception {
		CredencialDetalle relation = null;
		try {
			con = pool.getConnection();
			ps = con.prepareStatement(
					"select  cd.credencialdetalle_id, cd.credencial_id, cd.api_id,cd.usuario_id , cd.rol_id, r.roltipo  from usuarios u \r\n"
					+ "join credencialdetalle cd on (u.usuarioid = cd.usuario_id) "
					+ "join apis a on(cd.api_id = a.api_id) join roles r on(cd.rol_id=r.rolid) where u.usuarioid = ? and a.api_id = ?");
			ps.setInt(1, userId);
			ps.setInt(2, api_id);

			rs = ps.executeQuery();

			while (rs.next()) {
				relation = new CredencialDetalle();
				relation.setCredencial_Id(rs.getInt("credencial_id"));
				relation.setCredencialDetalleId(rs.getInt("credencialdetalle_id"));
				relation.setUsuario_Id(rs.getInt("usuario_id"));
				relation.setApi_Id(rs.getInt("api_id"));
				relation.setRol_Id(rs.getInt("rol_id"));
				relation.setRolTipo(rs.getString("roltipo"));

			}
			

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			pool.close(con, ps, rs);
		}

		return relation;
	}

	public CredencialesResponse obtenerCredencial(int credencial_id) throws Exception {
		CredencialesResponse credencial = null;

		try {
			con = pool.getConnection();
			ps = con.prepareStatement("Select * from credenciales where credencialid = ?");
			ps.setInt(1, credencial_id);

			rs = ps.executeQuery();

			while (rs.next()) {
				credencial = new CredencialesResponse();				
				credencial.setCredencialconsumersecret(rs.getString(Constantes.HeadersCredenciales.CREDENCIAL_SECRET));				
			}
			

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			pool.close(con, ps, rs);
		}

		return credencial;

	}

}
