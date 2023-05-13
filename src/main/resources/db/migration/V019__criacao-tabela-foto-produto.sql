
create table foto_produto (
  id bigserial,
  nome_arquivo varchar(60),
  descricao varchar(120),
  content_type varchar(60),
  tamanho float4,
  produto_id bigint
);

alter table foto_produto add constraint pk_foto_produto primary key (id);

alter table foto_produto add constraint uk_nome_arquivo unique (nome_arquivo);

alter table foto_produto add constraint fk_produto foreign key (produto_id) references produto;

alter table foto_produto alter column nome_arquivo set not null;
alter table foto_produto alter column content_type set not null;
alter table foto_produto alter column tamanho set not null;
alter table foto_produto alter column produto_id set not null;

create index idx_nome_arquivo on foto_produto (nome_arquivo);
