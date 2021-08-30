
package com.bands.hambands.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.bands.hambands.jsonparser.JsonParser
import es.usj.section_1.adrian.appseptember.Models.Movie

class Sqlitehelper(var context: Context) : SQLiteOpenHelper(context, "movies.db", null, 1) {

    var tableName = "moviedata"

    var rank: String = "rank"
    var title: String = "title"
    var genre: String = "genre"
    var description: String = "description"
    var director: String = "director"
    var actors = "actors"
    var year = "year"
    var runtimeminutes = "runtimeminutes"
    var rating = "rating"
    var votes = "votes"
    var revenuemillions = "revenuemillions"
    var metascore = "metascore"

    lateinit var db: SQLiteDatabase


    //it will called automatically whenever create object or this class.
    //in that method  create a query to create table. which will create table.
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + tableName +" (" +
                rank +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                title + " TEXT," +
                genre + " TEXT," +
                description + " TEXT," +
                director + " TEXT," +
                actors +" TEXT," +
                year + " INT," +
                runtimeminutes + " INT," +
                rating + " DOUBLE," +
                votes + " INT," +
                revenuemillions + " DOUBLE," +
                metascore + " INT)"

        //db.execSQL() method is used to execute a Sqlite query.
        //createTable query and now we are passing this in db.execSQL() method
        db?.execSQL(createTable)

        if (db != null) {
            this.db = db
        }else{
            Toast.makeText(context, "Unexpected Error Occurred.", Toast.LENGTH_SHORT).show()
            return
        }
        val jsonParser = JsonParser(context)
        insertList(jsonParser.readAll())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + tableName)
        onCreate(db)
    }


    //for inserting i have create 2 different method one is for inserting a complete arraylist.
    //and 2nd is for inserting only one record at a time.
    fun insertList(list: ArrayList<Movie>){
        //use loop to get all the values from a arraylist and one by one store them
        //into table.
        for (item in list){
            //for insert i have to convert our model data object to content values
                //same is hapening below.
            var cv = ContentValues()
            //first parameter is the name of the coloum
            //2nd parameter is actually value we need to store.
            cv.put(rank, item.rank)
            cv.put(title, item.title)
            cv.put(genre, item.genre)
            cv.put(description, item.description)
            cv.put(director, item.director)
            cv.put(actors , item.actors)
            cv.put(year, item.year)
            cv.put(runtimeminutes, item.runtimeminutes)
            cv.put(rating, item.rating)
            cv.put(votes, item.votes)
            cv.put(revenuemillions, item.revenuemillions)
            cv.put(metascore, item.metascore)
            try {
                //db is a Sqlite database object and insert is a predefind method which is used to insert data
                    //into table.Pass table name and our content values to insert.
                db.insert(tableName, null, cv)
            }catch (e: Exception){
                Toast.makeText(context, "Error = " + e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    //its the 2nd method which is used for insert only one row at a time.
    fun insertOne(item: Movie){
            var cv = ContentValues()
            cv.put(rank, item.rank)
            cv.put(title, item.title)
            cv.put(genre, item.genre)
            cv.put(description, item.description)
            cv.put(director, item.director)
            cv.put(actors , item.actors)
            cv.put(year, item.year)
            cv.put(runtimeminutes, item.runtimeminutes)
            cv.put(rating, item.rating)
            cv.put(votes, item.votes)
            cv.put(revenuemillions, item.revenuemillions)
            cv.put(metascore, item.metascore)
            try {
                this.writableDatabase.insert(tableName, null, cv)
            }catch (e: Exception){
                Toast.makeText(context, "Error = " + e.message, Toast.LENGTH_SHORT).show()
            }
    }

    //read data is as simple as running a sql query.
    fun readdata() :MutableList<Movie>{
        var list : MutableList<Movie> = ArrayList()

        // create sqlite readable database object names as db.
        val db = this.readableDatabase
        //create a query to get all the data.
        val query = "Select * from " + tableName
        //used rawQuery method to run our query and at this stage the response will be stored in
        //result variable.
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            //now we will get all the data from our response.
            do {
                var itemModel = Movie()
                //itemmodel is model data object.
                //using result.getint or result.getString method both are used to get integer or string
                //value from  reponse.
                // getting values from response and storing them in  itemmodel object.
                itemModel.rank = result.getInt(result.getColumnIndex(rank))
                itemModel.title = result.getString(result.getColumnIndex(title))
                itemModel.genre = result.getString(result.getColumnIndex(genre))
                itemModel.description = result.getString(result.getColumnIndex(description))
                itemModel.director = result.getString(result.getColumnIndex(director))
                itemModel.actors = result.getString(result.getColumnIndex(actors))
                itemModel.year = result.getInt(result.getColumnIndex(year))
                itemModel.runtimeminutes = result.getInt(result.getColumnIndex(runtimeminutes))
                itemModel.rating = result.getDouble((result.getColumnIndex(rating)))
                itemModel.votes = result.getInt(result.getColumnIndex(votes))
                itemModel.revenuemillions = result.getDouble(result.getColumnIndex(revenuemillions))
                itemModel.metascore = result.getInt(result.getColumnIndex(metascore))

                list.add(itemModel)
                //have used while loop because we want to get all 1000 or any number of data available in database.
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun Updateitem(itemModel: Movie, id:Int) {

        //update is totally same as insert.
        //creating content values
        val cv = ContentValues()
        cv.put(rank, itemModel.rank)
        cv.put(title, itemModel.title)
        cv.put(genre, itemModel.genre)
        cv.put(description, itemModel.description)
        cv.put(director, itemModel.director)
        cv.put(actors, itemModel.actors)
        cv.put(year, itemModel.year)
        cv.put(runtimeminutes, itemModel.runtimeminutes)
        cv.put(rating, itemModel.rating)
        cv.put(votes, itemModel.votes)
        cv.put(revenuemillions, itemModel.revenuemillions)
        cv.put(metascore, itemModel.metascore)

      /*  writableDatabase.update(
            tableName,
            cv,
            this.rank+" = ?", String[]{id}
        )*/
        try{
            //then instead of insert method we have used update.
            writableDatabase.update(tableName, cv, this.rank + "=?", arrayOf(id.toString()))
        }catch (e: Exception){
            Toast.makeText(context, "Error: " + e.message, Toast.LENGTH_SHORT).show()
        }


    }

    //and thats it.
}