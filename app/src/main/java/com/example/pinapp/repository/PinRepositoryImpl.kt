package com.example.pinapp.repository

import com.example.pinapp.model.dao.Pin
import com.example.pinapp.model.domain.PinDomain
import com.example.pinapp.model.mappers.toPin
import com.example.pinapp.model.mappers.toPinDomain
import com.example.pinapp.model.mappers.toPinDomainList
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort

class PinRepositoryImpl(
    private val realmDatabase: Realm
) : PinRepository {

    override fun insertPin(pin: PinDomain): Completable =
        Completable.fromAction {
            realmDatabase.writeBlocking {
                this.copyToRealm(pin.toPin())
            }
        }

    override fun getPins(): Single<List<PinDomain>> {
        return Single.fromCallable {
            val query = realmDatabase.query<Pin>().sort("name", Sort.ASCENDING).find()
            realmDatabase.writeBlocking {
                this.copyFromRealm(query).toPinDomainList()
            }
        }
    }
}