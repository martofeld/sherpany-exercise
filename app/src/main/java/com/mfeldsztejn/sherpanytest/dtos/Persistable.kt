package com.mfeldsztejn.sherpanytest.dtos

import java.io.Serializable

interface Persistable : Serializable {
    fun obtainId(): Int
}