
create table restaurante_forma_pagamento (
  restaurante_id bigint,
  forma_pagamento_id bigint
);

alter table restaurante_forma_pagamento add constraint pk_restaurante_forma_pagamento primary key (restaurante_id, forma_pagamento_id);

alter table restaurante_forma_pagamento add constraint fk_restaurante_forma_pagamento_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento;

alter table restaurante_forma_pagamento add constraint fk_restaurante_forma_pagamento_restaurante foreign key (restaurante_id) references restaurante;
