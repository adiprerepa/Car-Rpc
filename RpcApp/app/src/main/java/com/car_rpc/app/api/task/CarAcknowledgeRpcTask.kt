package com.car_rpc.app.api.task

import android.app.Activity
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import com.car_rpc.app.R
import com.car_rpc.app.api.ServerEntity
import com.car_rpc.generated.ControlAcknowledge
import com.car_rpc.generated.ControlAcknowledgeResponse
import com.car_rpc.generated.ControllerServiceGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.android.synthetic.main.connect_server_connecting.view.*
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.Exception
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

class CarAcknowledgeRpcTask(activity: Activity): AsyncTask<String, Void, ControlAcknowledgeResponse.Request_Status>() {

    var activityReference: WeakReference<Activity> = WeakReference(activity)
    lateinit var channel: ManagedChannel

    /**
     * Todo build out infrastructure so it isn't crap
     */
    override fun doInBackground(vararg p0: String?): ControlAcknowledgeResponse.Request_Status {
        val host: String? = p0[0]
        val port: Int = Integer.valueOf(p0[1]!!)
        val controllerKey: Int = Integer.valueOf(p0[2]!!)
        try {
            /**
             * The address is coming from an object class right now,
             * todo make it a data class
             */
            channel = ManagedChannelBuilder.forAddress(ServerEntity.serverAddress, ServerEntity.serverPort).usePlaintext().build()
            val stub: ControllerServiceGrpc.ControllerServiceBlockingStub = ControllerServiceGrpc.newBlockingStub(channel)
            val acknowledge: ControlAcknowledge = ControlAcknowledge.newBuilder().setAddress(host).setPort(port).setControllerKey(controllerKey).build()
            val response: ControlAcknowledgeResponse = stub.controlAcknowledgeService(acknowledge)
            return response.status
        } catch (e: Exception) {
            val sw = StringWriter()
            val pw = PrintWriter(sw)
            e.printStackTrace(pw)
            return ControlAcknowledgeResponse.Request_Status.INTERNAL_SERVER_ERROR
        }
    }

    override fun onPostExecute(result: ControlAcknowledgeResponse.Request_Status?) {
        try {
            channel.shutdown().awaitTermination(1, TimeUnit.SECONDS)
        } catch (e: Throwable) {
            Thread.currentThread().interrupt()
        }

        val activity: Activity? = activityReference.get()

        if (result!! == ControlAcknowledgeResponse.Request_Status.OK) {
            val progressBar: ProgressBar? = activity?.findViewById(R.id.progressBar)
            progressBar!!.visibility = View.GONE
        } else {
            val progressBar: ProgressBar? = activity?.findViewById(R.id.progressBar)
            progressBar!!.visibility = View.VISIBLE
        }
    }

}