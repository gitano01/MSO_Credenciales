package com.efectivale.credenciales.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efectivale.credenciales.models.ApiResponse;
import com.efectivale.credenciales.models.LoginRequest;
import com.efectivale.credenciales.models.RequestAPI;
import com.efectivale.credenciales.serviceImp.CredencialesServiceImpl;

@RestController
@RequestMapping({ "/credenciales" })
public class CredencialesController {

	private static final Logger logger = Logger.getLogger(CredencialesController.class);

	@Autowired
	CredencialesServiceImpl servCred;
	ApiResponse apiResponse;

	@GetMapping("/generaCredencial/{valor}")
	public ResponseEntity<ApiResponse> generaCredencial(@PathVariable("valor") String valor) {
		return servCred.generaCredencial(valor);
	}
	
//	@PostMapping("/generarPorUsuario")
//	public ResponseEntity<ApiResponse> generaCredentials(@PathVariable String valorParam) {
//		return servCred.generaCredencial(usuario);
//	}

	@PostMapping("/getCredencial")
	public ResponseEntity<ApiResponse> obtienecredenciales(@RequestBody RequestAPI request){
		
		return servCred.obtieneCredenciales(request);
	}

//	@PostMapping("/logInApi")
//	public ResponseEntity<ApiResponse> obtienecredencialesApi(@RequestBody LoginRequest login) {
//		return servCred.obtieneCredenciales(login);
//	}
//	
//	@GetMapping("/obtener")
//	public ResponseEntity<ApiResponse> obtieneCredentials(){
//			return servCred.obtieneCredenciales();		
//	}
	
	

}
