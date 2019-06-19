# EletronicSystem - Caixa Eletrônico

Projeto com duas api's: 

  módulo - <b>eletronic-withdrawal-system</b></br>
  É o saque bancário, no qual você manda um Http PUT no seguinte endpoint para saque:
    - http://localhost:8080/terminal/withdraw/30       <- aonde o valor final é a quantia de saque desejada
 
 módulo - <b>bank-api</b></br>
 É o sistema de abastecimento do banco, conforme zera a quantia de notas no banco de dados, aonde os endpoints somente são requisitadas com um JWT válido ( no projeto eletronic-system voce o encontra em application.properties)
  -Caso você queria gerar um novo token, você envia um POST pro endpoint http://localhost:8090/login mandando no body um json com username e password (username: username, password:password), no header virá o token, você simplemente insere no application.properties na key <b>api_published_key=token</b>
 

Para que os projetos rodem corretamente, é preciso que cada um rode em uma porta diferente, que já está configurado o eletronic-withdrawal-system para a porta 8080 e o bank-api para a porta 8090

O banco de dados da aplicação que foi MySQL, segue as querys para o Start do projeto:

CREATE DATABASE ELETRONIC_SYSTEM;

após subir a primeira vez, o hibernate irá automaticamente criar as tabelas, depois desse procedimento, insira os seguintes dados:

insert into terminal values(1,"Terminal Rua Augusta", 1);

insert into drawer_terminal values(1,1,10,0);</br>
insert into drawer_terminal values(2,1,20,0);</br>
insert into drawer_terminal values(3,1,50,0);</br>
insert into drawer_terminal values(4,1,100,0);</br>
