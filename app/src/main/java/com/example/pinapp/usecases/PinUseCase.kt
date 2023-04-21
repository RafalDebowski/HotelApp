package com.example.pinapp.usecases

import com.example.pinapp.model.domain.PinDomain
import com.example.pinapp.model.mappers.toPin
import com.example.pinapp.repository.PinRepositoryImpl
import io.reactivex.Completable
import io.reactivex.Single

class PinUseCase(
    private val pinRepository: PinRepositoryImpl
) {

    fun insertPin(pin: PinDomain): Completable =
        pinRepository.insertPin(pin)

    fun getPins(): Single<List<PinDomain>> =
        pinRepository.getPins()
}