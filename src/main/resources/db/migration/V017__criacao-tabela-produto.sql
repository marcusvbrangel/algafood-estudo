
create table produto (
  id bigserial,
  nome varchar(60),
  descricao varchar(120),
  preco numeric(38,2),
  ativo boolean,
  restaurante_id bigint
);

alter table produto add constraint pk_produto primary key (id);

alter table produto add constraint uk_produto_nome unique (nome);

create index idx_produto_nome on produto (nome);

alter table produto add constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante;

alter table produto alter column nome set not null;
alter table produto alter column preco set not null;
alter table produto alter column restaurante_id set not null;
