
create table estado(
  id serial,
  nome varchar(60)
);

alter table estado add constraint pk_estado primary key (id);

alter table estado alter column nome set not null;

alter table estado add constraint uk_estado_nome unique (nome);

create index idx_estado_nome on estado (nome);
