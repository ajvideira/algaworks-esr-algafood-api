INSERT INTO cozinha (nome) VALUES ('Tailandesa');
INSERT INTO cozinha (nome) VALUES ('Indiana');

INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Wok Thai Food', 9.90, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Tuk tuk', 13.50, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Sharin', 7.30, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Namaste', 0, 2);

INSERT INTO forma_pagamento (descricao) VALUES ('Débito');
INSERT INTO forma_pagamento (descricao) VALUES ('Crédito');

INSERT INTO estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO estado (nome) VALUES ('Mato Grosso');
INSERT INTO estado (nome) VALUES ('São Paulo');
INSERT INTO estado (nome) VALUES ('Amazonas');
INSERT INTO estado (nome) VALUES ('Paraná');
INSERT INTO estado (nome) VALUES ('Santa Catarina');

INSERT INTO cidade (nome, estado_id) VALUES ('São Paulo', 3);
INSERT INTO cidade (nome, estado_id) VALUES ('Manaus', 4);
INSERT INTO cidade (nome, estado_id) VALUES ('Curitiba', 5);

INSERT INTO permissao (nome, descricao) VALUES ('ROLE_ADMIN', 'Permissão de admin no sistema.');
INSERT INTO permissao (nome, descricao) VALUES ('ROLE_OPERACIONAL', 'Permissão de tarefas operacionais.');