package main;

import java.util.HashMap;
import java.util.Map;

import java.util.*;
import projeto.*;
import view.*;
import dados.*;

public class Main {
	
	private static Dados d = new Dados();
	private static Scanner in = new Scanner(System.in);
	private static Map<Repositorio, Usuario> mapaRepositoriosUsuarios = new HashMap<>();
	
	public static void main(String[] args) {
		int op = -1;
		int opSecundaria = -1;
		int aux;
		d.preencherDados();
		
		while(op != 0) {
			System.out.print(imprimirMenu());
			op = in.nextInt();
			switch (op) {
				case 0:
					System.out.println("Obrigado por utilizar o GitHub. Ate logo!");
					break;
				case 1:
					do {
						System.out.print(imprimirMenuUsuario());
						opSecundaria = in.nextInt();
						switch (opSecundaria) {
							case 0:
								//Voltar para o Menu
							break;
							case 1:
								cadastrarUsuario();
								System.out.println("\n");
								break;
							case 2:
								removerUsuario();
								System.out.println("\n");
								break;
							case 3:
								System.out.println("Escolha um dos Usuarios a seguir para editar as informacoes:\n");
								listarUsuarios();
								aux = in.nextInt();
								System.out.println("\n");
								Usuario u = lerDadosUsuario();
								editar(aux, u);
								break;
							case 4:
								listarUsuarios();
								System.out.println("Deseja olhar o Repositorio de algum Usuario? \n");
								do {
									System.out.print(imprimirMenuUsuarioRep());
									opSecundaria = in.nextInt();
									switch (opSecundaria) {
									case 0:
										//Voltar para o Menu Usuario
									break;
									case 1:
										listarRepositoriosUsuario();
										do {
											System.out.println("\n");
											System.out.print(imprimirMenuRepositorio());
											opSecundaria = in.nextInt();
											switch (opSecundaria) {
												case 0:
													System.out.println("\n");
													break;
												case 1:
													System.out.println("\n");
													cadastrarRepositorio();
													break;
												case 2:
													System.out.println("\n");
													removerRepositorio();
													break;
												case 3:
													System.out.println("\nEscolha um dos Repositorios a seguir para editar as informações:\n");
													listarRepositorios();
													aux = in.nextInt();
													System.out.println("\n");
													Repositorio r = lerDadosRepositorio();
													editar(aux, r);
													break;
												case 4:
													System.out.println("\n");
													listarRepositorios();
													break;
											}
											} while (opSecundaria != 0);
									break;
									}
								}while (opSecundaria != 0);
								/**/
							default:
								System.out.println("\nOpção Invalida!\n");
								break;
						}
					} while (opSecundaria != 0);
				break;
				case 2:
					do {
						System.out.println("\n");
						System.out.print(imprimirMenuRepositorio());
						opSecundaria = in.nextInt();
						switch (opSecundaria) {
							case 0:
								System.out.println("\n");
								break;
							case 1:
								System.out.println("\n");
								cadastrarRepositorio();
								break;
							case 2:
								System.out.println("\n");
								removerRepositorio();
								break;
							case 3:
								System.out.println("\nEscolha um dos Repositorios a seguir para editar as informações:\n");
								listarRepositorios();
								aux = in.nextInt();
								System.out.println("\n");
								Repositorio r = lerDadosRepositorio();
								editar(aux, r);
								break;
							case 4:
								System.out.println("\n");
								listarRepositorios();
								break;
							case 5:
								System.out.println("\n");
								System.out.println("Insira o Nome para buscar: ");
								in.nextLine(); // Limpa o buffer
								String nome = in.nextLine();
								buscarRepositorio(nome);
								break;
							case 6:
								System.out.println("\n");
								cadastrarCommit();
								break;
							case 7:
								System.out.println("\n");
								removerCommit();
								break;
							case 8:
								System.out.println("\nEscolha um dos Commits a seguir para editar as informações:\n");
								listarCommits();
								aux = in.nextInt();
								System.out.println("\n");
								Commit c = lerDadosCommit();
								editar(aux, c);
								break;
							case 9:
								System.out.println("\n");
								listarCommits();
								break;
							case 10:
								System.out.println("\n");
								cadastrarIssue();
								break;
							case 11:
								System.out.println("\n");
								removerIssue();
								break;
							case 12:
								System.out.println("\nEscolha um dos Issues a seguir para editar as informações:\n");
								listarIssues();
								aux = in.nextInt();
								System.out.println("\n");
								Issue iss = lerDadosIssue();
								editar(aux, iss);
								break;
							case 13:
								System.out.println("\n");
								listarIssues();
								break;
							default:
								System.out.println("\nOpção Invalida!\n");
								break;
						}
					} while (opSecundaria != 0);
					break;
				default:
					System.out.println("\nOpção Invalida!\n");
					break;
			}
		}
		in.close();
	}
	
	/////////////
	//Menus
	/////////////
	public static String imprimirMenu() {
		String saida = new String("Bem vindo ao Menu GitHub! \nEscolha uma das opçoes a seguir:\n");
		saida = saida + "00 - Sair da aplicação\n";
		saida = saida + "01 - Usuarios\n";
		saida = saida + "02 - Repositorios\n";
		saida = saida + "03 - Projetos\n";
		return saida;
	}

	public static String imprimirMenuUsuario() {
		String saida = new String("Escolha uma das opçoes a seguir:\n");
		saida = saida + "00 - Voltar para o menu\n";
		saida = saida + "01 - Cadastrar novo Usuario\n";
		saida = saida + "02 - Remover usuario existente\n";
		saida = saida + "03 - Editar usuario existente\n";
		saida = saida + "04 - Listar usuarios\n";
		return saida;
	}
	
	public static String imprimirMenuUsuarioRep() {
		String saida = new String("Escolha uma das opçoes a seguir:\n");
		saida = saida + "00 - Nao, voltar para o menu\n";
		saida = saida + "01 - Sim\n";
		return saida;
	}

	public static String imprimirMenuRepositorio() {
		String saida = new String("Escolha uma das opçoes a seguir:\n");
		saida = saida + "00 - Voltar para o menu\n";
		saida = saida + "01 - Cadastrar novo Repositorio\n";
		saida = saida + "02 - Remover repositorio existente\n";
		saida = saida + "03 - Editar repositorio existente\n";
		saida = saida + "04 - Listar repositorios\n";
		saida = saida + "05 - Buscar Repositorio\n";
        saida = saida + "06 - Cadastrar novo Commit\n";
        saida = saida + "07 - Remover commit existente\n";
        saida = saida + "08 - Editar commit existente\n";
        saida = saida + "09 - Listar commits\n";
        saida = saida + "10 - Cadastrar novo Issue\n";
        saida = saida + "11 - Remover projeto issue\n";
        saida = saida + "12 - Editar projeto issue\n";
        saida = saida + "13 - Listar issues\n";
		return saida;
	}

	public static String imprimirMenuProjeto() {
		String saida = new String("Escolha uma das opçoes a seguir:\n");
		saida = saida + "00 - Voltar para o menu\n";
		saida = saida + "01 - Cadastrar novo Projeto\n";
		saida = saida + "02 - Remover projeto existente\n";
		saida = saida + "03 - Editar projeto existente\n";
		saida = saida + "04 - Listar projetos\n";
		return saida;
	}
	
	/////////////
	//Usuario
	/////////////
	
	public static boolean cadastrarUsuario() {
		Usuario u = lerDadosUsuario();
		if(d.getnUsuarios() < 100) {
			d.setUsuario(d.getnUsuarios(), u);
			d.setnUsuarios(d.getnUsuarios()+1);
			System.out.println("Usuario cadastrado com sucesso!\n");
			return true;
		} else {
			System.out.println("Não foi possivel cadastrar o usuario!\n");
			return false;
		}
	}
	
	public static Usuario lerDadosUsuario() {
		String nome;
		String email; 
		String senha;
		in.nextLine(); //esvazia dados do teclado
		System.out.println("Digite o nome do Usuario: ");
		nome = in.nextLine();
		System.out.println("Digite o email do Usuario:");
		email = in.nextLine();
		System.out.println("Digite a senha do Usuario");
		senha = in.nextLine();
		Usuario u = new Usuario(nome, email, senha);
		return u;	
	}
	
	public static void removerUsuario() {
		System.out.println("Escolha um dos usuarios a seguir para ser removido:\n");
		listarUsuarios();
		int i = in.nextInt();
		if(i < d.getnUsuarios() && i > 0) {
			swapListaUsuarios(i);
			d.setUsuario(d.getnUsuarios(), null);
			d.setnUsuarios(d.getnUsuarios() - 1);
			System.out.println("Usuario removido com sucesso");
		} else {
			System.out.println("Voce escolheu um numero invalido!");
		}
		
	}
	
	public static void swapListaUsuarios(int u) {
		for(int i = u; i < d.getnUsuarios() - 1; i++) 
			d.setUsuario(i, d.getUsuario(i+1));
	}
	
	public static void editar(int i, Usuario u) {
		if(i < d.getnUsuarios() && i >= 0) {
			d.setUsuario(i, u);
			System.out.println("Dados editados com sucesso");
		} else {
			System.out.println("Voce escolheu um numero invalido!");
		}
	}
	
	public static void listarUsuarios() {
		in.nextLine(); //esvazia dados do teclado
		for(int i = 0; i < d.getnUsuarios(); i++) 
			System.out.println(i + " -> " + d.getUsuarios()[i].toString());
		/* Descomente a linha a seguir para ver a listagem dos alunos em interface gráfica
		 * new TelaListagem(d.getNomeAlunos());
		 */
	}
	
	private static void buscarRepositorio(String nome) {
        //Busca Dentro do Usuário

        /*for(int i = 0; i < d.getnUsuarios(); i++) {
            if(d.getUsuarios()[i].buscaRepositorio(nome)!= null){
                System.out.println(d.getUsuarios()[i].buscaRepositorio(nome).toString());
            }
        }
        */

        //Dentro do Dados
        for(int i = 0; i < d.getnRepositorios(); i++) {
            if(d.getRepositorios()[i].getNome().compareToIgnoreCase(nome) == 0) {
                System.out.println(d.getRepositorios()[i].toString());
            }
        }

    }
	
	public static void listarRepositoriosUsuario() {
	    listarUsuarios();
	    System.out.println("Escolha um dos usuários para listar os repositórios:\n");
	    int usuarioSelecionado = in.nextInt();
	    in.nextLine(); // Limpa o buffer
	    if (usuarioSelecionado >= 0 && usuarioSelecionado < d.getnUsuarios()) {
	        Usuario usuario = d.getUsuarios()[usuarioSelecionado];
	        System.out.println("Repositórios do usuário " + usuario.getNome() + ":");
	        for (Map.Entry<Repositorio, Usuario> entry : mapaRepositoriosUsuarios.entrySet()) {
	            if (entry.getValue().equals(usuario)) {
	                System.out.println(entry.getKey().toString());
	            }else {
	            	System.out.println("NAda\n");
	            }
	        }
	    } else {
	        System.out.println("Opção inválida!");
	    }
	}
	
	/////////////
	//Repositorio
	/////////////
	
	/*public static boolean cadastrarRepositorio() {
		Repositorio r = lerDadosRepositorio();
		if(d.getnRepositorios() < 100) {
			d.setRepositorio(d.getnRepositorios(), r);
			d.setnRepositorios(d.getnRepositorios()+1);
			System.out.println("Repositorio cadastrado com sucesso!\n");
			return true;
		} else {
			System.out.println("Não foi possivel cadastrar o Repositorio!\n");
			return false;
		}
	}*/
	
	private static Usuario escolherUsuario() {
	    System.out.println("Escolha um usuário:");

	    // Listar usuários disponíveis
	    listarUsuarios();

	    // Solicitar a escolha do usuário
	    int escolha = in.nextInt();
	    in.nextLine(); // Limpar o buffer

	    // Verificar se a escolha é válida
	    if (escolha >= 0 && escolha < d.getnUsuarios()) {
	        return d.getUsuarios()[escolha];
	    } else {
	        System.out.println("Escolha inválida. Retornando null.");
	        return null;
	    }
	}
	
	public static boolean cadastrarRepositorio() {
	    Repositorio r = lerDadosRepositorio();
	    Usuario u = escolherUsuario(); // Adapte conforme necessário
	    if (d.getnRepositorios() < 100) {
	        d.setRepositorio(d.getnRepositorios(), r);
	        d.setnRepositorios(d.getnRepositorios() + 1);
	        mapaRepositoriosUsuarios.put(r, u); // Mapeia o repositório para o usuário
	        System.out.println("Repositorio cadastrado com sucesso!\n");
	        return true;
	    } else {
	        System.out.println("Não foi possivel cadastrar o Repositorio!\n");
	        return false;
	    }
	}
	
	public static Repositorio lerDadosRepositorio() {
		String nome;
		String dtCriacao;
		in.nextLine(); //esvazia dados do teclado
		System.out.println("Digite o nome do Repositorio: ");
		nome = in.nextLine();
		System.out.println("Digite a data de criação do Repositorio: ");
		dtCriacao = in.nextLine();
		Repositorio r = new Repositorio(nome, dtCriacao);
		return r;	
	}
	
	public static void removerRepositorio() {
		System.out.println("Escolha um dos repositorios a seguir para ser removido:\n");
		listarRepositorios();
		int i = in.nextInt();
		if(i < d.getnRepositorios() && i > 0) {
			swapListaRepositorios(i);
			d.setRepositorio(d.getnRepositorios(), null);
			d.setnRepositorios(d.getnRepositorios() - 1);
			System.out.println("Repositorio removido com sucesso");
		} else {
			System.out.println("Voce escolheu um numero invalido!");
		}
		
	}
	
	public static void swapListaRepositorios(int r) {
		for(int i = r; i < d.getnRepositorios() - 1; i++) 
			d.setRepositorio(i, d.getRepositorio(i+1));
	}
	
	public static void editar(int i, Repositorio r) {
		if(i < d.getnRepositorios() && i >= 0) {
			d.setRepositorio(i, r);
			System.out.println("Dados editados com sucesso");
		} else {
			System.out.println("Voce escolheu um numero invalido!");
		}
	}
	
	public static void listarRepositorios() {
		in.nextLine(); //esvazia dados do teclado
		for(int i = 0; i < d.getnRepositorios(); i++) 
			System.out.println(i + " -> " + d.getRepositorios()[i].toString());
		/* Descomente a linha a seguir para ver a listagem dos alunos em interface gráfica
		 * new TelaListagem(d.getNomeAlunos());
		 */
	}
	
	/////////////
	//Commit
	/////////////
	
	public static boolean cadastrarCommit() {
		Commit c = lerDadosCommit();
		if(d.getnCommits() < 100) {
			d.setCommit(d.getnCommits(), c);
			d.setnCommits(d.getnCommits()+1);
			System.out.println("Commit cadastrado com sucesso!\n");
			return true;
		} else {
			System.out.println("Não foi possivel cadastrar a Commit!\n");
			return false;
		}
	}
	
	public static Commit lerDadosCommit() {
		String nome;
		int id;
		String descricao;
		String dtEnvio;
		
		in.nextLine(); //Limpa o buffer
		
		System.out.println("Digite o nome do Commit: ");
		nome = in.nextLine();
		
		while (true) {
            System.out.println("Digite o ID: ");
            if (in.hasNextInt()) {
                id = in.nextInt();
                in.nextLine(); // Limpa o buffer
                break; // Sai do loop se um número inteiro válido for fornecido
            } else {
                System.out.println("Por favor, digite um valor numérico para o ID.");
                in.nextLine(); // Limpa o buffer
            }
        }
		
		System.out.println("Digite a Descrição: ");
		descricao = in.nextLine();
		
		System.out.println("Digite a data de envio do Commit: ");
		dtEnvio = in.nextLine();
		
		Commit c = new Commit(nome, id, descricao, dtEnvio);
		return c;	
	}
	
	public static void removerCommit() {
		System.out.println("Escolha um dos commits a seguir para ser removido:\n");
		listarCommits();
		int i = in.nextInt();
		if(i < d.getnCommits() && i > 0) {
			swapListaCommits(i);
			d.setCommit(d.getnCommits(), null);
			d.setnCommits(d.getnCommits() - 1);
			System.out.println("Commit removido com sucesso");
		} else {
			System.out.println("Voce escolheu um numero invalido!");
		}
		
	}
	
	public static void swapListaCommits(int c) {
		for(int i = c; i < d.getnCommits() - 1; i++) 
			d.setCommit(i, d.getCommit(i+1));
	}
	
	public static void editar(int i, Commit c) {
		if(i < d.getnCommits() && i >= 0) {
			d.setCommit(i, c);
			System.out.println("Dados editados com sucesso");
		} else {
			System.out.println("Voce escolheu um numero invalido!");
		}
	}
	
	public static void listarCommits() {
		in.nextLine(); //esvazia dados do teclado
		for(int i = 0; i < d.getnCommits(); i++) 
			System.out.println(i + " -> " + d.getCommits()[i].toString());
		/* Descomente a linha a seguir para ver a listagem dos alunos em interface gráfica
		 * new TelaListagem(d.getNomeAlunos());
		 */
	}
	
	/////////////
	//Issue
	/////////////
	
	public static boolean cadastrarIssue() {
		Issue i = lerDadosIssue();
		if(d.getnIssues() < 100) {
			d.setIssue(d.getnIssues(), i);
			d.setnIssues(d.getnIssues()+1);
			System.out.println("Issue cadastrado com sucesso!\n");
			return true;
		} else {
			System.out.println("Não foi possivel cadastrar a Issue!\n");
			return false;
		}
	}
	
	public static Issue lerDadosIssue() {
		String nome;
		int id;
		String descricao;
		String status;
		in.nextLine();
		System.out.println("Digite o nome do Issue: ");
		nome = in.nextLine();
		System.out.println("Digite o ID: ");
		id = in.nextInt();
		in.nextLine();
		System.out.println("Digite a Descrição: ");
		descricao = in.nextLine();
		System.out.println("Digite o status do Issue: ");
		status = in.nextLine();
		Issue iss = new Issue(nome, id, descricao, status);
		return iss;	
	}
	
	public static void removerIssue() {
		System.out.println("Escolha um dos issues a seguir para ser removido:\n");
		listarIssues();
		int i = in.nextInt();
		if(i < d.getnIssues() && i > 0) {
			swapListaIssues(i);
			d.setIssue(d.getnIssues(), null);
			d.setnIssues(d.getnIssues() - 1);
			System.out.println("Issue removido com sucesso");
		} else {
			System.out.println("Voce escolheu um numero invalido!");
		}
		
	}
	
	public static void swapListaIssues(int c) {
		for(int i = c; i < d.getnIssues() - 1; i++) 
			d.setIssue(i, d.getIssue(i+1));
	}
	
	public static void editar(int i, Issue iss) {
		if(i < d.getnIssues() && i >= 0) {
			d.setIssue(i, iss);
			System.out.println("Dados editados com sucesso");
		} else {
			System.out.println("Voce escolheu um numero invalido!");
		}
	}
	
	public static void listarIssues() {
		in.nextLine(); //esvazia dados do teclado
		for(int i = 0; i < d.getnIssues(); i++) 
			System.out.println(i + " -> " + d.getIssues()[i].toString());
		/* Descomente a linha a seguir para ver a listagem dos alunos em interface gráfica
		 * new TelaListagem(d.getNomeAlunos());
		 */
	}
	
}
