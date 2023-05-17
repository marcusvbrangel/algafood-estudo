
-- SET CONSTRAINTS ALL DEFERRED;

-- BEGIN;

delete from item_pedido;
delete from pedido;
delete from produto;
delete from foto_produto;
delete from restaurante_forma_pagamento;
delete from forma_pagamento;
delete from restaurante;
delete from cozinha;
delete from cidade;
delete from estado;
delete from usuario;
delete from usuario_grupo;
delete from permissao;
delete from grupo;
delete from grupo_permissao;


ALTER SEQUENCE public.cidade_id_seq RESTART WITH 1;
ALTER SEQUENCE public.cozinha_id_seq RESTART WITH 1;
ALTER SEQUENCE public.estado_id_seq RESTART WITH 1;
ALTER SEQUENCE public.forma_pagamento_id_seq RESTART WITH 1;
ALTER SEQUENCE public.foto_produto_id_seq RESTART WITH 1;
ALTER SEQUENCE public.grupo_id_seq RESTART WITH 1;
ALTER SEQUENCE public.permissao_id_seq RESTART WITH 1;
ALTER SEQUENCE public.produto_id_seq RESTART WITH 1;
ALTER SEQUENCE public.restaurante_id_seq RESTART WITH 1;
ALTER SEQUENCE public.usuario_id_seq RESTART WITH 1;


insert into cozinha(nome) values ('Cozinha Tailandeza');
insert into cozinha(nome) values ('Cozinha Indiana');

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

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, data_cadastro, data_atualizacao) values ('Restaurante Indiano', 10.09, 2, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', CURRENT_DATE, CURRENT_DATE);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Restaurante Tailandez', 2.34, 1, 5,CURRENT_DATE, CURRENT_DATE);
insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, data_cadastro, data_atualizacao) values ('Restaurante Grande Indiano', 2.34, 2, 9, CURRENT_DATE, CURRENT_DATE);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 1);

insert into produto (nome, preco, ativo, restaurante_id) values ('Lagosta', 87.99, true, 1);
insert into produto (nome, preco, ativo, restaurante_id) values ('Frango', 200.01, false, 1);
insert into produto (nome, preco, ativo, restaurante_id) values ('Vinho', 188.10, true, 2);
insert into produto (nome, preco, ativo, restaurante_id) values ('Arroz', 33.54, false, 3);

-- COMMIT;

