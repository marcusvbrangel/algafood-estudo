
update cidade c set estado_id = (select e.id from estado e where e.nome = c.nome_estado);
