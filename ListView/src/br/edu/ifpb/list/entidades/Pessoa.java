package br.edu.ifpb.list.entidades;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pessoa implements Serializable{

	@SerializedName("id")
	private int id;
	
	@SerializedName("fullName")
	private String nome;

	@SerializedName("typeInscription")
	private String inscription;

	@SerializedName("email")
	private String email;

	@SerializedName("isDelivered")
	private boolean entregue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInscription() {
		return inscription;
	}

	public void setInscription(String inscription) {
		this.inscription = inscription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEntregue() {
		return entregue;
	}

	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}
	
}
