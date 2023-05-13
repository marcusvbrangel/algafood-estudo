
create table usuario_grupo (
  usuario_id bigint,
  grupo_id bigint
);

alter table usuario_grupo add constraint pk_usuario_grupo primary key (usuario_id, grupo_id);

alter table usuario_grupo add constraint fk_usuario foreign key (usuario_id) references usuario;

alter table usuario_grupo add constraint fk_grupo foreign key (grupo_id) references grupo;
