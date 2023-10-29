package projeto;

import java.util.ArrayList;

public class Usuario {
	private String nome;
	private String email;
	private String senha;
	private ArrayList<Projeto> listaProjetos;
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		listaProjetos = new ArrayList<Projeto>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<Projeto> getListaProjetos() {
		return listaProjetos;
	}

	public void setListaProjetos(ArrayList<Projeto> listaProjetos) {
		this.listaProjetos = listaProjetos;
	}
	
	public boolean addProjeto(Projeto projeto) {
		return listaProjetos.add(projeto);
	}
	
	public boolean deletarProjeto(Projeto projeto) {
		return listaProjetos.remove(projeto);
	}
	
	//Criar o Buscar Projeto

	@Override
	public String toString() {
		return "Usuario" + nome + ", Email: " + email + ", Senha: " + senha + 
				", Projetos: " + listaProjetos.size();
	}
	
	
}
