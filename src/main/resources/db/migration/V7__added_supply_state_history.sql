CREATE TABLE supply_state_history(
    id BIGSERIAL PRIMARY KEY,
    status varchar(64),
    time timestamp,
    supply_id bigint,
    CONSTRAINT FK_SUPPLY FOREIGN KEY (supply_id) REFERENCES supply(id)
)