
create table forma_pagamento (
  id bigserial,
  descricao varchar(60)
);

alter table forma_pagamento add constraint pk_forma_pagamento primary key (id);

alter table forma_pagamento alter column descricao set not null;

alter table forma_pagamento add constraint uk_forma_pagamento_descricao unique (descricao);

create index idx_forma_pagamento_descricao on forma_pagamento (descricao);
