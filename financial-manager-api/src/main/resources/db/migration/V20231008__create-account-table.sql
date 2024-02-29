CREATE TABLE accounts (
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(100) NOT NULL,
    balance MONEY NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

ALTER TABLE accounts ADD CONSTRAINT fk_accounts_user_01 FOREIGN KEY (user_id) REFERENCES users (id);
CREATE INDEX ix_accounts_01 ON accounts (user_id);

