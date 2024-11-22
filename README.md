# Eazy - Java Backend

### Integrantes:

[**Diego Costa Silva - RM552648 - 2TDSPC**](https://www.linkedin.com/in/diegocostacs/)
-Desenvolvedor de Java, Mobile, Banco de dados Oracle; Deploy de máquinas virtuais e aplicações..

[**Lucas Minozzo Bronzeri - RM553745 - 2TDSPC**](https://www.linkedin.com/in/lucas-minozzo-bronzeri-b212a4248/)
-Desenvolvedor Python, e Banco de dados Oracle; Deploy de máquinas virtuais e aplicações. 

[**Thaís Ribeiro Asfur - RM553870 - 2TDSZ**](https://www.linkedin.com/in/thaís-ribeiro-asfur-52b0692a2/)
-Desenvolvedora Csharp, Banco de dados Oracle, e responsável por QA do projeto; 

## Descrição do projeto

Todos nós já sentimos a necessidade de gerenciar nossas contas de forma fácil, mas nunca tivemos uma planilha muito bem elaborada, ou nem conhecimento o suficiente para connstruir uma plannilha repleta de fórmulas.
Com este cenário em mente e visando a **necessidade** de nosso mundo em buscar formas mais sustentáveis de viver, nós pensamos em **aplicação gratuita que permite o usuário ter controle/conciência de seus gastos com conta elétrica** . 
Além disso, **nossa plataforma entrega insights sobre como ele poderia economizar no logo prazo, adotando meios de energia sustentáveis.**

Acreditamos firmemennte que nosso aplicativo é o ponta pé inicial para alguém ter um olhar mais cuidadoso com seu dinheiro, e o planeta em que vivemos. Mostrando que pode ser mais **Eazy** do que você pensa mudar o rumo do planeta.

## Relevância 

Nosso projeto aborda um problema significativo na área de energia sustentável: o consumo elevado e o desperdício de energia elétrica devido à falta de informação e monitoramento dos usuários. A solução proposta tem potencial para melhorar a qualidade de vida dos usuários, promovendo a adoção de fontes de energia renováveis e conscientizando sobre o consumo consciente.

## Viabilidade e Usabilidade

A solução proposta é tecnicamente viável, utilizando tecnologias de desenvolvimento de aplicações móveis, APIs para análise de dados e recursos que permitem ao usuário carregar suas contas e visualizar insights de maneira simples. Os alunos demonstraram compreensão das tecnologias utilizadas e sua aplicação no projeto, garantindo a implementação de funcionalidades-chave, como a previsão de consumo e a simulação de economia ao adotar energia solar.

A usabilidade é um dos principais focos do projeto: nossa aplicação foi projetada para ser fácil de usar, com uma interface intuitiva que facilita o acesso às informações e funcionalidades. Queremos que tanto o público-alvo quanto outros stakeholders possam utilizar a solução sem dificuldade, promovendo a conscientização e mudança de hábitos de consumo de energia de maneira amigável e eficaz.

Você pode ver o nosso Pitch aqui: [pitch](https://www.youtube.com/watch?v=l5fuZtikMCA&list=PLNpYt22sUw3VJftQ8ltlV-7C0Aayi30p3&index=1)

E, ver o teste da API aqui: [testando na VM](https://www.youtube.com/watch?v=Y0TDjuulEb4&list=PLNpYt22sUw3VJftQ8ltlV-7C0Aayi30p3&index=4)

# Detalhes técnicos do projeto

## Figma de Mobile, e Frontend Csharp
[Figma de Mobile](https://www.figma.com/design/jOBzPXopQbuF1WyMb7BpNz/Eazy---Energia?t=bjPi5ig1kgWvlmyI-1)

## Modelo banco de dados
![image](https://github.com/user-attachments/assets/62261769-d39b-4ee1-851d-fc789cf4db3a)

## VM Java

![image](https://github.com/user-attachments/assets/503127f5-5af8-40a0-9e54-4adc5f4ee8a3)

## Endpoints

Abaixo, se enontra as tabelas que explanam quais os endpoints presentes no projeto:

Na pasta [**```Documentation\```**](https://github.com/DiegoCostaCode/GlobalSolution-Eazy-Java/blob/master/eazy/documentation/POSTMAN_COLLECTION) do projeto, se encontra a collection do Postman para que você possa acessar facilmente os endpoints. Mas também temos o [**Swagger**](http://localhost:8080/swagger-ui/index.html#/Usuario/login).


**Tabela de Usuário**
| **Grupo**   | **Endpoint**   | **Método** | **URL**                             | **Descrição**                                                                                           |
|-------------|----------------|------------|-------------------------------------|---------------------------------------------------------------------------------------------------------|
| **Usuario**  | BuscarId       | GET        | http://localhost:8080/usuario/{id} | Retorna os detalhes de um usuário específico pelo ID.                                                  |
|             | Login          | POST       | http://localhost:8080/usuario/login| Realiza o login de um usuário com as credenciais fornecidas.                                          |
|             | Criar          | POST       | http://localhost:8080/usuario      | Cria um novo usuário com os dados fornecidos no corpo da requisição.                                   |
|             | Atualizar      | PUT        | http://localhost:8080/usuario/{id} | Atualiza os dados de um usuário específico pelo ID.                                                    |
|             | Delete         | DELETE     | http://localhost:8080/usuario/{id} | Remove um usuário específico pelo ID.                                                                   |


**Tabela de Conta**
| **Grupo**   | **Endpoint**          | **Método** | **URL**                                   | **Descrição**                                                                                           |
|-------------|-----------------------|------------|-------------------------------------------|---------------------------------------------------------------------------------------------------------|
| **Conta**   | BuscarId              | GET        | http://localhost:8080/conta/{id}         | Retorna os detalhes de uma conta específica pelo ID.                                                  |
|             | BuscarIdUsuario       | GET        | http://localhost:8080/conta/usuario/{id} | Retorna as contas associadas a um usuário específico pelo ID do usuário.                              |
|             | Criar                 | POST       | http://localhost:8080/conta/{idUsuario}  | Cria uma nova conta com os dados fornecidos no corpo da requisição e associa a um usuário.            |
|             | Atualizar             | PUT        | http://localhost:8080/conta/{id}         | Atualiza os dados de uma conta específica pelo ID.                                                    |
|             | Deletar               | DELETE     | http://localhost:8080/conta/{id}         | Remove uma conta específica pelo ID.                                                                    |


**Tabela de Informações Tributárias**
| **Grupo**                     | **Endpoint**        | **Método** | **URL**                                   | **Descrição**                                                                                           |
|-------------------------------|---------------------|------------|-------------------------------------------|---------------------------------------------------------------------------------------------------------|
| **Informações Tributárias**   | BuscarTodos          | GET        | http://localhost:8080/infoTriburaria     | Retorna todas as informações tributárias disponíveis.                                                  |
|                               | BuscarById           | GET        | http://localhost:8080/infoTriburaria/{id}| Retorna os detalhes de uma informação tributária específica pelo ID.                                   |
|                               | BuscarByEstado       | GET        | http://localhost:8080/infoTriburaria/{estado} | Retorna as informações tributárias relacionadas a um estado específico.                                 |
|                               | Criar                | POST       | http://localhost:8080/infoTriburaria     | Cria uma nova informação tributária com os dados fornecidos no corpo da requisição.                    |
|                               | Atualizar            | PUT        | http://localhost:8080/infoTriburaria/{id}| Atualiza os dados de uma informação tributária específica pelo ID.                                     |


