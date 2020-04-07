package br.com.compasso.poll.controller.form;

import br.com.compasso.poll.model.User;

public class UserForm {
	private String name;
	private String cpf;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public User toUser(){
		return new User(name, cpf);
	}
}
