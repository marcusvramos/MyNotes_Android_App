# MyNotes2024

## Descrição
O **MyNotes** é um aplicativo Android desenvolvido em Java, criado para gerenciar pequenas anotações ou lembretes. O aplicativo permite o registro de anotações com informações como título, texto e prioridade (alta, normal e baixa). A persistência dos dados é feita via SQLite, garantindo que as anotações sejam salvas mesmo ao fechar ou minimizar o app.

O app exibe as anotações em um ListView personalizado, onde a cor de cada item varia conforme a prioridade da anotação. Ele também oferece funcionalidades para cadastrar, visualizar, ordenar e excluir anotações de forma simples e eficiente.

## Funcionalidades

- **Cadastrar anotações**: Registre uma nova anotação com título, texto e nível de prioridade.
- **Visualizar anotações**: As anotações são exibidas em uma lista, com cores que indicam sua prioridade:
    - Vermelho: Alta
    - Azul: Normal
    - Amarelo: Baixa
- **Ordenar anotações**: Ordene as anotações por prioridade ou em ordem alfabética.
- **Excluir anotações**: Toque longo em um item da lista para excluí-lo.
- **Visualizar detalhes**: Toque curto em uma anotação para visualizar os detalhes em uma nova tela.
- **Persistência de dados**: As anotações são salvas no banco de dados SQLite e carregadas automaticamente ao iniciar o aplicativo.
- **Splash Screen**: Tela customizada assim que inicia o aplicativo.

## Tecnologias Utilizadas
- **Linguagem**: Java
- **Banco de Dados**: SQLite
- **Componentes de Interface**: ListView personalizado, Activities
