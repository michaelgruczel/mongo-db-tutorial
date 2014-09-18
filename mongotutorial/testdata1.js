function insertData(dbName, colName, num, aYear) {
  var col = db.getSiblingDB(dbName).getCollection(colName);
  for (i = 0; i < num; i++) {
    col.insert({name : 'yellow pages', year : aYear, authors: 'unknown', pages: i});
  }
  print(col.count());
}
insertData('myBookStore', 'books', 400, 2010);
insertData('myBookStore', 'books', 300, 2011);
insertData('myBookStore', 'books', 700, 2012);
insertData('myBookStore', 'books', 2, 2013);