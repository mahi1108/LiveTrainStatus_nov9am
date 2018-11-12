package cubex.mahesh.livetrainstatus_nov9am

import cubex.mahesh.livetrainstatus_nov9am.beans.TrainStatusBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TrainStatusAPI {
    @GET("v2/live/train/{tno}/date/{date}/apikey/0rfz4cfpdo/")
    fun getTrainStatus(@Path("tno") tn:String,
                @Path("date") dt:String):Call<TrainStatusBean>
}