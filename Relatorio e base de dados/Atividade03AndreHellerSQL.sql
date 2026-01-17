

-- Cria banco 
CREATE SCHEMA IF NOT EXISTS `sistemaescolainformatica` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `sistemaescolainformatica` ;

-- Table aluno
CREATE TABLE IF NOT EXISTS `sistemaescolainformatica`.`aluno` (
  `id_aluno` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL DEFAULT NULL,
  `cpf` VARCHAR(14) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `telefone` VARCHAR(15) NULL DEFAULT NULL,
  `status` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_aluno`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- Table professor
CREATE TABLE IF NOT EXISTS `sistemaescolainformatica`.`professor` (
  `id_professor` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL DEFAULT NULL,
  `disciplina` VARCHAR(100) NULL DEFAULT NULL,
  `horario_disponivel` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id_professor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- Table curso
CREATE TABLE IF NOT EXISTS `sistemaescolainformatica`.`curso` (
  `id_curso` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL DEFAULT NULL,
  `descricao` TEXT NULL DEFAULT NULL,
  `carga_horaria` INT NULL DEFAULT NULL,
  `valor` DECIMAL(10,2) NULL DEFAULT NULL,
  `data_inicio` DATE NULL DEFAULT NULL,
  `data_termino` DATE NULL DEFAULT NULL,
  `id_professor` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_curso`),
  INDEX `id_professor` (`id_professor` ASC) VISIBLE,
  CONSTRAINT `curso_ibfk_1`
    FOREIGN KEY (`id_professor`)
    REFERENCES `sistemaescolainformatica`.`professor` (`id_professor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- Table matricula
CREATE TABLE IF NOT EXISTS `sistemaescolainformatica`.`matricula` (
  `id_matricula` INT NOT NULL AUTO_INCREMENT,
  `id_aluno` INT NULL DEFAULT NULL,
  `id_curso` INT NULL DEFAULT NULL,
  `data_matricula` DATE NULL DEFAULT NULL,
  `status` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_matricula`),
  INDEX `id_aluno` (`id_aluno` ASC) VISIBLE,
  INDEX `id_curso` (`id_curso` ASC) VISIBLE,
  CONSTRAINT `matricula_ibfk_1`
    FOREIGN KEY (`id_aluno`)
    REFERENCES `sistemaescolainformatica`.`aluno` (`id_aluno`),
  CONSTRAINT `matricula_ibfk_2`
    FOREIGN KEY (`id_curso`)
    REFERENCES `sistemaescolainformatica`.`curso` (`id_curso`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- Table certificado
CREATE TABLE IF NOT EXISTS `sistemaescolainformatica`.`certificado` (
  `id_certificado` INT NOT NULL AUTO_INCREMENT,
  `id_matricula` INT NULL DEFAULT NULL,
  `data_emissao` DATE NULL DEFAULT NULL,
  `codigo_verificacao` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id_certificado`),
  INDEX `id_matricula` (`id_matricula` ASC) VISIBLE,
  CONSTRAINT `certificado_ibfk_1`
    FOREIGN KEY (`id_matricula`)
    REFERENCES `sistemaescolainformatica`.`matricula` (`id_matricula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- Table comunicado
CREATE TABLE IF NOT EXISTS `sistemaescolainformatica`.`comunicado` (
  `id_comunicado` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NULL DEFAULT NULL,
  `mensagem` TEXT NULL DEFAULT NULL,
  `data_envio` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id_comunicado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- Table pagamento
CREATE TABLE IF NOT EXISTS `sistemaescolainformatica`.`pagamento` (
  `id_pagamento` INT NOT NULL AUTO_INCREMENT,
  `id_matricula` INT NULL DEFAULT NULL,
  `valor` DECIMAL(10,2) NULL DEFAULT NULL,
  `data_pagamento` DATE NULL DEFAULT NULL,
  `status` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id_pagamento`),
  INDEX `id_matricula` (`id_matricula` ASC) VISIBLE,
  CONSTRAINT `pagamento_ibfk_1`
    FOREIGN KEY (`id_matricula`)
    REFERENCES `sistemaescolainformatica`.`matricula` (`id_matricula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- Table usuario
CREATE TABLE IF NOT EXISTS `sistemaescolainformatica`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `senha` VARCHAR(255) NULL DEFAULT NULL,
  `perfil` ENUM('Administrador', 'Professor', 'Aluno') NULL DEFAULT NULL,
  `aluno_id_aluno` INT NOT NULL,
  `professor_id_professor` INT NOT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `fk_usuario_aluno1_idx` (`aluno_id_aluno` ASC) VISIBLE,
  INDEX `fk_usuario_professor1_idx` (`professor_id_professor` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_aluno1`
    FOREIGN KEY (`aluno_id_aluno`)
    REFERENCES `sistemaescolainformatica`.`aluno` (`id_aluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_professor1`
    FOREIGN KEY (`professor_id_professor`)
    REFERENCES `sistemaescolainformatica`.`professor` (`id_professor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- Table usuario_has_comunicado
CREATE TABLE IF NOT EXISTS `sistemaescolainformatica`.`usuario_has_comunicado` (
  `usuario_id_usuario` INT NOT NULL,
  `comunicado_id_comunicado` INT NOT NULL,
  PRIMARY KEY (`usuario_id_usuario`, `comunicado_id_comunicado`),
  INDEX `fk_usuario_has_comunicado_comunicado1_idx` (`comunicado_id_comunicado` ASC) VISIBLE,
  INDEX `fk_usuario_has_comunicado_usuario1_idx` (`usuario_id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_has_comunicado_usuario1`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `sistemaescolainformatica`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_comunicado_comunicado1`
    FOREIGN KEY (`comunicado_id_comunicado`)
    REFERENCES `sistemaescolainformatica`.`comunicado` (`id_comunicado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Inserts na tabela aluno
INSERT INTO aluno (nome, cpf, email, telefone, status) VALUES
('Lucas Silva', '123.456.789-00', 'lucas.silva@example.com', '(11)91234-5678', 'Ativo'),
('Mariana Souza', '987.654.321-00', 'mariana.souza@example.com', '(21)99876-5432', 'Ativo'),
('Pedro Henrique', '321.654.987-11', 'pedro.henrique@example.com', '(31)91234-1234', 'Ativo'),
('Ana Clara', '456.789.123-22', 'ana.clara@example.com', '(41)99876-5678', 'Inativo'),
('Bruno Costa', '789.123.456-33', 'bruno.costa@example.com', '(51)98765-4321', 'Ativo');


-- =====================================
-- Inserts na tabela professor
INSERT INTO professor (nome, disciplina, horario_disponivel, email) VALUES
('Carlos Alberto', 'Lógica de Programação', 'Segunda e Quarta - 14h às 16h', 'carlos.alberto@example.com'),
('Fernanda Lima', 'Banco de Dados', 'Terça e Quinta - 10h às 12h', 'fernanda.lima@example.com'),
('Juliana Paiva', 'Estrutura de Dados', 'Segunda e Sexta - 08h às 10h', 'juliana.paiva@example.com'),
('Rafael Dias', 'Programação Web', 'Terça e Quinta - 19h às 21h', 'rafael.dias@example.com'),
('Patrícia Gomes', 'Segurança da Informação', 'Quarta - 18h às 20h', 'patricia.gomes@example.com');

-- =====================================
-- Inserts na tabela curso
INSERT INTO curso (nome, descricao, carga_horaria, valor, data_inicio, data_termino, id_professor) VALUES
('Introdução à Programação', 'Curso básico de lógica e algoritmos.', 40, 500.00, '2025-06-01', '2025-07-01', 1),
('Banco de Dados MySQL', 'Curso intermediário sobre modelagem e consultas.', 60, 750.00, '2025-06-10', '2025-08-10', 2),
('Estruturas Avançadas', 'Curso sobre estruturas de dados complexas.', 50, 600.00, '2025-07-05', '2025-08-15', 3),
('Desenvolvimento Web', 'HTML, CSS e JavaScript na prática.', 45, 550.00, '2025-06-20', '2025-08-20', 4),
('Segurança Digital', 'Boas práticas em segurança da informação.', 30, 400.00, '2025-07-15', '2025-08-30', 5);

-- =====================================
-- Inserts na tabela matricula
INSERT INTO matricula (id_aluno, id_curso, data_matricula, status) VALUES
(1, 1, '2025-05-20', 'Matriculado'),
(2, 2, '2025-05-21', 'Matriculado'),
(3, 3, '2025-05-22', 'Matriculado'),
(4, 4, '2025-05-23', 'Cancelado'),
(5, 5, '2025-05-24', 'Matriculado');

-- =====================================
-- Inserts na tabela certificado
INSERT INTO certificado (id_matricula, data_emissao, codigo_verificacao) VALUES
(1, '2025-08-01', 'ABC123DEF'),
(2, '2025-08-15', 'XYZ456GHI'),
(3, '2025-08-20', 'LMN789OPQ'),
(4, '2025-09-01', 'JKL012RST'),
(5, '2025-09-10', 'UVW345XYZ');

-- =====================================
-- Inserts na tabela comunicado
INSERT INTO comunicado (titulo, mensagem, data_envio) VALUES
('Bem-vindo ao curso!', 'Estamos felizes por tê-lo conosco.', '2025-05-15'),
('Aviso de Feriado', 'Não haverá aula no dia 12/06.', '2025-06-05'),
('Entrega de Certificados', 'A entrega ocorrerá no dia 01/09.', '2025-08-25'),
('Nova Turma', 'Abrimos uma nova turma para o curso de Web.', '2025-06-10'),
('Atualização de Sistema', 'O sistema ficará fora do ar dia 20/06.', '2025-06-18');

-- =====================================
-- Inserts na tabela pagamento
INSERT INTO pagamento (id_matricula, valor, data_pagamento, status) VALUES
(1, 500.00, '2025-05-25', 'Pago'),
(2, 750.00, '2025-05-26', 'Pago'),
(3, 600.00, '2025-05-27', 'Pendente'),
(4, 550.00, '2025-05-28', 'Cancelado'),
(5, 400.00, '2025-05-29', 'Pago');

-- =====================================
ALTER TABLE usuario
MODIFY COLUMN aluno_id_aluno INT NULL;

ALTER TABLE usuario
MODIFY COLUMN professor_id_professor INT NULL;

-- Inserts na tabela usuario
INSERT INTO usuario (nome, email, senha, perfil, aluno_id_aluno, professor_id_professor) VALUES
('Lucas Silva', 'lucas.silva@example.com', 'senha123', 'Aluno', 1, NULL),
('Mariana Souza', 'mariana.souza@example.com', 'senha321', 'Aluno', 2, NULL),
('Pedro Henrique', 'pedro.henrique@example.com', 'senha456', 'Aluno', 3, NULL),
('Carlos Alberto', 'carlos.alberto@example.com', 'senha789', 'Professor', NULL, 1),
('Fernanda Lima', 'fernanda.lima@example.com', 'senha987', 'Professor', NULL, 2);


-- =====================================
-- Inserts na tabela usuario_has_comunicado
INSERT INTO usuario_has_comunicado (usuario_id_usuario, comunicado_id_comunicado) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5);

-- =====================================
-- SELECTS - Consultas importantes

-- Alunos
SELECT * FROM aluno;

-- Professores
SELECT * FROM professor;

-- Cursos e seus Professores
SELECT c.nome AS Curso, p.nome AS Professor
FROM curso c
JOIN professor p ON c.id_professor = p.id_professor;

-- Matrículas com status
SELECT a.nome AS Aluno, cu.nome AS Curso, m.status
FROM matricula m
JOIN aluno a ON m.id_aluno = a.id_aluno
JOIN curso cu ON m.id_curso = cu.id_curso;

-- Pagamentos efetuados
SELECT p.id_pagamento, a.nome AS Aluno, p.valor, p.status
FROM pagamento p
JOIN matricula m ON p.id_matricula = m.id_matricula
JOIN aluno a ON m.id_aluno = a.id_aluno;

-- Certificados
SELECT c.id_certificado, a.nome AS Aluno, c.codigo_verificacao
FROM certificado c
JOIN matricula m ON c.id_matricula = m.id_matricula
JOIN aluno a ON m.id_aluno = a.id_aluno;

-- Buscar certificado com código de verificação específico
SELECT * FROM certificado WHERE codigo_verificacao = 'ABC123DEF';

-- Buscar comunicado enviado após uma data
SELECT * FROM comunicado WHERE data_envio > '2025-06-01';

-- Buscar pagamentos com status 'Pendente'
SELECT * FROM pagamento WHERE status = 'Pendente';

-- =====================================
-- UPDATE 
select * from matricula;

UPDATE matricula
SET status = 'Concluído'
WHERE id_matricula = 4;

select * from matricula;

select * from usuario;

UPDATE usuario
SET nome = 'Enzo Gabriel'
WHERE id_usuario = 1;

select * from usuario;


-- =====================================
-- DELETE 
select * from comunicado;
DELETE FROM usuario_has_comunicado WHERE comunicado_id_comunicado = 4;
DELETE FROM comunicado WHERE id_comunicado = 4;
select * from comunicado;

select * from certificado;
DELETE FROM certificado WHERE id_matricula = 1;
select * from certificado;




