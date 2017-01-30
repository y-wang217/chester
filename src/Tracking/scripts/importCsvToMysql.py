#!/usr/bin/python

import MySQLdb

# Open database connection
db = MySQLdb.connect("localhost","user","user1`","tracking" )

# prepare a cursor object using cursor() method
cursor = db.cursor()

# Prepare SQL query to INSERT a record into the database.

sql = "CREATE TABLE {0} LIKE {1}; INSERT {2} SELECT * FROM {3};".format('backup', 'bols', 'backup', 'bols')

try:
    # Execute the SQL command
    cursor.execute(sql)
    # Commit your changes in the database
    db.commit()
except:
    # Rollback in case there is any error
    db.rollback()

# disconnect from server
db.close()