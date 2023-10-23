package com.efectivale.credenciales.serviceImp;

import com.efectivale.credenciales.serviceDao.CredencialesServiceDao;
import com.efectivale.credenciales.utils.Utilidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.efectivale.credenciales.models.CredencialesGeneradas;
import com.efectivale.credenciales.models.CredencialesResponse;
import com.efectivale.credenciales.models.ErrorResponse;
import com.efectivale.credenciales.models.LoginRequest;
import com.efectivale.credenciales.models.RequestAPI;
import com.efectivale.credenciales.models.Response;
import com.efectivale.credenciales.models.Usuario;
import com.efectivale.credenciales.models.ApiResponse;
import com.efectivale.credenciales.models.CredencialDetalle;

@Service
public class CredencialesServiceImpl implements CredencialesServiceDao {

	@Autowired
	Utilidades func;

//	public ResponseEntity<ApiResponse> generaCredencial(Usuario usuario) {
//
//		ApiResponse apiResponse = null;
//		ResponseEntity<ApiResponse> response = null;
//
//		try {
//
//			Usuario usr = func.obtenerUsuario(usuario);
//
//			if (usr == null) {
//				apiResponse = new ErrorResponse(500, "Operacion fallida", "El usuario no existe", "");
//				response = new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
//				return response;
//			}
//
//			char[] charsUsuario = usuario.getNombre().toCharArray();
//			char[] charsPassword = usuario.getPassword().toCharArray();
//
//			String consumerKey = func.generador(charsUsuario);
//			String consumerSecret = func.generador(charsPassword);
//			CredencialesGeneradas credeGen = new CredencialesGeneradas(usuario.getNombre(), consumerKey,
//					consumerSecret);
//			apiResponse = new Response(200, "Operacion Exitosa", credeGen);
//			response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
//
//		} catch (Exception e) {
//
//			apiResponse = new ErrorResponse(500, "Operacion fallida", "http://efevserv.com/InternalError",
//					e.getMessage());
//			response = new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return response;
//	}

	public ResponseEntity<ApiResponse> generaCredencial(String valor) {

		ApiResponse apiResponse = null;
		ResponseEntity<ApiResponse> response = null;

		try {
			char[] charsValor = valor.toCharArray();
			String consumerSecret = func.generador(charsValor);
			if (consumerSecret.isEmpty() || consumerSecret == null) {

				apiResponse = new ErrorResponse(500, "Operacion fallida", "http://efevserv.com/BadRequest",
						"No se pudieron generar las credenciales");
				response = new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				CredencialesGeneradas credeGen = new CredencialesGeneradas(consumerSecret);
				apiResponse = new Response(200, "Operacion Exitosa", credeGen);
				response = new ResponseEntity<>(apiResponse, HttpStatus.OK);
			}
		} catch (Exception e) {

			apiResponse = new ErrorResponse(500, "Operacion fallida", "http://efevserv.com/InternalError",
					e.getMessage());
			response = new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

//	public ResponseEntity<ApiResponse> obtieneCredenciales(LoginRequest loginr) {
//
//		ApiResponse apiresponse = null;
//		ResponseEntity<ApiResponse> response = null;
//
//		try {
//			// Validar la existencia del usuario
//			Usuario usr = func.obtenerUsuario(loginr);
//
//			if (usr == null) {
//				apiresponse = new Response(404, "Operación fallida", "No existe el usuario");
//				response = new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);
//				return response;
//			}
//
//			// Validar si tiene una credencial
//
//			CredencialDetalle cred = func.obtenerCredencialDetalle(usr.getId());
//
//			if (cred == null) {
//				apiresponse = new Response(404, "Operación fallida", "No tiene credencial asociada");
//				response = new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);
//				return response;
//			} else {
//				// Validar si la credencial esta asociada al api
//				CredencialesResponse cred_r = func.obtenerCredencial(cred.getCredencial_Id());
//
//				if (cred_r == null) {
//					apiresponse = new Response(404, "Operación fallida", "La credencial no esta asociada al API");
//					response = new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);
//					return response;
//				}
//
//				apiresponse = new Response(200, "Operación exitosa", cred_r);
//				response = new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
//			}
//
//		} catch (Exception e) {
//			apiresponse = new ErrorResponse(500, "Operacion fallida", "http://efevserv.com/InternalError",
//					e.getMessage());
//			response = new ResponseEntity<>(apiresponse, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return response;
//	}

	
	public ResponseEntity<ApiResponse> obtieneCredenciales(RequestAPI request) {

		ApiResponse apiresponse = null;
		ResponseEntity<ApiResponse> response = null;

		try {
			// Validar la existencia del usuario
			boolean usr = func.obtenerUsuario(request.getUser_id());	
			
			
			if (!usr) {
				apiresponse = new Response(404, "Operación fallida", "No existe el usuario");
				response = new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);
				return response;
			}
			
			//Validar si el api existe busqueda por id
			
			boolean api_exist = func.verificaAPI(request.getApi_id());
			
			if(!api_exist) {
				apiresponse = new Response(404, "Operación fallida", "No existe el API");
				response = new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);
				return response;
			}
			
			// Validar si la existe credencial ligada al usuario y al API
			CredencialDetalle cred = func.obtenerCredencialDetalle(request.getUser_id(),request.getApi_id());

			if (cred == null) {
				apiresponse = new Response(404, "Operación fallida", "No tiene credencial asociada");
				response = new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);
				return response;
			} else {
				// Validar si la credencial esta asociada al api
				CredencialesResponse cred_r = func.obtenerCredencial(cred.getCredencial_Id());

				if (cred_r == null) {
					apiresponse = new Response(404, "Operación fallida", "La credencial no esta asociada al API");
					response = new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);
					return response;
				}				

				cred_r.setRolTipo(cred.getRolTipo());					
				apiresponse = new Response(200, "Operación exitosa", cred_r);
				response = new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
			}

		} catch (Exception e) {
			apiresponse = new ErrorResponse(500, "Operacion fallida", "http://efevserv.com/InternalError",
					e.getMessage());
			response = new ResponseEntity<>(apiresponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
}
