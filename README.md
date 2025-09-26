# eda-hashmap-analysis

Esse repositório contém a experimentação feita sobre a comparação de diferentes implementações de HashMap, com intuito de analisar diversas métricas sobre a eficiência de cada um. Os HashMap analisados são:
* Endereçamento Aberto com Sondagem Linear
* Endereçamento Aberto com Sondagem Quadrática
* Encadeamento Fechado com LinkedList
* Encadeamento Fechado com ArrayList

## Introdução

Os HashMaps têm seu uso consolidado na prática computacional por se configurarem como ferramentas essenciais para a organização e manutenção de dados em um sistema. A busca por estruturas de dados eficientes permite sistemas mais rápidos, facilita a manipulação de informações sem perdas e maximiza o aproveitamento dos recursos. Esse tema torna-se relevante diante do crescimento constante no volume de dados, que exige soluções eficazes e escaláveis. Neste estudo, implementamos diferentes HashMaps, sob distintos fatores de carga, para avaliar a eficiência de seus métodos e identificar qual apresenta melhor desempenho na inserção e busca de itens.

## Objetivo

O objetivo deste projeto é analisar e comparar diferentes implementações de HashMaps, avaliando a eficiência de suas operações de inserção e busca sob distintos fatores de carga. O estudo busca identificar qual abordagem apresenta melhor desempenho na manipulação de uma quantidade randômica de dados, considerando diferentes cenários de colisão e níveis de ocupação das tabelas. Por fim, o objetivo é gerar uma base de análise que auxilie desenvolvedores e pesquisadores na escolha da implementação de HashMap mais adequada para aplicações que demandam alta performance, confiabilidade na recuperação de dados e consistência operacional, contribuindo para a otimização de sistemas que lidam com grandes volumes de informação.

## Como rodar o experimento?
```
./run-benchmark.sh
```

## Metodologia

Esse experimento será conduzido em cinco etapas principais, correspondentes ao ciclo completo de preparação, execução e análise. O objetivo é avaliar a performance de diferentes estratégias de resolução de colisões em operações de inserção e busca em HashMap. Para isso, serão utilizadas quatro implementações distintas: endereçamento aberto com sondagem linear, endereçamento aberto com sondagem quadrática, encadeamento fechado com LinkedList e encadeamento fechado com ArrayList. Cada etapa foi definida de forma a garantir comparabilidade entre as implementações, possibilitando identificar em quais cenários cada estrutura apresenta maior eficiência.

### Configuração de etapa do plano de experimento:

- Configuração das estruturas

	Inicialmente, serão obtidos HashMaps do repositório Open Source thealgorithms, que serão devidamente alterados, para garantir que cada versão do HashMap esteja disponível para análise e possível modificação.


- Ambientação e configuração

	Após a recuperação, será realizada a adaptação das implementações para permitir a variação controlada do fator de carga em cada estrutura, assegurando comparabilidade entre os diferentes métodos de tratamento de colisões.


- Geração das entradas

	As entradas utilizadas nos experimentos serão geradas de maneira randômica, assegurando diversidade de casos de colisão. Essa abordagem garante que cada implementação seja testada sob condições variadas, simulando cenários próximos a aplicações reais.


- Execução dos testes com benchmarks
	
	Para a execução dos testes será utilizado um ambiente controlado, no qual serão aplicados benchmarks em Java, utilizando o JMH (Java Microbenchmark Harness), uma ferramenta de análise de desempenho projetada para medir e analisar o desempenho de código Java. Os testes permitirão a coleta de métricas relacionadas ao custo de memória e à eficiência das operações put e get. Cada cenário será repetido múltiplas vezes para reduzir a influência de outliers.


- Coleta e análise dos resultados

	Após a execução, os dados serão processados para permitir a comparação entre os cenários. Os dados obtidos durante a execução dos benchmarks serão registrados em arquivos de resultados. Em seguida, serão gerados gráficos comparativos que representarão o desempenho de cada variação de HashMap em relação às métricas coletadas. Por fim, os resultados serão analisados de forma crítica, buscando identificar padrões de comportamento e compreender em quais cenários cada técnica apresenta maior eficiência.

## Resultados do Estudo de Desempenho (Benchmarks)

O objetivo desta análise é comparar a eficiência de quatro estruturas de dados em operações chave: recuperação de dados (getAll) e inserção de dados (putAll), sob diversas configurações de loadFactor. Os resultados são medidos em tempo de execução (ns/op).
<br> <br>

![Gráfico com todos os Hashs, fatores de carga e métodos](/images/allHashs.png)
1. **Métricas Chave e Dados Brutos**

>As estruturas de dados comparadas são: HashArrayList, HashLinkedList, LinearProbingHashMap, e QuadraticProbingHashMap.
A métrica principal é o Score (ns/op), onde valores menores indicam melhor desempenho. 
O loadFactor variou entre 0.5, 0.75, 0.9 e 1.5, dependendo do teste.
<br>


<table>
      <tr>
        <td><img src="images/linearProb_quadraticProb.png" alt="Descrição da imagem 1" width="100%"></td>
        <td><img src="images/arrayList_linkedList.png" alt="Descrição da imagem 2" width="100%"></td>
      </tr>
    </table>

2. **Análise de Desempenho por Operação**
    1. Desempenho de Recuperação 
    	> Esta seção avalia a rapidez com que a estrutura recupera todos os dados armazenados.

		|Estrutura|loadFactor|Score (ns/op)|
		|-----|------|-------|
		|LinearProbingHashMap|0.5|49.423.310,89|
		|LinearProbingHashMap|0.75|49.863.936,24|
		|LinearProbingHashMap|0.9|49.617.860,91|
		|QuadraticProbingHashMap|0.5|63.907.939,62|
		|QuadraticProbingHashMap|0.9|64.948.320,53|
		|HashArrayListBenchmark|0.5|107.964.315,5|
		|HashLinkedListBenchmark|1.5|136.800.778,8|

		#### Interpretação da Recuperação (getAll):
		O desempenho superior da LinearProbingHashMap na recuperação (≈49M ns/op) pode ser explicado pela localidade espacial da memória. A sondagem linear tende a explorar posições adjacentes no array, reduzindo falhas de cache e aproveitando melhor a hierarquia de memória. Esse padrão garante acesso mais previsível e eficiente, o que a coloca consistentemente como a opção mais rápida.
Já a QuadraticProbingHashMap, embora também baseada em endereçamento aberto, apresenta crescimento quadrático nos deslocamentos, o que aumenta a dispersão dos acessos e reduz parcialmente os ganhos de localidade. Isso explica seu desempenho inferior (≈64M ns/op), ainda que melhor que as estratégias de encadeamento.
Por outro lado, as implementações HashArrayList e HashLinkedList sofrem com a natureza das listas encadeadas: maior overhead de ponteiros, acessos não contíguos e falhas frequentes de cache. Esses fatores penalizam a velocidade de recuperação, sendo a HashLinkedList a mais prejudicada (≈136M ns/op). Esse resultado reforça que, em cenários orientados à leitura massiva, estruturas com sondagem aberta oferecem vantagens significativas sobre as encadeadas.

	2. Desempenho de Inserção    
		> Esta seção avalia o custo de tempo para inserir todos os dados na estrutura.
    
		|Estrutura|loadFactor|Score (ns/op)|
		|-----|-----|-----|
		|LinearProbingHashMap|0.75|72.506.491,74|
		|LinearProbingHashMap|0.9|73.516.415,75|
		|QuadraticProbingHashMap|0.9|81.498.851,21|	
		|QuadraticProbingHashMap|0.75|81.949.619,83|	
		|HashArrayListBenchmark|0.75|162.519.794,9|	
		|HashLinkedListBenchmark|0.75|167.618.200,4|
			
		#### Interpretação da Inserção (putAll):
		A LinearProbingHashMap novamente se destacou, registrando o melhor custo de inserção (≈72M ns/op com loadFactor 0.75). Esse desempenho decorre da sua simplicidade no tratamento de colisões: a busca sequencial pelo próximo slot vazio tende a ser curta em fatores de carga controlados, favorecendo a eficiência. Além disso, a baixa complexidade operacional reduz overhead no processo de inserção.
A QuadraticProbingHashMap, embora próxima em desempenho (≈81M ns/op), apresenta inserções um pouco mais custosas devido ao crescimento quadrático da sequência de sondagem. Embora essa estratégia reduza clusters primários, ela introduz maior dispersão e, portanto, pode exigir mais saltos até encontrar uma posição livre.
As estruturas baseadas em encadeamento (HashArrayList e HashLinkedList) foram as menos eficientes. O custo de criar e gerenciar objetos adicionais (nós e listas) e o overhead de ponteiros tornam o processo de inserção significativamente mais lento (≈160M ns/op). Esses resultados indicam que, em cenários de inserção intensiva, o encadeamento fechado é penalizado por sua complexidade estrutural, enquanto o endereçamento aberto mantém melhor escalabilidade.

	3. Impacto do Fator de Carga 
		> O impacto do Fator de Carga no desempenho varia conforme a implementação:
	
		O efeito do Fator de Carga mostrou-se altamente dependente da técnica de tratamento de colisões. Nas estratégias de sondagem aberta (linear e quadrática), o desempenho permaneceu estável mesmo com variações entre 0.5 e 0.9. Isso sugere que a densidade de ocupação da tabela não compromete substancialmente a eficiência até valores relativamente altos, reforçando a robustez dessas implementações em cenários de maior utilização da memória.
Já nas estratégias de encadeamento fechado, o impacto do fator de carga foi mais sensível. O aumento da densidade elevou significativamente os tempos de operação, como observado na recuperação do HashArrayList, que cresceu de ≈108M ns/op (loadFactor 0.5) para ≈133M ns/op (loadFactor 1.5). Esse comportamento decorre do crescimento médio das listas encadeadas, que aumenta linearmente o custo de busca e degrada a eficiência.
Esses resultados sugerem que, em ambientes com maior pressão de carga, o endereçamento aberto oferece maior previsibilidade e resiliência, enquanto o encadeamento fechado degrada rapidamente, tornando-se adequado apenas para cenários com baixa ocupação ou em que a inserção de chaves distribuídas seja garantida.

3. **Análise de Alocação de Memória (GC Metrics)**
    * As métricas de Garbage Collection (GC) indicam que a alocação de memória não foi um gargalo no desempenho medido:
        * O gc.count foi consistentemente 0.0 em todas as 23 medições de benchmark. Isso significa que não houve ciclos de coleta de lixo durante as operações cronometradas.
        * O gc.alloc.rate.norm (B/op), que mede a taxa normalizada de alocação de memória, permaneceu extremamente baixo e estável, variando apenas minimamente em torno de 0.0011 B/op em todos os testes.
<br>

## Ameaças à validade
Uma ameaça relevante à validade deste estudo está no comportamento observado entre os métodos de endereçamento aberto com sondagem linear e quadrática. Segundo a teoria, esperava-se que o método linear apresentasse pior desempenho à medida que o fator de carga aumentasse, enquanto o quadrático deveria amenizar os efeitos de colisões. No entanto, os resultados iniciais mostraram desempenhos muito próximos entre os dois métodos, com pouca influência do fator de carga.

### Experimento de Validação

Para investigar essa discrepância, conduzimos um mini-experimento adicional. Neste experimento, alteramos a função de hash para hashCode % 5, criando um cenário com alta taxa de colisões. O objetivo foi observar como cada método se comporta sob condições de estresse, forçando situações em que a sondagem quadrática deveria se destacar. As variáveis do experimento foram levemente modificadas em relação ao experimento original. Para evitar clustering em demasiado, foram alteradas 
* A entrada analisada agora contém 40.000 valores aleatórios que podem ser repetidos;
* Os parâmetros do benchmark foram todos alterados para 1, afim de acelerar o processo;


### Resultados da experimentação
Os resultados mostraram que, nesse cenário, a sondagem quadrática realmente diluiu melhor as colisões, apresentando tempos médios de inserção e acesso menores do que a linear. O gráfico a seguir demonstra os resultados do experimento. 


Entretanto, mesmo nesse contexto extremo, o fator de carga continuou a ter impacto mínimo nos tempos observados, confirmando que a ausência de efeito do fator de carga nos experimentos originais constitui a principal ameaça à validade do estudo.


## Considerações finais

## Referências
Foi utilizado o [repositório](https://github.com/TheAlgorithms/Java/tree/master/src/main/java/com/thealgorithms/datastructures/hashmap) do TheAlgorithms para usar as implementações de base dos HashMaps como base para nossas próprias implementações.
## Projeto de Experimento Inicial
[Projeto de Experimento](https://docs.google.com/document/d/1McAgqlTyzA-5fwfJNOPwc6OmLkhDPfAJob9Lm05y2U4/edit?tab=t.0) 
