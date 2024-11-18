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

Você pode ver o nosso Pitch aqui: [www.youtube.com](https://www.youtube.com/watch?v=sn--dy2o7Xo)

E, ver o teste da API aqui: [www.youtube.com](https://www.youtube.com/watch?v=sn--dy2o7Xo)

# Detalhes técnicos do projeto

## Figma de Mobile, e Frontend Csharp
[Figma de Mobile](https://www.figma.com/design/jOBzPXopQbuF1WyMb7BpNz/Eazy---Energia?t=bjPi5ig1kgWvlmyI-1)

## Modelo banco de dados
![image](https://github.com/user-attachments/assets/62261769-d39b-4ee1-851d-fc789cf4db3a)


## Endpoints

Abaixo, se enontra as tabelas que explanam quais os endpoints presentes no projeto:

Na pasta **```Documentation\```** do projeto, se encontra a collection do Postman para que você possa acessar facilmente os endpoints. Mas também temos o [**Swagger**](http://localhost:8080/swagger-ui/index.html#/Usuario/login).

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


