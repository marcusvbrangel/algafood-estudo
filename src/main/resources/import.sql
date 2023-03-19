insert into cozinha(nome) values ('Cozinha Tailandeza');
insert into cozinha(nome) values ('Cozinha Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante Indiano', 10.09, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante Tailandez', 2.34, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante Grande Indiano', 2.34, 2);

insert into forma_pagamento (descricao) values ('Débito');
insert into forma_pagamento (descricao) values ('Crédito');
insert into forma_pagamento (descricao) values ('Paypal');
insert into forma_pagamento (descricao) values ('Pix');
insert into forma_pagamento (descricao) values ('Dinheiro');

insert into permissao (nome, descricao) values ('Incluir', 'Descrição incluir');
insert into permissao (nome, descricao) values ('Alterar', '');
insert into permissao (nome, descricao) values ('Excluir', 'Descrição excluir');
insert into permissao (nome, descricao) values ('Recuperar', '');
insert into permissao (nome, descricao) values ('Listar', 'descrição listar');

insert into estado (nome) values ('Rio de Janeiro');
insert into estado (nome) values ('Espirito Santo');
insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('São Paulo');

insert into cidade (nome, estado_id) values ('Rio de Janeiro', 1);
insert into cidade (nome, estado_id) values ('Cabo Frio', 1);
insert into cidade (nome, estado_id) values ('Niteroi', 1);
insert into cidade (nome, estado_id) values ('Linhares', 2);
insert into cidade (nome, estado_id) values ('Marataizes', 2);
insert into cidade (nome, estado_id) values ('Vila Velha', 2);
insert into cidade (nome, estado_id) values ('Belo Horizonte', 3);
insert into cidade (nome, estado_id) values ('Juiz de Fora', 3);
insert into cidade (nome, estado_id) values ('Uberlândia', 3);
insert into cidade (nome, estado_id) values ('São Paulo', 4);
insert into cidade (nome, estado_id) values ('Campinas', 4);
insert into cidade (nome, estado_id) values ('Ribeirão Preto', 4);