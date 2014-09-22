# Solution for first test in chapter 3

db.books.find({pages: { $lt: 10},  year: { $lt: 2012}}).sort({year: -1, pages: 1})