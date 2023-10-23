package com.ext.credenciales.serviceDao;

import org.springframework.http.ResponseEntity;

import com.ext.credenciales.models.ApiResponse;
import com.ext.credenciales.models.LoginRequest;
import com.ext.credenciales.models.RequestAPI;
import com.ext.credenciales.models.Usuario;

public interface CredencialesServiceDao {
	public ResponseEntity<ApiResponse> generaCredencial(String valor);
	public ResponseEntity<ApiResponse> obtieneCredenciales(RequestAPI request);
	//public ResponseEntity<ApiResponse> obtieneCredenciales(LoginRequest login);
}
