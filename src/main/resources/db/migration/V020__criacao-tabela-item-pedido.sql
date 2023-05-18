
create table item_pedido(
  pedido_id bigint,
  produto_id bigint,
  quantidade integer,
  preco_unitario numeric(38, 2),
  preco_total numeric(38, 2),
  observacao varchar(120)
);

alter table item_pedido add constraint pk_item_pedido primary key (pedido_id, produto_id);

alter table item_pedido add constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido;

alter table item_pedido add constraint fk_item_pedido_produto foreign key (produto_id) references produto;

alter table item_pedido alter column quantidade set not null;
alter table item_pedido alter column preco_unitario set not null;
alter table item_pedido alter column preco_total set not null;


