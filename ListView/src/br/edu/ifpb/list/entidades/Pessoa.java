package br.edu.ifpb.list.entidades;

public class Pessoa {
	
	private String nome;

	private String inscription;

	private String email;

	private boolean entregue;

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
	
	public String getEntrega(){
		if(this.isEntregue()){
			return "Foi entregue";
		}
		return "Não foi entregue";
	}
	
}
