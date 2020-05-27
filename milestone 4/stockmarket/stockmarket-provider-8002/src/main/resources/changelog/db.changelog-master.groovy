package changelog

databaseChangeLog() {
	// release 0.0.1, default initialize cloudDB01 DB.
    include(file: 'release001/groovy/main-1.0.groovy', context: 'test', relativeToChangelogFile: true)

	// not recommend below way
	// includeAll(path: 'snapshot21021/groovy/', context: 'production', relativeToChangelogFile: true)
}