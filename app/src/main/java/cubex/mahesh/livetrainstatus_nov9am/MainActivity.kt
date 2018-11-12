package cubex.mahesh.livetrainstatus_nov9am

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import cubex.mahesh.livetrainstatus_nov9am.beans.TrainStatusBean
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getStatus.setOnClickListener {

            var r = Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl("https://api.railwayapi.com/").
                build()
            var api = r.create(TrainStatusAPI::class.java)
            var call:Call<TrainStatusBean> = api.getTrainStatus(
                et2.text.toString(), et1.text.toString())
            call.enqueue(object:Callback<TrainStatusBean>{
                override fun onFailure(call: Call<TrainStatusBean>, t: Throwable) {
                }

                override fun onResponse(call: Call<TrainStatusBean>,
                                        response: Response<TrainStatusBean>) {
                 var bean =   response.body()
                 var temp_list = mutableListOf<String>()
                 temp_list.add(bean!!.position)
                 for(st in bean!!.route!!)   {
                     temp_list.add(st.station.name+"\n"+
                                st.actarr+"\t"+st.actdep+"\n"+
                                st.status)
                 }
                    var adapter = ArrayAdapter<String>(this@MainActivity,
                        android.R.layout.activity_list_item,temp_list)
                    lview.adapter = adapter
                }
            })

        }
    }
}
