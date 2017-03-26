INSERT INTO permissao VALUES (3, 'ROLE_CADASTRAR_CERVEJA');
INSERT INTO permissao VALUES (4, 'ROLE_ALTERAR_EXCLUIR_CERVEJA');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 3);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 4);