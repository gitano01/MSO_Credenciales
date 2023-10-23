package com.efectivale.credenciales.serviceDao;

import org.springframework.http.ResponseEntity;

import com.efectivale.credenciales.models.ApiResponse;
import com.efectivale.credenciales.models.LoginRequest;
import com.efectivale.credenciales.models.RequestAPI;
import com.efectivale.credenciales.models.Usuario;

public interface CredencialesServiceDao {
	public ResponseEntity<ApiResponse> generaCredencial(String valor);
	public ResponseEntity<ApiResponse> obtieneCredenciales(RequestAPI request);
	//public ResponseEntity<ApiResponse> obtieneCredenciales(LoginRequest login);
}
