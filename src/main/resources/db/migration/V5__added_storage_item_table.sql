CREATE TABLE storage_item (
    id BIGSERIAL PRIMARY KEY,
    is_hidden BOOLEAN,
    price float8,
    description text,
    quantity bigint,
    product_id bigint,
    organization_branch_id bigint,
    CONSTRAINT FK_PRODUCT FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT FK_ORGANIZATION_BRANCH FOREIGN KEY (organization_branch_id) REFERENCES  organization_branch(id)
)