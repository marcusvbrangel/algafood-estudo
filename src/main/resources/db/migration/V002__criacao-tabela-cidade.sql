create table cidade(
  id bigserial,
  nome_cidade varchar(60),
  nome_estado varchar(60)
);

alter table cidade add constraint pk_cidade primary key (id);

alter table cidade alter column nome_cidade set not null;
alter table cidade add constraint uk_nome_cidade unique (nome_cidade);

alter table cidade alter column nome_estado set not null;

create index idx_nome_cidade on cidade (nome_cidade);
