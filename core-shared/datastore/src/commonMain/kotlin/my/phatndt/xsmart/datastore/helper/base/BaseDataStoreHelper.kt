package my.phatndt.xsmart.datastore.helper.base

interface BaseDataStoreHelper {
    suspend fun setIntValue(key: String, value: Int)
    suspend fun getIntValue(key: String, defaultValue: Int)

    suspend fun setDoubleValue(key: String, value: Double)
    suspend fun getDoubleValue(key: String, defaultValue: Double)

    suspend fun setStringValue(key: String, value: String)
    suspend fun getStringValue(key: String, defaultValue: String)

    suspend fun setBooleanValue(key: String, value: Boolean)
    suspend fun getBooleanValue(key: String, defaultValue: Boolean)

    suspend fun setFloatValue(key: String, value: Float)
    suspend fun getFloatValue(key: String, defaultValue: Float)

    suspend fun setLongValue(key: String, value: Long)
    suspend fun getLongValue(key: String, defaultValue: Long)

    suspend fun setStringSetValue(key: String, value: Set<String>)
    suspend fun getStringSetValue(key: String, defaultValue: Set<String>)

    suspend fun setByteArrayValue(key: String, value: ByteArray)
    suspend fun getByteArrayValue(key: String, defaultValue: ByteArray)
}
