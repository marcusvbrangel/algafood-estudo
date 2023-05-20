
create table restaurante (
  id bigserial,
  nome varchar(50),
  taxa_frete numeric(38,2),
  data_cadastro timestamp(6),
  data_atualizacao timestamp(6),
  cozinha_id bigint,
  endereco_logradouro varchar(60),
  endereco_complemento varchar(60),
  endereco_bairro varchar(60),
  endereco_cep varchar(20),
  endereco_numero varchar(20),
  endereco_cidade_id bigint
);

alter table restaurante add constraint pk_restaurante primary key (id);

create index idx_restaurante_nome on restaurante (nome);

alter table restaurante add constraint uk_restaurante_nome unique (nome);

alter table restaurante add constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha;
alter table restaurante add constraint fk_restaurante_endereco_cidade foreign key (endereco_cidade_id) references cidade;

alter table restaurante alter column nome set not null;
alter table restaurante alter column taxa_frete set not null;
alter table restaurante alter column data_cadastro set not null;
alter table restaurante alter column data_atualizacao set not null;
alter table restaurante alter column cozinha_id set not null;
