Pizzaria API

Esta API foi desenvolvida para gerenciar uma pizzaria, permitindo aos usuários fazer pedidos, consultar o cardápio e realizar autenticação de maneira simples e eficaz. O objetivo é fornecer uma interface robusta para que os sistemas de pedidos de pizza possam ser integrados facilmente.
Funcionalidades

    Autenticação: Implementação de login e registro de usuários com JWT para segurança.
    Pedidos: Adicione, visualize e altere pedidos de pizzas.
    Cardápio: Consulte as pizzas disponíveis no cardápio e seus detalhes.
    Gestão de Pizzas: Admins podem adicionar, editar e excluir pizzas.
    Estoque: Acompanhe o status dos pedidos em tempo real.

Tecnologias

    Java 17+
    Spring Boot 2.6+
    Spring Security para autenticação e segurança
    JPA e Hibernate para persistência de dados
    JWT para autenticação segura

Endpoints
/auth/login

    Método: POST
    Descrição: Realiza o login de um usuário e retorna um token JWT.
    Parâmetros:
        email: E-mail do usuário.
        password: Senha do usuário.

/auth/register

    Método: POST
    Descrição: Realiza o registro de um novo usuário.

/orders

    Método: POST
    Descrição: Cria um novo pedido com as pizzas selecionadas.
    Parâmetros:
        pizzaIds: Lista de IDs das pizzas para o pedido.

/pizzas

    Método: GET
    Descrição: Retorna todas as pizzas disponíveis no cardápio.

Como rodar

    Clone o repositório.
    Abra o terminal e navegue até o diretório do projeto.
    Execute o seguinte comando para compilar e rodar a API:

    mvn spring-boot:run

Contribuindo

Contribuições são bem-vindas! Se você deseja adicionar novas funcionalidades ou corrigir bugs, fique à vontade para abrir um pull request.
