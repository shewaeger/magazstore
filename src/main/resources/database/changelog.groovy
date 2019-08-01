databaseChangeLog() {
    changeSet(id: 'create_table_actions', author: 'shewaeger') {
        createTable(tableName: 'actions') {
            column(name: 'id', type: 'bigint', autoIncrement: true) {
                constraints(primaryKey: true, nullable: false)
            }
            column(name: 'action', type: 'varchar') {
                constraints(nullable: false, unique: true, uniqueConstraintName: 'unique_action_value')
            }
        }
        rollback {
            dropTable(tableName: 'actions')
        }
    }

    changeSet(id: 'create_table_users', author: 'shewaeger') {
        createTable(tableName: 'users'){
            column(name: 'id', type: 'bigint', autoIncrement: true){
                constraints([primaryKey:true])
            }
            column(name: 'login', type: 'varchar' ){
                constraints(unique: true, uniqueConstraintName: "unique_user_login", nullable: false)
            }
            column(name: 'password', type: 'varchar'){
                constraints(nullable: false)
            }
            column(name: 'date_create', type: 'datetime') {
                constraints(nullable: false)
            }
            column(name: 'date_update', type: 'datetime') {
                constraints(nullable: false)
            }
            column(name: 'email', type: 'varchar') {
                constraints(nullable: false, unique: true, uniqueConstraintName: 'unique_user_email')
            }
        }

        createTable(tableName: 'users_actions') {
            column(name: 'id_user', type: 'bigint') {
                constraints(references: 'users(id)', foreignKeyName: 'fk_users_actions')
            }
            column(name: 'id_action', type: 'bigint') {
                constraints(references: 'actions(id)', foreignKeyName: 'fk_actions_users')
            }
        }

        rollback {
            delete(tableName: 'users_actions')
            dropTable(tableName: 'users_actions')
            dropTable(tableName: 'users')
        }
    }

    changeSet(id: 'create_table_attachments', author: 'shewaeger') {
        createTable(tableName: 'attachments') {
            column(name: 'id', type: 'bigint', autoIncrement: true) {
                constraints(primaryKey: true, nullable: false)
            }

            column(name: 'mime_type', type: 'varchar')

            column(name: 'hash', type: 'varchar') {
                constraints(nullable: false, unique: true, uniqueConstraintName: 'unique_attachments_hash')
            }

            column(name: 'id_owner', type: 'bigint') {
                constraints(nullable: false, references: 'users(id)', foreignKeyName: 'fk_attachments_owner')
            }

            column(name: 'date_create', type: 'datetime') {
                constraints nullable: false
            }

            column(name: 'date_update', type: 'datetime') {
                constraints nullable: false
            }
        }

        rollback {
            delete(tableName: 'attachments')
            dropTable(tableName: 'attachments')
        }
    }

    changeSet(id: 'add_avatar_to_users_table', author: 'shewaeger') {
        addColumn(tableName: 'users') {
            column(name: 'id_avatar', type: 'bigint') {
                constraints(references: 'attachments(id)', foreignKeyName: 'fk_users_attachments')
            }
        }
        rollback {
            dropColumn(tableName: 'users', columnName: 'id_attachment')
        }
    }

    changeSet(id: 'create_table_categories', author: 'shewaeger') {
        createTable(tableName: 'categories') {
            column(name: 'id', type: 'bigint', autoIncrement: true) {
                constraints(primaryKey: true, nullable: false)
            }
            column(name: 'name', type: 'varchar') {
                constraints(nullable: false)
            }
            column(name: 'id_parent', type: 'bigint') {
                constraints(references: 'categories(id)', foreignKeyName: 'fk_categories_tree')
            }
            column(name: 'date_create', type: 'datetime') {
                constraints(nullable: false)
            }
            column(name: 'date_update', type: 'datetime') {
                constraints(nullable: false);
            }
        }
        rollback {
            dropTable(tableName: 'categories')
        }
    }

    changeSet(id: 'create_table_products', author: 'shewaeger') {
        createTable(tableName: 'products') {
            column(name: 'id', type: 'bigint', autoIncrement: true) {
                constraints primaryKey: true, nullable: false
            }
            column(name: 'name', type: 'varchar') {
                constraints(nullable: false)
            }
            column(name: 'description', type: 'varchar')

            column(name: 'id_parent', type: 'bigint') {
                constraints(references: 'categories(id)', foreignKeyName: 'fk_categories_tree')
            }
            column(name: 'date_create', type: 'datetime') {
                constraints(nullable: false)
            }
            column(name: 'date_update', type: 'datetime') {
                constraints nullable: false
            }
        }

        createTable(tableName: 'products_attachments') {
            column(name: 'id_product', type: 'bigint') {
                constraints(references: 'products(id)', foreignKeyName: "fk_product_attachment")
            }
            column(name: 'id_attachment', type: 'bigint') {
                constraints(references: 'attachments(id)', foreignKeyName: 'fk_attachments_products')
            }
        }
        rollback {
            dropTable tableName: 'products_attachments'
            dropTable tableName: 'products'
        }
    }
}