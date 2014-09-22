# Solution for second test in chapter 3

	var aCursor = db.books.find()

	while ( aCursor.hasNext() ) {
		var book = aCursor.next();
  		if (book.pages > 100) {
			db.boring.insert(book);
		}
	}