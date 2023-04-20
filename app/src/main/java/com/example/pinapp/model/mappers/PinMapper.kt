package com.example.pinapp.model.mappers

import com.example.pinapp.model.dao.Pin
import com.example.pinapp.model.domain.PinDomain

fun Pin.toPinDomain(): PinDomain =
    PinDomain(
        id = this.id,
        name = this.name,
        code = this.code
    )

fun PinDomain.toPin(): Pin {
    val pinDomain = this
    return Pin().apply {
        this.name = pinDomain.name
        this.code = pinDomain.code
    }
}


fun List<Pin>.toPinDomainList(): List<PinDomain> =
    this.map {
        it.toPinDomain()
    }

fun List<PinDomain>.toPinList(): List<Pin> =
    this.map {
        it.toPin()
    }