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
            dropTable(tableName: 'users_actions')
            dropTable(tableName: 'users')
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
        }
        rollback {
            dropTable tableName: 'products'
        }
    }
}