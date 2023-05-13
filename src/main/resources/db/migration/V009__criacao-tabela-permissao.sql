
create table permissao (
  id bigserial,
  nome varchar(60),
  descricao varchar(200)
);

alter table permissao add constraint pk_permissao primary key (id);

alter table permissao alter column nome set not null;

alter table permissao add constraint uk_permissao_nome unique (nome);

create index idx_permissao_nome on permissao (nome);
