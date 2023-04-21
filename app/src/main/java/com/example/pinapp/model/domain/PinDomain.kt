package com.example.pinapp.model.domain

import org.mongodb.kbson.ObjectId

data class PinDomain(
    var id: ObjectId? = null,
    var name: String,
    var code: Int
)