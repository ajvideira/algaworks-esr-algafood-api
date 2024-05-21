INSERT INTO cozinha (nome) VALUES ('Tailandesa');
INSERT INTO cozinha (nome) VALUES ('Indiana');

INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Wok Thai Food', 9.90, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Tuk tuk', 13.50, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Sharin', 7.30, 2);

INSERT INTO forma_pagamento (descricao) VALUES ('Débito');
INSERT INTO forma_pagamento (descricao) VALUES ('Crédito');

INSERT INTO estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO estado (nome) VALUES ('Mato Grosso');
INSERT INTO estado (nome) VALUES ('Sergipe');
INSERT INTO estado (nome) VALUES ('Acre');

INSERT INTO permissao (nome, descricao) VALUES ('ROLE_ADMIN', 'Permissão de admin no sistema.')
INSERT INTO permissao (nome, descricao) VALUES ('ROLE_OPERACIONAL', 'Permissão de tarefas operacionais.')

INSERT INTO cidade (nome) VALUES ('São Paulo');
INSERT INTO cidade (nome) VALUES ('Manaus');
INSERT INTO cidade (nome) VALUES ('Curitiba');