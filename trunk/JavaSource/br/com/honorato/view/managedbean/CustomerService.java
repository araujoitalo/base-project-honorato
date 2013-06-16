package br.com.honorato.view.managedbean;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
 
@Service
@Scope("prototype")
public class CustomerService 
{
	String message;
 
	public String getMessage() {
		return message;
	}
 
	public void setMessage(String message) {
		this.message = message;
	}
	
	@PreAuthorize("hasRole('ROLE_ZZZ')")
	public void teste(){
		System.out.println("TETETTE");
	}
}
