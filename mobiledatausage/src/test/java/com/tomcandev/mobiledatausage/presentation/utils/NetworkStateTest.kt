package com.tomcandev.mobiledatausage.presentation.utils

import org.junit.Assert.*
import org.junit.Test

class NetworkStateTest {
    @Test
    fun testCreateState() {
        var networkState:NetworkState = NetworkState.Loading
        assertEquals(networkState, NetworkState.Loading)
        networkState = NetworkState.Loaded
        assertEquals(networkState, NetworkState.Loaded)
        networkState = NetworkState.Error("error")
        assertEquals((networkState as NetworkState.Error).error, "error")
    }
}