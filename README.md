# Challenge Ahoy

## Description

Você precisa criar uma API que faça um CRUD completo de Vendedores considerando os seguintes dados:

- Matrícula
    - Obrigatório e único
    - Deve terminar com os seguintes caracteres:
        - OUT
        - CLT
        - PJ
    - Exemplo:
        98767367-OUT
- Nome 
    - Obrigatório
- Data de Nascimento 
    - Opcional
    - Validar o formato Data
- CPF ou CNPJ
    - Obrigatório
    - O valor precisa ser válido
- E-mail
    - Obrigatório
    - Formato validado
- Tipo de Contratação
    - Obrigatório
    - Valores possíveis:
        - Outsourcing (terceirizado) 
        - CLT
        - Pessoa Jurídica 
- Filial
    - Obrigatório
    Os valores da filial serão retornados através de uma API (que você deve mockar) com os seguintes dados:
        - ID, Nome, CNPJ, Cidade, UF, Tipo, Ativo, Data Cadastro, Última Atualização 

## REGRAS DE NEGÓCIO:
    - A matrícula deve ser um sequencial gerado automaticamente
    - Atentar para o documento a ser registrado pelo Vendedor. Pessoas Jurídicas utilizam CNPJ e os demais CPF
