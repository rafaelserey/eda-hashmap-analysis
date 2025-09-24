## eda-hashmap-analysis

Esse repositório contém a experimentação feita sobre a comparação de diferentes implementações de HashMap, com intuito de analisar diversas métricas sobre a eficiência de cada um. Os HashMap analisados são:
* Endereçamento Aberto com Sondagem Linear
* Endereçamento Aberto com Sondagem Quadrática
* Encadeamento Fechado com LinkedList
* Encadeamento Fechado com ArrayList

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


## Relatório do experimento
Esse [_Relatório_](https://docs.google.com/document/d/1McAgqlTyzA-5fwfJNOPwc6OmLkhDPfAJob9Lm05y2U4/edit?tab=t.0) contém o processo de análise e os resultados.
