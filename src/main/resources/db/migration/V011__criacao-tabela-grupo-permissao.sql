
create table grupo_permissao (
  grupo_id bigint,
  permissao_id bigint
);

alter table grupo_permissao add constraint pk_grupo_permissao primary key (grupo_id, permissao_id);

alter table grupo_permissao add constraint fk_permissao foreign key (permissao_id) references permissao;

alter table grupo_permissao add constraint fk_grupo foreign key (grupo_id) references grupo;
