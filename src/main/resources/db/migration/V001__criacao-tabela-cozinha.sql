
create table cozinha(
  id bigserial,
  nome varchar(60)
);

alter table cozinha add constraint pk_cozinha primary key (id);

alter table cozinha alter column nome set not null;

alter table cozinha add constraint uk_cozinha_nome unique (nome);

create index idx_cozinha_nome on cozinha (nome);


