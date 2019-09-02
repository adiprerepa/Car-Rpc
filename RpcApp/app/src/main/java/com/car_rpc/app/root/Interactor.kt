package com.car_rpc.app.root

interface Interactor {

    fun initiateConnection(ipAddress: String, port: Int, controllerKey: String) {

    }
}