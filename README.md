# Projeto-IBM

Para o correto funcionamento do código deve-se, primeiramente:
1) Instalar o software Eclipse IDE for Java Developers (o código foi desenvolvido na versão 2021-03).
2) Baixar o projeto e descompactá-lo.
3) Importar o projeto para dentro do ambiente Eclipse, para isso:
     - Abra o software e na primeira janela escolha o local onde deseja salvar futuros projetos que poderão ser criados(chamado de workspace). Para executar esse projeto pode-se          escolher qualquer local.
     - Em seguida, com o software aberto, vá em File - Import - Existing Projects into Workspace.
     - Na sequência, clique em Browse e selecione a pasta em que se encontra o arquivo baixado.
     - Feito isso, o projeto será adicionado na seção Package Explorer e está pronto para ser executado.

Para executar o código o projeto requer que os nomes que irão receber um email sejam adicionados. Então, os passos para a execução são:
1) Acesse a função adicionarNomes que se encontra na classe GeradorEmails, dentro do pacote Emails.
2) Verifique os nomes já inseridos e os altere ou exclua-os conforme sua necessidade.
3) Caso queira inserir mais nomes utilize o seguinte comando: listaNomes.add("Nome desejado");
     - listaNomes é o vetor de nomes responsável por armazenar os nomes informados.
     - add é o comando que insere o nome na última posição do vetor e automaticamente adiciona mais uma posição.
4) Execute o código através do botão run.

A saída do código desenvolvido apresenta o seguinte formato: Nome_informado <email_gerado>. 

Se o nome informado apresentar um desses caracteres - "@,&,/,#,!" - ele é considerado inválido e portanto não é gerado um email.
  - Nesse caso é informada a seguinte mensagem: O nome Nome_informado possui um caracter inválido e por isso não é possível gerar um email.
  
Demais ações são desnecessárias e qualquer outra mensagem de saída desse ser considerada como um erro ou exceção na execução do código.


Autor: Gustavo Parapugna Moraes
