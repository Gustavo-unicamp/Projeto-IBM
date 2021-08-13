//--------------------------------------------------------------
/*/
@author : Gustavo Parapugna Moraes
@since 12/08/2021
/*/
//--------------------------------------------------------------

package Emails;
import java.util.ArrayList;

public class GeradorEmails 
{
	/* Variaveis globais utilizadas que podem ser utilizadas em mais de uma funcao */
	private ArrayList<String> listaNomes = new ArrayList<String>();
	private ArrayList<String> listaEmails = new ArrayList<String>();
	private String provedor = "@company.com";
	private String primeiroNome = "";
	private String nomeMeio = "";
	private String ultimoNome = "";
	
	/*
	 * Funcao que centraliza as acoes e chama as funcoes necessarias para avaliar os nomes
	 * e criar os emails correspondentes.
	 */
	public void gerarEmails()
	{
		String nomeAlterado = "";
		boolean caracterInvalido = false;
		
		/* Adiciona os nomes no array listaNomes */
		adicionarNomes();
		
		/* Esse laco for passa pelo nomes que foram inseridos no array listaNomes, faz todas
		 *  as verificacoes necessarias e entao imprime a saida que corresponde a esse nome.
		 * 
		 * A saida pode ser:
		 * 	- Nome <email gerado>
		 *  - Uma mensagem informando que aquele nome possui algum caractere invalido que impossibilita
		 *    a criacao do email.
		 */
		for(String lista: listaNomes)
		{
			/* Verifica se o nome apresenta caractere invalido */
			caracterInvalido = verificarCaracteresInvalidos(lista);
			
			/* Caso o nome nao tenha caractere invalido faz-se a separacao do nome e gera-se o email */
			if(caracterInvalido == false)
			{
				/* Verifica se o nome apresenta acentuacao ou hifen */
				nomeAlterado = alterarNome(lista);
				
				/* Se o nome foi modificado altera-se a variavel lista para que o email seja
				 * criado corretamente.
				 */
				if(!nomeAlterado.equals(lista))
					lista = nomeAlterado;
				
				/* Separa o nome e salva nas variaveis globais para posteriormente criar o email. */
				separarNome(lista);
			
				/* Chama a funcao criarEmail que retorna o email que foi gerado a partir do nome */
				System.out.println(lista + " <" + criarEmail() + "> ");
			}
			else
				System.out.println("O nome " +lista+ " possui um caracter inválido e por isso não é possível gerar um email.");		
		}
	}
	
	/*
	 * Adiciona os nomes manualmente, mas poderia ser alterada para adicionar os nomes a partir de 
	 * uma outra lista ou entao a partir de uma entrada do usuario. Foi feito dessa forma porque 
	 * os nomes ja foram disponibilizados no enunciado.
	 * 
	 * Obs: Foram acrescentados alguns nomes para testar as funcoes que foram desenvolvidas.
	 */
	private void adicionarNomes()
	{
		listaNomes.add("John Doe");
		listaNomes.add("Peter Parker");
		listaNomes.add("Mary Jane Watson-Parker");
		listaNomes.add("James Doe");
		listaNomes.add("John Elvis Doe");
		listaNomes.add("Jane Doe");
		listaNomes.add("Penny Parker");
		listaNomes.add("José Silvério");
		listaNomes.add("Marcelo Oliveira@Sales");
		listaNomes.add("João&Paulo Simas");
		listaNomes.add("Palo!ma Gonçalves");
		listaNomes.add("Antôni# Rocha");
		listaNomes.add("Cam/ila Pereira");
	}
	
	/*
	 * Recebe o nome ja no formato correto e separa a partir dos espacos em:
	 * 	- Primeiro nome: Ate o primeiro espaco (Primeira posicao salva no vetor nomeSeparado).
	 *  - Nome do meio: Entre o primeiro e segundo espacos (Para nomes com mais de um nome do meio foi
	 *                  considerado somente o primeiro nome do meio).
	 *  - Ultimo nome: Ultima posicao salva no vetor nomeSeparado.
	 */
	private void separarNome(String nome)
	{
		String[] nomeSeparado = nome.split(" ");
		
		primeiroNome = nomeSeparado[0];
		
		/* A logica utilizada considera que se o vetor possui somente duas posicoes, entao ele
		 * nao possui nome do meio, caso contrario, atribui-se a segunda posicao ao nome do meio
		 * e a ultima posicao ao ultimo nome.
		 */
		if(nomeSeparado.length == 2)
		{
			nomeMeio = "";
			ultimoNome = nomeSeparado[1];
		}
		else
		{
			nomeMeio = nomeSeparado[1];
			ultimoNome = nomeSeparado[nomeSeparado.length - 1];
		}
	}
	
	/*
	 * Essa funcao recebe o nome que esta sendo verificado no momento e avalia se algum desses caracteres
	 * esta presente no nome, caso sim, retorna "true" para que a verificacao do nome seja interrompida 
	 * passando para o nome seguinte da lista.
	 */
	private boolean verificarCaracteresInvalidos(String nome)
	{
		if(nome.contains("@") || nome.contains("&") || nome.contains("/") || nome.contains("#") || nome.contains("!"))
			return true;
			
		return false;
	}
	
	/*
	 * A funcao alterarNome recebe o nome e verifica se o mesmo apresenta alguma dessas acentuacoes 
	 * ou hifen, e faz a alteracao para que o nome fique no formato desejado para a criacao do email.
	 * 
	 * Caso o nome tenha:
	 * 	- Hifen: Junta-se a parte anterior e posterior ao hifen. EX: Watson-Parker ==> WatsonParker
	 *  - Acento: Passa por cada vetor de vogal e ao encontrar a vogal que apresenta acentuacao troca-se
	 *            para a mesma vogal sem acento.
	 */
	private String alterarNome(String nome)
	{
		char[] listaAs = {'á','ã','à','â'};
		char[] listaEs = {'é','ê','è'};
		char[] listaIs = {'í','î','ì'};
		char[] listaOs = {'ó','õ','ò','ô'};
		char[] listaUs = {'ú','ù','û'};
		
		String antes = "";
		String depois = "";
		String[] alterarNome = {""};
		
		if(nome.contains("-"))
		{
			alterarNome = nome.split("-");
			
			nome = alterarNome[0];
			nome += alterarNome[1];

			return nome;
		}
		
		for(int i = 0; i < nome.length(); i++)
		{
			for(char letra: listaAs)
			{
				if(nome.charAt(i) == letra)
				{
					antes = nome.substring(0,i);
					depois = nome.substring(i+1,nome.length());
					
					nome = antes;
					nome += "a";
					nome += depois;
				}
			}
		}
		
		for(int i = 0; i < nome.length(); i++)
		{
			for(char letra: listaEs)
			{
				if(nome.charAt(i) == letra)
				{
					antes = nome.substring(0,i);
					depois = nome.substring(i+1,nome.length());
					
					nome = antes;
					nome += "e";
					nome += depois;
				}
			}
		}
		
		for(int i = 0; i < nome.length(); i++)
		{
			for(char letra: listaIs)
			{
				if(nome.charAt(i) == letra)
				{
					antes = nome.substring(0,i);
					depois = nome.substring(i+1,nome.length());
					
					nome = antes;
					nome += "i";
					nome += depois;
				}
			}
		}
		
		for(int i = 0; i < nome.length(); i++)
		{
			for(char letra: listaOs)
			{
				if(nome.charAt(i) == letra)
				{
					antes = nome.substring(0,i);
					depois = nome.substring(i+1,nome.length());
					
					nome = antes;
					nome += "o";
					nome += depois;
				}
			}
		}
		
		for(int i = 0; i < nome.length(); i++)
		{
			for(char letra: listaUs)
			{
				if(nome.charAt(i) == letra)
				{
					antes = nome.substring(0,i);
					depois = nome.substring(i+1,nome.length());
					
					nome = antes;
					nome += "u";
					nome += depois;
				}
			}
		}
		
		return nome;
	}
	
	/*
	 * Essa funcao acessa as variaveis globais que foram definidas e concatena na variavel email 
	 * para montar da forma correta. 
	 * Ela tambem verifica se ja existe um email igual e faz a modificacao para distinguir os emails
	 */
	private String criarEmail()
	{
		String email = "";
		int contador = 1;
		String antes = "";
		
		email += ultimoNome.toLowerCase();
		email += ".";
		email += primeiroNome.substring(0, 1).toLowerCase();
		
		/* Caso nao tenha nome do meio coloca-se o provedor diretamente, senao, acrescenta o nome 
		 * do meio antes do provedor.
		 */
		if(nomeMeio.equals(""))
			email += provedor;
		else
		{
			email += ".";
			email+= nomeMeio.substring(0, 1).toLowerCase();
			email += provedor;
		}

		/* Chama a funcao que verifica o email criado ate que nao se encontre nenhum email igual */
		while(verificarExistencia(email))
		{
			/* Logica para alterar o numero que distingue os emails.
			 * A partir do valor atual do contador retira a parte que esta antes do "contador@" 
			 * para depois modificar o final do email.
			 */
			if(contador == 1)
				antes = email.substring(0,email.length() - 12);
			else 
				antes = email.substring(0,email.length() - (12 + Integer.toString(contador).length()) );

			/* Concatena o email novamente com a parte anterior ao "contador@", acrescenta 1 no
			 * contador e concatena "contador+provedor" para finalizar a alteracao do email.
			 */
			email = antes;
			contador++; 
			email += contador+provedor;
		}
		
		/* Adiciona no array listaEmails para posterior verificacao dos emails ja gerados. */
		listaEmails.add(email);
		
		return email;
	}

	/*
	 * Funcao que percorre o array e verifica se o email recebido ja foi gerado.
	 */
	private boolean verificarExistencia(String email)
	{
		for(String lista: listaEmails)
		{
			if(email.equals(lista))
				return true;
		}
		
		return false;
	}
}
