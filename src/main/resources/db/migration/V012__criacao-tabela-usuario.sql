
create table usuario (
  id bigserial,
  nome varchar(60),
  email varchar(60),
  senha varchar(20),
  data_cadastro timestamp(6),
  data_atualizacao timestamp(6)
);

alter table usuario add constraint pk_usuario primary key (id);

create index idx_usuario_nome on usuario (nome);

alter table usuario add constraint uk_usuario_email unique (email);

alter table usuario alter column nome set not null;
alter table usuario alter column email set not null;
alter table usuario alter column senha set not null;
alter table usuario alter column data_cadastro set not null;
alter table usuario alter column data_atualizacao set not null;

