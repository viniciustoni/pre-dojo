PROBLEMA
========
Dado o seguinte log de um jogo de tiro em primeira pessoa:

23/04/2013 15:34:22 - New match 11348965 has started  
23/04/2013 15:36:04 - Roman killed Nick using M16  
23/04/2013 15:36:33 - &lt;WORLD&gt; killed Nick by DROWN  
23/04/2013 15:39:22 - Match 11348965 has ended  

Resultado esperado
------------------
* A partir de um input de um arquivo de log do formato acima, montar o ranking de cada partida, com a quantidade assassinatos e a quantidade de mortes de cada jogador;

Observações
------------
* Assassinatos realizados pelo &lt;WORLD&gt; devem ser desconsiderados, no entanto, as mortes causadas pelo &lt;WORLD&gt; devem ser consideradas para o jogador que foi morto.


Bônus
-----
Não obrigatório. Faça apenas caso se identifique com o problema ou se achar que há algo interessante a ser mostrado na solução
* Descobrir a arma preferida (a que mais matou) do vencedor;
* Identificar a maior sequência de assassinatos efetuadas por um jogador (streak) sem morrer, dentro da partida;
* Jogadores que vencerem uma partida sem morrerem devem ganhar um "award";
* Jogadores que matarem 5 vezes em 1 minuto devem ganhar um "award".


SOLUÇÃO
=======
Para a execução:
----------------

* Baixar o projeto
* Executar o seguinte comando: mvn clean package
* Executar o seguinte comando: mvn exec:java
* Clicar no Botao de "Processar Arquivo"
* Selecionar algum arquivo .txt ou .log
* Clicar em Abrir

Considerações
-------------

* A arquitetura está muito simples, onde tem uma tela em Swing que abre um arquivo e mostra em um TextArea o resultado do ranking;

* Por efeitos de testes pessoais, eu resolvi criar toda a Logica nas classes de Entity, costumeiramente criamos as classes de Entity/TOs com somente propriedades e seus métodos de GET/SET sem nenhuma lógica(padrão muito comum no mercado), porém gostaria de experimentar alguns novos padrões onde as entitys recebem métodos contendo lógicas de negócio porém somente respectivas a eles, particularmente achei interessante, porém somente para aplicações pequenas e bem especificas como essa, em ambiente corporativo ou com aplicações com lógicas mais pesadas, o melhor ainda é o formato tradicional, onde há uma ou mais classes de negócio.

