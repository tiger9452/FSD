package changelog.release001.groovy

databaseChangeLog(logicalFilePath: 'main-1.0.groovy') {

    changeSet(id: 'initial_common_DB_v001', author: 'sam', context: "test") {

        preConditions(onFail: 'HALT') {
            sqlCheck(expectedResult: '1') {
                "select count(1) from information_schema.schemata where schema_name='cloudDB01'"
            }
        }
        sqlFile(path: '../sql/update/table-1.0.sql', stripComments: true, relativeToChangelogFile: true)
        sqlFile(path: '../sql/update/data.init-1.0.sql', stripComments: true, relativeToChangelogFile: true)

        rollback {
            sqlFile(path: '../sql/rollback/db.changelog-1.0.sql', stripComments: true, relativeToChangelogFile: true)
        }
    }
}
