package my.phatndt.xsmart.feature.currency.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.FieldPath
import dev.gitlive.firebase.firestore.ServerTimestampBehavior
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.orderBy
import dev.gitlive.firebase.firestore.where
import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import my.phatndt.xsmart.feature.currency.data.model.CurrencyModel


class RemoteFirebaseDataSource {
    suspend fun uploadCurrencyDataToFireStore(currencyModel: CurrencyModel) {
        Firebase.firestore.collection("currency_rate_data")
            .add(currencyModel)
    }

    suspend fun getCurrencyInDate(): CurrencyModel {
        val result = Firebase.firestore.collection("currency_rate_data")
            .orderBy("meta.last_updated_at", Direction.DESCENDING).get()
        val format = Json { isLenient = true }
        return format.decodeFromString(
            result.documents.first().data<String>(ServerTimestampBehavior.NONE).replace("=", ":")
        )
    }
}