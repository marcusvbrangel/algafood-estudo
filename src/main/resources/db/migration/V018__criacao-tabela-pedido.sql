
create table pedido (
  id bigserial,
  sub_total numeric(38,2),
  taxa_frete numeric(38,2),
  valor_total numeric(38,2),
  data_criacao timestamp(6),
  data_confirmacao timestamp(6),
  data_cancelamento timestamp(6),
  status varchar(10),
  data_entrega timestamp(6),
  endereco_bairro varchar(255),
  endereco_cep varchar(255),
  endereco_complemento varchar(255),
  endereco_logradouro varchar(255),
  endereco_numero varchar(255),
  cliente_id bigint,
  endereco_cidade_id bigint,
  forma_pagamento_id bigint,
  restaurante_id bigint
);

alter table pedido add constraint pk_pedido primary key (id);

alter table pedido add constraint fk_pedido_cliente foreign key (cliente_id) references usuario;
alter table pedido add constraint fk_pedido_endereco_cidade foreign key (endereco_cidade_id) references cidade;
alter table pedido add constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento;
alter table pedido add constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante;

alter table pedido alter column sub_total set not null;
alter table pedido alter column taxa_frete set not null;
alter table pedido alter column valor_total set not null;
alter table pedido alter column data_criacao set not null;
alter table pedido alter column status set not null;
alter table pedido alter column cliente_id set not null;
alter table pedido alter column endereco_cidade_id set not null;
alter table pedido alter column forma_pagamento_id set not null;
alter table pedido alter column restaurante_id set not null;
