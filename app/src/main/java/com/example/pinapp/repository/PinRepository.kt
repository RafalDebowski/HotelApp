package com.example.pinapp.repository

import com.example.pinapp.model.domain.PinDomain
import io.reactivex.Completable
import io.reactivex.Single
import org.mongodb.kbson.ObjectId

interface PinRepository {

    fun insertPin(pin : PinDomain) : Completable

    fun getPins() : Single<List<PinDomain>>

    fun deletePinById(id: ObjectId): Completable
}