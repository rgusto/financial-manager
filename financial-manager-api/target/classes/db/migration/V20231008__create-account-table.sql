CREATE TABLE bank_account (
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(100) NOT NULL,
    balance NUMERIC NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

ALTER TABLE bank_account ADD CONSTRAINT fk_bank_account_user_01 FOREIGN KEY (user_id) REFERENCES users (id);
CREATE INDEX ix_bank_account_01 ON bank_account (user_id);

