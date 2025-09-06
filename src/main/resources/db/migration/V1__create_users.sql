-- garante que extensão uuid esteja ativa
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- remove a tabela antiga se existir
DROP TABLE IF EXISTS users CASCADE;

-- recria a tabela já com UUID como PK
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);
