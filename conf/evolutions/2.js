// A simple test evolution

// --- !Ups
db.users.createIndex({"name" : 1})

// --- !Downs
db.users.dropIndex("name_1")
