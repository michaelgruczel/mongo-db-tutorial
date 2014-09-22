# Solution for test in chapter 2

There are several solutions to do this one could be:

	use company

	var anEmployee = {
               name: { first: 'Homer', last: 'Simpson' },
               birth: new Date('Jun 12, 1960'),
               position: 'security',
               salary : NumberLong(40000)
            }

	db.employees.insert( anEmployee )
	db.books.find({'name.last' : 'Simpson'}).pretty()