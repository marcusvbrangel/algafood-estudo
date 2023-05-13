
create table grupo (
  id bigserial,
  nome varchar(60)
);

alter table grupo add constraint pk_grupo primary key (id);

alter table grupo alter column nome set not null;

alter table grupo add constraint uk_grupo_nome unique (nome);

create index idx_grupo_nome on grupo (nome);
