# Challenge Ahoy

## Description
Você precisa criar uma API que faça um CRUD completo de Vendedores considerando os seguintes dados:
•	Matrícula
o	Obrigatório e único
o	Deve terminar com os seguintes caracteres:
	- OUT
	- CLT
	- PJ
o	Exemplo:
	98767367-OUT
•	Nome 
o	Obrigatório
•	Data de Nascimento 
o	Opcional
o	Validar o formato Data
•	CPF ou CNPJ
o	Obrigatório
o	O valor precisa ser válido
•	E-mail
o	Obrigatório
o	Formato validado
•	Tipo de Contratação
o	Obrigatório
o	Valores possíveis:
	Outsourcing (terceirizado) 
	CLT
	Pessoa Jurídica 
•	Filial
o	Obrigatório
	Os valores da filial serão retornados através de uma API (que você deve mockar) com os seguintes dados: ID, Nome, CNPJ, Cidade, UF, Tipo, Ativo, Data Cadastro, Última Atualização 

REGRAS DE NEGÓCIO:

•	A matrícula deve ser um sequencial gerado automaticamente
•	Atentar para o documento a ser registrado pelo Vendedor. Pessoas Jurídicas utilizam CNPJ e os demais CPF
