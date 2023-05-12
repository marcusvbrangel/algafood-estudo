delete from flyway_schema_history
/
create table cidade (
  id serial,
  nome_cidade varchar(60) not null,
  nome_estado varchar(60) not null,
  primary key (id)
);

drop table cidade
/
select * from cidade


insert into cidade (nome_cidade, nome_estado) values ('Rio de Janeiro', 'Rio de Janeiro');
insert into cidade (nome_cidade, nome_estado) values ('Cabo Frio', 'Rio de Janeiro');
insert into cidade (nome_cidade, nome_estado) values ('Niteroi', 'Rio de Janeiro');
insert into cidade (nome_cidade, nome_estado) values ('Linhares', 'Espirito Santo');
insert into cidade (nome_cidade, nome_estado) values ('Marataizes', 'Espirito Santo');
insert into cidade (nome_cidade, nome_estado) values ('Vila Velha', 'Espirito Santo');
insert into cidade (nome_cidade, nome_estado) values ('Belo Horizonte', 'Minas Gerais');
insert into cidade (nome_cidade, nome_estado) values ('Juiz de Fora', 'Minas Gerais');
insert into cidade (nome_cidade, nome_estado) values ('Uberlândia', 'Minas Gerais');
insert into cidade (nome_cidade, nome_estado) values ('São Paulo', 'São Paulo');
insert into cidade (nome_cidade, nome_estado) values ('Campinas', 'São Paulo');
insert into cidade (nome_cidade, nome_estado) values ('Ribeirão Preto', 'São Paulo');

/
create table estado(
  id serial,
  nome varchar(60) not null unique,
  primary key (id)
);
/
insert into estado (nome)
select distinct nome_estado from cidade;
/
alter table cidade add column estado_id integer not null;
/
update cidade c set c.estado_id = (select e.id from estado e where e.nome = c.nome_estado);
/
alter table cidade 
add constraint fk_cidade_estado
foreign key (estado_id) references estado (id);
/
alter table cidade drop column nome_estado;
/
alter table cidade rename column nome_cidade to nome;


update cidade c set estado_id = (select e.id from estado e where e.nome = c.nome_estado);

