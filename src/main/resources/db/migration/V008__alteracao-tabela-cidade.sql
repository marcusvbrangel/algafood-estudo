
alter table cidade drop column nome_estado;

alter table cidade rename column nome_cidade to nome;

alter table cidade add constraint fk_cidade_estado foreign key (estado_id) references estado (id);
