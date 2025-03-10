# Odontoprev-Java

## Integrantes do Grupo

* **[Diego Costa Silva RM552648](https://www.linkedin.com/in/diegocostacs/)**: Desenvolvedor principal do projeto, responsável por criar a aplicação e documentar o processo de desenvolvimento.
* **[Lucas Minozzo Bronzeri RM553745](https://www.linkedin.com/in/lucas-minozzo-bronzeri-b212a4248/)**: Coworker que cuidou de Banco de Dados, e ajudou na estruturação e nas tomadas de decisão do projeto.

## Descrição do Problema

**Clínicas médicas fraudando atendimentos/exames/cirurgias e clientes que utilizam do planos de forma imprópria/desnecessária, gerando gastos para a Odontoprev.**

## Descrição da Solução

**Nosso sistema end-to-end conecta o administrador Odontoprev, as clínicas e os pacientes em um ecossistema integrado, projetado para facilitar o rastreamento e a auditoria de informações. Para os pacientes, desenvolvemos um aplicativo em Kotlin que permite acessar informações sobre seu planos e tomar decisões informadas com base em recomendações personalizadas, utilizando inteligência artificial com Deep Learning.**

**O backend, implementado em Java, gerencia as APIs e integra todos os módulos. Para clínicas e administradores Odontoprev, utilizamos uma plataforma web construída em .NET que fornece insights de dados para melhorar a gestão. O administrador Odontoprev terá uma visão completa do sistema, incluindo ferramentas para auditoria eficiente e transparência no uso dos planos.**

**Acreditamos que este sistema será o ponto de partida para a Odontoprev solucionar seus desafios atuais, oferecendo uma plataforma de informações centralizada, moderna e intuitiva.**

## Progresso na Sprint 2

Na Sprint 2, realizamos diversas melhorias e implementações para expandir e otimizar a estrutura do sistema:

| Implementação                                | Descrição                                                                                                                                                                                                                                 |
|----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Refatoração do Banco de Dados**            | Revisamos e aprimoramos o modelo de dados, refletindo as atualizações no DER e garantindo um banco mais eficiente e escalável. As tabelas e classes foram criadas com **JPA** e **Hibernate** para integração com o sistema Java.         |
| **Simplificação de Código com Lombok**       | Implementamos **Lombok** para reduzir o código boilerplate, facilitando a manutenção e deixando o código mais limpo e legível ao eliminar a necessidade de getters e setters manuais.                                                    |
| **Implementação de HATEOAS com EntityModel** | Usando a biblioteca **Spring HATEOAS**, integramos o padrão HATEOAS aos endpoints da API, utilizando **EntityModel**. Com isso, os endpoints RESTful agora oferecem links dinâmicos, facilitando a navegação e escalabilidade da API.     |

## Atividades da Sprint 2

Nesta Sprint, focamos em diversas atividades para aprimorar a aplicação, que incluem a modelagem do banco de dados, implementação de funcionalidades e documentação. Abaixo estão as atividades realizadas:

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
**Teste de endpoints em vídeo -> [Testando endpoints](https://youtu.be/XoNUMdeHdU8)**

**O collection do Postman para teste dos endpoints se encontra na pasta `/documentation/`**

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
| GET    | `/planos`                      | Listar Planos                  |
| POST   | `/planos`                      | Cadastrar Plano                |
| PUT    | `/planos/{id}`                 | Atualizar Plano                |
| DELETE | `/planos/{id}`                 | Deletar Plano                  |

## Endereço

| Método | URL                                   | Descrição                                   |
|--------|---------------------------------------|---------------------------------------------|
| POST   | `/endereco/paciente/{id}`            | Adicionar Endereço a Paciente               |
| PUT    | `/endereco/paciente/{pacienteId}/{enderecoId}` | Atualizar Endereço de Paciente              |
| POST   | `/endereco/clinica/{id}`            | Adicionar Endereço a Clínica                |
| PUT    | `/endereco/clinica/{clinicaId}/{enderecoId}` | Atualizar Endereço de Clínica               |
| DELETE | `/endereco/{id}`                     | Deletar Endereço de Paciente                |

## Carteirinha

| Método | URL                       | Descrição                     |
|--------|---------------------------|-------------------------------|
| POST   | `/carteirinha`           | Gerar Carteirinha             |
| GET    | `/carteirinha`           | Listar Carteirinhas          |
| PUT    | `/carteirinha/{id}`      | Atualizar planos de Carteirinha|
| DELETE | `/carteirinha/{id}`      | Deletar Carteirinha          |

## Atendimento

| Método | URL                       | Descrição                     |
|--------|---------------------------|-------------------------------|
| GET    | `/agendamento`            | Listar Atendimento            |
| POST   | `/agendamento`            | Marcar Atendimento             |
| PUT    | `/agendamento/{id}`       | Atualizar Atendimento          |
| DELETE | `/agendamento/{id}`       | Deletar Atendimento            |

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
| POST   | `/dentista/cadastrar`          | Cadastrar Doutor              |
| GET    | `/dentista/{id}`               | Obter Doutor por ID          |
| PUT    | `/dentista/{id}`               | Atualizar Doutor              |
| DELETE | `/dentista/{id}`               | Deletar Doutor                |

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
| POST   | `/paciente/procedure`         | Inserir Odonto Paciente       |
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

# Odontoprev-Java: Configuração com Docker

Este projeto utiliza o Docker para facilitar a construção e execução da aplicação **Odontoprev-Java** e seus serviços associados, como o banco de dados Oracle. Abaixo, descrevemos a estrutura utilizada e justificamos as escolhas feitas para garantir uma solução eficiente e fácil de gerenciar.

## Estrutura Docker

### Dockerfile

Criamos um Dockerfile que segue a estratégia de *multi-stage build*, dividindo o processo em duas etapas. Na primeira, utilizamos uma imagem do Gradle para compilar o código e gerar o arquivo JAR da aplicação. Na segunda, usamos uma imagem do OpenJDK para criar uma imagem final enxuta, contendo apenas o essencial para a execução da aplicação. Isso resulta em uma imagem mais leve e segura, com um processo de construção mais eficiente.

Principais pontos do Dockerfile:

- **Multi-stage build**: Permite separar a etapa de construção da etapa de execução, garantindo uma imagem final mais limpa e otimizada.
- **Permissões apropriadas**: O uso de `COPY --chown=gradle:gradle` garante que os arquivos tenham permissões corretas, evitando problemas durante a construção.
- **Porta exposta**: A aplicação é configurada para escutar na porta `8080`, permitindo o direcionamento adequado do tráfego.
- **Comando de entrada**: O comando `ENTRYPOINT` é usado para iniciar a aplicação quando o contêiner é executado.

### Docker Compose

Para orquestrar os serviços da aplicação, utilizamos o Docker Compose. Definimos um arquivo `docker-compose.yml` que inclui tanto a aplicação Java quanto o banco de dados Oracle, permitindo que ambos os serviços sejam iniciados e configurados de maneira coordenada.

Principais pontos do Docker Compose:

- **Serviços integrados**: Dois serviços são definidos: `odontoprevjava` (a aplicação Java) e `oracle-db` (o banco de dados). A aplicação depende do banco de dados, garantindo que ele seja iniciado antes.
- **Configuração dinâmica**: Variáveis de ambiente são usadas para configurar credenciais e URLs, tornando o ambiente flexível e adaptável a diferentes cenários.
- **Rede e armazenamento**: Uma rede personalizada (“app-network”) conecta ambos os serviços, e um volume (“oracle-data”) é utilizado para armazenar os dados do banco, garantindo persistência.
- **Limitação de recursos**: Definimos limites de CPU e memória para cada serviço, ajudando a garantir que os recursos do sistema sejam usados de forma controlada.

## Como Executar

Para iniciar os serviços, certifique-se de ter o Docker e o Docker Compose instalados e execute o comando:

```sh
docker-compose up --build
```
## Deploy
**[Vídeo](https://youtu.be/zt8nd6TeFQM)**


## Imagem dos Diagramas

* **Diagrama de Classes**: ![plantuml_page-0001](https://github.com/user-attachments/assets/8de083c6-a6cd-4fbf-8dd3-749f5405983a)
  
* **Diagrama de Sequência**: ![Logica3l](https://github.com/user-attachments/assets/5500ef6b-b64b-414a-814a-a8418b6cc3d3)

## Link para vídeo apresentando a Proposta Tecnológica
[Pitch](https://youtu.be/SBQ-_mBXdK0)
