### Fatec - Centro Paula Souza
##### Curso - Desenvolvimento de Software Multiplataforma
##### Estudo de Caso – Quitanda Frescor
> No contexto do projeto interdisciplinar o estudo de caso tem como objetivo desenvolver e implantar o site do Hortifruti quitanda frescor . O projeto tem como objetivo facilitar a análise das vendas para apoio a tomada de decisão. É possivel realizar encomendas e fazer controle de estoque
Time de desenvolvimento
- Bruno Bertholini
-Gabriel Maia
- Gustavo Vito
##### Instalação
O projeto esta em desenvolvimento utilizando STS4 e a linguagem Java 17.
- No Github selecione a opção clone e copie o endereço do repositório.
- No STS4 abra a perspectiva Git opção clone.
- Ao terminar o download import o projeto para o workspace
- A classe ProdutoAppplication inicializa a execução do projeto
##### Processo de Desenvolvimento de Software - PDS
> O PDS segue uma abordagem interativa incremental adaptada do Scrum. A definição de pronto estabelece os mecanismos para
> controle de qualidade da aplicação.
##### Análise de risco
> O resultado da análise conclui que será possível implementar o projeto no semestre com grupos de alunos atuando como programadores. O maior risco identificado é a falta de tempo para reunião do time de desenvolvimento e estudo
do projeto. O grupo deve definir estratégias para mitigar o risco de dificuldades com a linguagem de programação, identificando membros do grupo com problemas na programação, selecionando videoaulas, referência bibliográficas, para
melhora a produtividade do time.
##### Product Backlog
> Cada requisito tem um identificador único de maneira que seja possível rastrear a necessidade do cliente com a implementação do software.
| Identificador | Descrição | Complexidade|
| ------------ | ------------------------------------------------------------------------ | ------|
| REQ01 – cadastrar produto | Como – vendedor, Eu quero – cadastrar o produto, De maneira que – seja possível consultar o produto para confirmar sua disponibilidade no estoque| Média |
| REQ02 – consultar catalogo| Como – caixa, Eu quero – consultar o catalogo de produtos, De maneira que – seja possível consultar o produto para confirmar uma venda| Média |
| REQ03 – upload de imagem | Como – vendedor, Eu quero – realizar o upload de imagens, De maneira que – seja possível consultar o produto para confirmar sua disponibilidade no estoque| Alta |
| REQ04 – atualizar informações do produto | Como – vendedor, Eu quero – atualizar as informações do produto, De maneira que – seja possível consultar o produto com informações atualizadas| Média |
| REQ05 – excluir informações do produto | Como – vendedor, Eu quero – excluir informações do produto, De maneira que – este produto não fique não disponível para venda| Média |
| REQ06 – cadastrar cliente | Como – vendedor, Eu quero – cadastrar o cliente, De maneira que – seja possível identificar o cliente e o endereço de entrega para confirmar uma venda| Média |
| REQ07 – consultar cliente por ID| Como – vendedor, Eu quero – consultar um cliente pelo ID, De maneira que – seja possível obter informações detalhas do cliente | Alta |
| REQ08 – consultar cliente por CPF | Como – vendedor, Eu quero – consultar um cliente, De maneira que – seja possível obter informações detalhas do cliente | Alta |
| REQ09 – consultar todos os clientes | Como – vendedor, Eu quero – consultar todos os clientes cadastrado, De maneira que – seja possível obter uma lista de clientes | Baixa |
| REQ10 – corrigir informações de cliente | Como – vendedor, Eu quero – corrigir as informações do cliente, De maneira que – seja possível manter as informações atualizadas | Baixa |
| REQ11 – excluir cliente | Como – vendedor, Eu quero – excluir um cliente, De maneira que – seja possível manter informações somente de clientes ativos | Baixa |
| REQ12 – registrar pedidos de compra | Como – vendedor, Eu quero – acompanhar o processo de compras na organização, De maneira que – seja possível prever a entrega programada de pedidos de compra| Alta |
| REQ13 – registrar recebimento | Como – vendedor, Eu quero – registrar o recebimento dos pedidos de compra, De maneira que – registrar a entrada de mercadorias e atualizar o estoque | Alta |
| REQ14 – registrar vendas | Como – vendedor, Eu quero – registrar as vendas, De maneira que – seja possivel tomar decisões na reposição do estoque | Alta |
##### Definição de pronto
> O sprint será considerado concluido quando:
> 1) Os casos de teste de aceitação forem executados e obtiverem 100% de satisfatorios. Os casos de teste (CT) são rastreáveis para os requisiitos (REQ). O elo de rastreabilidade
é estabelecido pelo identificador do caso de teste.
> 2) Depois de executado os casos de teste com 100% de satisfatorios o código deve ser armazenado no github (commit).
> 3) O relatório do SonarLint foi gerado e revisado.
##### Casos de teste
| Identificador | Cenário de uso |
| ------------ | ------------------------------------------------------------------------ |
| REQ06CT01 | Dado (setup) que o CPF do cliente não está cadastrado; Quando (ação) o usuário confirma o cadastro; Então (resultado esperado) o sistema envia uma mensagem de cadastro realizado com sucesso |
| REQ06CT02 | Dado (setup) que o CPF do cliente está cadastrado; Quando (ação) o usuário confirma o cadastro; Então (resultado esperado) o sistema rejeita e envia uma mensagem de dados inválidos |
>
O modelo de dominio (Larman, 2006 - classes conceituais ou classes de negócio) foi definido considerando as seguintes classes:
![modelo de dominio](https://user-images.githubusercontent.com/68782201/160412338-54c2c974-d6d2-4ab6-bea5-e1137a6f7e6c.jpg)
A arquitetura segue uma abordagem orientada a serviços. Os serviços foram classificados em três tipos (ERL, 2007):
- **1. Serviços utilitários**. Implementam funcionalidades comuns a vários tipos de aplicações, como, por exemplo: log, notificação, transformação de informações. Um exemplo de serviço utilitário é um serviço de conversão de moeda que
poderá ser acessado para calcular a conversão de uma moeda (por exemplo, dólares) para outra (por exemplo, euros).
- **2. Serviços de entidade (serviços de negócios)**. Derivado de uma ou mais entidades de negócio (domínio), possuindo um alto grau de reutilização. Geralmente são serviços que fazem operações CRUD (Create, Read, Update e Delete).
- **3. Serviços de tarefa (coordenação de processos-workflow)**. Tipo de serviço mais específico que possui baixo grau de reuso. Consome outros serviços para atender seus consumidores. São serviços que suportam um processo de negócios
amplo que geralmente envolve atividades e atores diferentes. Um exemplo de serviço de coordenação em uma empresa é um serviço de pedidos em que os pedidos são feitos, os produtos são aceitos e os pagamentos são efetuados.
A visão lógica da arquitetura para API de Cliente é apresentada na figura abaixo. A visã lógica descreve como o código está organizado, as classes os pacotes e os relacionamentos entre eles.
![f3_visao_logica](https://user-images.githubusercontent.com/68782201/162488505-5ec27561-eb83-42dc-a05f-27760e5bb7f3.jpg)
>A entidade Cliente foi identificada como um serviço (ERL, 2007 - serviço do tipo entidade). A visão lógica da arquitetura (Krutchen, 1995)
>descreve a organização do código. O contrato das operações de sistema (LARMAN, 2006, pag.140) foi definido no diagrama abaixo.
```mermaid
classDiagram
class ClienteServicoI
<<interface>> ClienteServicoI
 
ClienteServicoI : +List<Cliente> consultaTodos()
ClienteServicoI : +Optional<<Cliente>> consultaPorCpf(String cpf)
ClienteServicoI : +Optional<<Cliente>> consultaPorId(Long id)
ClienteServicoI : +Optional<<Cliente>> save(Cliente c)
ClienteServicoI : +void delete (Long id)
ClienteServicoI : +Optional<<Cliente>> altera (Cliente c)
```
> Operação consulta todos - o diagrama de sequência abaixo descreve como os varios componentes do projeto devem colaborar para atender
> o comportamento da função consulta todos.
```mermaid
sequenceDiagram
UsuarioDaAPI ->> APIClienteController: GET /api/v1/clientes
APIClienteController ->> ClienteServiceI: consultaTodos ( )
ClienteServiceI ->> ClienteRepository: findAll ( )
ClienteRepository -->> ClienteServiceI: List[]
ClienteServiceI-->> APIClienteController: List[]
```
>Referencias
- [1] KRUCHTEN, Philippe. Reference: Title: Architectural blueprints—the “4+ 1” view model of software architecture. IEEE software, v. 12, n. 6, 1995.
- [2] ERL, Thomas. SOA principles of service design (the Prentice Hall service-oriented computing series from Thomas Erl). Prentice Hall PTR, 2007.
- [3] LARMAN, Craig. Utilizando UML e padrões. 2aed., Porto Alegre: Bookman Editora, 2006 (pag. 147).
