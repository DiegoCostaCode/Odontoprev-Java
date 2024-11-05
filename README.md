# Odontoprev-Java

## Integrantes do Grupo

* **[Diego Costa Silva RM552648](https://www.linkedin.com/in/diegocostacs/)**: Desenvolvedor principal do projeto, responsável por criar a aplicação e documentar o processo de desenvolvimento.
* **[Lucas Minozzo Bronzeri RM553745](https://www.linkedin.com/in/lucas-minozzo-bronzeri-b212a4248/)**: Coworker que cuidou de Banco de Dados, e ajudou na estruturação e nas tomadas de decisão do projeto.

## Descrição do Problema

**Clínicas médicas fraudando atendimentos/exames/cirurgias e clientes que utilizam do plano de forma imprópria/desnecessária, gerando gastos para a Odontoprev.**

## Descrição da Solução

**Um aplicativo que coloca a Odontoprev, o cliente e a clínica no mesmo ambiente, permitindo que os clientes agendem e acompanhem suas atendimentos, contratem planos e avaliem clínicas. A clínica pode lançar informações referentes às atendimentos dos clientes e receber também.**

## Progresso na Sprint 2

Na Sprint 2, realizamos diversas melhorias e implementações para expandir e otimizar a estrutura do sistema:

| Implementação                                | Descrição                                                                                                                                                                                                                                 |
|----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Refatoração do Banco de Dados**            | Revisamos e aprimoramos o modelo de dados, refletindo as atualizações no DER e garantindo um banco mais eficiente e escalável. As tabelas e classes foram criadas com **JPA** e **Hibernate** para integração com o sistema Java.         |
| **Simplificação de Código com Lombok**       | Implementamos **Lombok** para reduzir o código boilerplate, facilitando a manutenção e deixando o código mais limpo e legível ao eliminar a necessidade de getters e setters manuais.                                                    |
| **Implementação de HATEOAS com EntityModel** | Usando a biblioteca **Spring HATEOAS**, integramos o padrão HATEOAS aos endpoints da API, utilizando **EntityModel**. Com isso, os endpoints RESTful agora oferecem links dinâmicos, facilitando a navegação e escalabilidade da API.     |
| **Procedures no Banco de Dados**             | Desenvolvemos procedures no banco de dados para operações específicas de Paciente e Clínica, centralizando a lógica de negócios diretamente no banco e aprimorando o desempenho e eficiência em consultas complexas.                        |

## Atividades da Sprint 2

Nesta Sprint, focamos em diversas atividades para aprimorar a aplicação, que incluem a modelagem do banco de dados, implementação de funcionalidades e documentação. Abaixo estão as atividades realizadas:

## Atividades da Sprint 2

| Atividade                                                                 | Responsável          | Prazo de Entrega |
|---------------------------------------------------------------------------|----------------------|------------------|
| Revisar modelagem do banco de dados e identificar melhorias para a aplicação | Diego Costa Silva    | 01/10            |
| Refatorar o modelo DER do banco para refletir as melhorias e iniciar a implementação em Java | Diego Costa Silva    | 01/10            |
| Implementação do banco de dados e criação das tabelas/classes (usando JPA e Hibernate) | Diego Costa Silva    | 01/10            |
| Desenvolvimento dos endpoints RESTful da API para CRUD de Pacientes, Clínicas, Planos, Endereços e tabelas dependentes (Carteirinha, Atendimento, Procedimento, Sinistro e Doutor) | Diego Costa Silva    | 01/10            |
| Implementação do Lombok para simplificação do código | Diego Costa Silva | 01/10 |
| Criação de procedures para entrega paralela de Database | Diego Costa Silva | 01/10 |
| Aplicação de HATEOAS com EntityModel | Diego Costa Silva | 01/10 |
| Realizar testes iniciais na estrutura desenvolvida para refinamento do projeto | Diego Costa Silva    | 01/10            |
| Documentar o projeto e preparar os entregáveis | Diego Costa Silva | 01/10 |


# API Endpoints - SPRINT2 - Java
**[Testando endpoints](https://youtu.be/XoNUMdeHdU8)**

## Paciente

| Método | URL                           | Descrição                      |
|--------|-------------------------------|--------------------------------|
| GET    | `/paciente`                   | Listar Pacientes               |
| GET    | `/paciente/{id}`              | Obter Paciente por ID          |
| POST   | `/paciente`                   | Cadastrar Paciente             |
| PUT    | `/paciente/{id}`              | Atualizar Cadastro de Paciente  |
| DELETE | `/paciente/{id}`              | Deletar Cadastro de Paciente    |

## Clínica

| Método | URL                           | Descrição                      |
|--------|-------------------------------|--------------------------------|
| GET    | `/clinica`                    | Listar Clínicas                |
| GET    | `/clinica/{id}`               | Obter Clínica por ID           |
| POST   | `/clinica`                    | Cadastrar Clínica              |
| PUT    | `/clinica/{id}`               | Atualizar Cadastro de Clínica   |
| DELETE | `/clinica/{id}`               | Deletar Cadastro de Clínica     |

## Plano

| Método | URL                           | Descrição                      |
|--------|-------------------------------|--------------------------------|
| GET    | `/plano`                      | Listar Planos                  |
| POST   | `/plano`                      | Cadastrar Plano                |
| PUT    | `/plano/{id}`                 | Atualizar Plano                |
| DELETE | `/plano/{id}`                 | Deletar Plano                  |

## Endereço

| Método | URL                                   | Descrição                                   |
|--------|---------------------------------------|---------------------------------------------|
| POST   | `/endereco/paciente/{id}`            | Adicionar Endereço a Paciente               |
| PUT    | `/endereco/paciente/{pacienteId}/{enderecoId}` | Atualizar Endereço de Paciente              |
| POST   | `/endereco/clinica/{id}`            | Adicionar Endereço a Paciente               |
| PUT    | `/endereco/clinica/{clinicaId}/{enderecoId}` | Atualizar Endereço de Clinica              |
| DELETE | `/endereco/{id}`                     | Deletar Endereço de Paciente                |

## Carteirinha

| Método | URL                       | Descrição                     |
|--------|---------------------------|-------------------------------|
| POST   | `/carteirinha`           | Gerar Carteirinha             |
| GET    | `/carteirinha`           | Listar Carteirinhas          |
| PUT    | `/carteirinha/{id}`           | Atualizar plano de Carteirinha          |
| DELETE | `/carteirinha/{id}`      | Deletar Carteirinha          |

## Atendimento

| Método | URL                       | Descrição                     |
|--------|---------------------------|-------------------------------|
| GET   | `/atendimento`            | Listar Atendimento             |
| POST   | `/atendimento`            | Marcar Atendimento             |
| PUT    | `/atendimento/{id}`       | Atualizar Atendimento          |
| DELETE | `/atendimento/{id}`       | Deletar Atendimento            |

## Procedimento

| Método | URL                          | Descrição                     |
|--------|------------------------------|-------------------------------|
| POST   | `/procedimento/criar`        | Registrar Procedimento        |
| GET    | `/procedimento`              | Listar Procedimentos         |
| PUT    | `/procedimento/{id}`         | Atualizar Procedimento        |
| DELETE | `/procedimento/{id}`         | Deletar Procedimento          |

## Sinistro

| Método | URL                       | Descrição                     |
|--------|---------------------------|-------------------------------|
| GET    | `/sinistro`               | Listar Fraudes                |
| POST   | `/sinistro`               | Registrar Fraude              |
| PUT    | `/sinistro/{id}`          | Atualizar Fraude              |
| DELETE | `/sinistro/{id}`          | Deletar Fraude                |

## Doutor

| Método | URL                          | Descrição                     |
|--------|------------------------------|-------------------------------|
| POST   | `/doutor/cadastrar`          | Cadastrar Doutor              |
| GET    | `/doutor/{id}`               | Obter Doutor por ID          |
| PUT    | `/doutor/{id}`               | Atualizar Doutor              |
| DELETE | `/doutor/{id}`               | Deletar Doutor                |

## Clinica-Doutor-Relacionamento

| Método | URL                                   | Descrição                     |
|--------|---------------------------------------|-------------------------------|
| POST   | `/cadastro-relacionamento`            | Criar Relacionamento          |
| GET    | `/cadastro-relacionamento`            | Listar Relações               |
| PUT    | `/cadastro-relacionamento/{id}`       | Atualizar Relacionamento      |
| DELETE | `/cadastro-relacionamento/{id}`       | Deletar Relacionamento        |

## Procedures (Endpoint extra)

| Método | URL                          | Descrição                     |
|--------|------------------------------|-------------------------------|
| | POST   | `/paciente/procedure`         | Inserir Odonto Paciente       |
| PUT    | `/paciente/procedure/{id}`    | Update Odonto Paciente        |
| DELETE | `/paciente/procedure/{id}`    | Delete Odonto Paciente        |
| POST   | `/clinica/procedure`          | Inserir Odonto Clínica        |
| PUT    | `/clinica/inserirOdontoClinica/{id}` | Update Odonto Clínica        |
| DELETE | `/clinica/procedure/{id}`     | Delete Odonto Clínica         |


## Instrução de como rodar a aplicação

1. **Clone o repositório do projeto**: `git clone https://github.com/DiegoCostaSilva/Odontoprev-Java.git`
2. **Faça o BUILD do projeto**
3. **Rode o projeto**
4. **Visite o seguinte URL para testar os ENDPOINTS**:  [Swagger](http://localhost:8080/swagger-ui/index.html#/)

## Imagem dos Diagramas

* **Diagrama de Classes**: ![plantuml_page-0001](https://github.com/user-attachments/assets/8de083c6-a6cd-4fbf-8dd3-749f5405983a)
  
* **Diagrama de Sequência**: ![Logica3l](https://github.com/user-attachments/assets/5500ef6b-b64b-414a-814a-a8418b6cc3d3)



## Link para vídeo apresentando a Proposta Tecnológica



