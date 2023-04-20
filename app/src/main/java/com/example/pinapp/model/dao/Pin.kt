package com.example.pinapp.model.dao

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


open class Pin: RealmObject {
     @PrimaryKey
     var id: ObjectId = ObjectId.invoke()
     var name: String = ""
     var code: Long = -1

}

