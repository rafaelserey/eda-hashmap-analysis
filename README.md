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

No estudo de Estruturas de Dados, compreender o impacto das diferentes estratégias de resolução de colisões é essencial para projetar algoritmos que conciliam rapidez e uso eficiente de memória. Nesse contexto, estruturas amplamente utilizadas, como HashMap, podem apresentar desempenhos bastante distintos dependendo da forma como as colisões são tratadas. Estratégias como o encadeamento fechado, que pode ser otimizado com diferentes variações de listas, e o endereçamento aberto, que admite distintas técnicas de sondagem, influenciam diretamente no custo das operações fundamentais, como a inserção de elementos. Assim, este projeto tem como motivação analisar experimentalmente como essas variações se comportam sob diferentes condições de carga e tipos de entrada, de modo a identificar quais configurações se mostram mais adequadas em cada cenário. A partir dessa avaliação, pretende-se evidenciar o papel das escolhas de implementação no desempenho prático de estruturas de dados e oferecer subsídios para decisões mais embasadas em aplicações reais.


## Relatório do experimento
Esse [_Relatório_](https://docs.google.com/document/d/1McAgqlTyzA-5fwfJNOPwc6OmLkhDPfAJob9Lm05y2U4/edit?tab=t.0) contém o processo de análise e os resultados.
