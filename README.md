# 7 Days of Code: Java com Spring Boot! 🚀

## 💻 Dia 07: Geração de Relatórios de Hábitos com Gráficos
### 🚀 O Desafio de Hoje

No nosso último dia de desafio fechamos nosso projeto criando relatórios para exibir informações sobre os hábitos do usuário. Usamos gráficos para mostrar dados como Média de Tempo Gasto em Exercícios, Média de Calorias Consumidas por Refeição e Média de Horas Dormidas.

### 🛠️ O que fizemos?
- Adicionamos uma nova seção na página `crud.html` para exibir relatórios, além de usarmos o Chart.js para criar gráficos que apresentam dados de `Exercicio`, `Refeicao` e `Sono` de maneira visual e interativa.

- Criamos um `RelatorioService` que calcula as médias necessárias para os relatórios, consultando os repositórios e calculando médias, como tempo gasto em exercícios, calorias consumidas e horas dormidas.

- Deixamos nos repositórios, com a anotação @Query, as consultas que calculam as médias diretamente no banco. O `RelatorioService` calcula em Java, com a API de streams, ignorando os registros gravados sem valor; as consultas @Query ficam como ponto de partida para quem quiser fazer a conta no banco. 

