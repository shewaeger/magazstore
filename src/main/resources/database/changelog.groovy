databaseChangeLog() {

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
        }
        rollback {
            dropTable(tableName: 'users')
        }
    }
}