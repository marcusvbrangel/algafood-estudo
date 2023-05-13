create table cidade (id bigserial not null, nome varchar(50) not null, estado_id bigint not null, primary key (id));
create table cozinha (id bigserial not null, nome varchar(50) not null, primary key (id));
create table estado (id bigserial not null, nome varchar(50) not null, primary key (id));
create table forma_pagamento (id bigserial not null, descricao varchar(100) not null, primary key (id));
create table grupo (id bigserial not null, nome varchar(50) not null, primary key (id));
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null);
create table permissao (id bigserial not null, descricao varchar(200), nome varchar(50) not null, primary key (id));
create table produto (id bigserial not null, ativo boolean not null, nome varchar(50) not null, preco numeric(38,2) not null, restaurante_id bigint not null, primary key (id));
create table restaurante (id bigserial not null, data_atualizacao timestamp(6) not null, data_cadastro timestamp(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(50) not null, taxa_frete numeric(38,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id));
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null);
create table usuario (id bigserial not null, data_atualizacao timestamp(6) not null, data_cadastro timestamp(6) not null, email varchar(50) not null, nome varchar(50) not null, senha varchar(20) not null, primary key (id));
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null);
alter table if exists cozinha add constraint UK_21inunwxqp3wdrnbji4sp1vli unique (nome);
alter table if exists estado add constraint UK_gfot2y0318rs8hc74ppp0n87p unique (nome);
alter table if exists cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado;
alter table if exists grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao;
alter table if exists grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo;
alter table if exists produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante;
alter table if exists restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha;
alter table if exists restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade;
alter table if exists restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento;
alter table if exists restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante;
alter table if exists usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo;
alter table if exists usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario;
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
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Restaurante Tailandez', 2.34, 1, CURRENT_DATE, CURRENT_DATE);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Restaurante Grande Indiano', 2.34, 2, CURRENT_DATE, CURRENT_DATE);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 1);
insert into produto (nome, preco, ativo, restaurante_id) values ('Lagosta', 87.99, true, 1);
insert into produto (nome, preco, ativo, restaurante_id) values ('Frango', 200.01, false, 1);
insert into produto (nome, preco, ativo, restaurante_id) values ('Vinho', 188.10, true, 2);
insert into produto (nome, preco, ativo, restaurante_id) values ('Arroz', 33.54, false, 3);