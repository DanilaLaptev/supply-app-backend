CREATE TABLE supply (
    id BIGSERIAL PRIMARY KEY,
    price float8,
    quantity bigint,
    created timestamp,
    group_id bigint,
    delivery_time timestamp,
    from_storage_item_id bigint,
    to_storage_item_id bigint,
    CONSTRAINT FK_FROM_ORGANIZATION_BRANCH FOREIGN KEY (from_storage_item_id) references storage_item(id),
    CONSTRAINT FK_TO_ORGANIZATION_BRANCH FOREIGN KEY (to_storage_item_id) REFERENCES storage_item(id)
)