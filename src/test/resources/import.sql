INSERT INTO estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO estado (nome) VALUES ('Mato Grosso');
INSERT INTO estado (nome) VALUES ('São Paulo');
INSERT INTO estado (nome) VALUES ('Amazonas');
INSERT INTO estado (nome) VALUES ('Paraná');
INSERT INTO estado (nome) VALUES ('Santa Catarina');
INSERT INTO estado (nome) VALUES ('Rio Grande do Sul');

INSERT INTO cidade (nome, estado_id) VALUES ('São Paulo', 3);
INSERT INTO cidade (nome, estado_id) VALUES ('Manaus', 4);
INSERT INTO cidade (nome, estado_id) VALUES ('Curitiba', 5);
INSERT INTO cidade (nome, estado_id) VALUES ('Porto Alegre', 7);

INSERT INTO cozinha (nome) VALUES ('Tailandesa');
INSERT INTO cozinha (nome) VALUES ('Indiana');
INSERT INTO cozinha (nome) VALUES ('Italiana');

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id, data_cadastro, data_atualizacao) VALUES ('Wok Thai Food', 9.90, 1, '90040001', 'Avenida João Pessoa', '1784', '1601', 'Farroupilha', 4, now(), now());
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id, data_cadastro, data_atualizacao) VALUES ('Tuk tuk', 13.50, 1, '91898909', 'Rua João Manoel', '1243', '76', 'Centro', 2, now(), now());
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id, data_cadastro, data_atualizacao) VALUES ('Sharin', 7.30, 2, '99098678', 'Rua República', '87', '1', 'Menino Deus', 1, now(), now());
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id, data_cadastro, data_atualizacao) VALUES ('Namaste', 0, 2, '98765678', 'Avenida Ipiranga', '55', '814', 'Praia de Belas', 4, now(), now());

INSERT INTO forma_pagamento (descricao) VALUES ('Débito');
INSERT INTO forma_pagamento (descricao) VALUES ('Crédito');
INSERT INTO forma_pagamento (descricao) VALUES ('Pix');

INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (4, 1);



INSERT INTO permissao (nome, descricao) VALUES ('ROLE_ADMIN', 'Permissão de admin no sistema.');
INSERT INTO permissao (nome, descricao) VALUES ('ROLE_OPERACIONAL', 'Permissão de tarefas operacionais.');