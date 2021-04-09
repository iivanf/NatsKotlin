package example.nats


import android.graphics.Color
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import io.nats.client.Connection


open interface IDataCollector {
    fun setConnect(connect: Boolean)
    fun setResponse(response: String)
}

class MainActivity : AppCompatActivity(), IDataCollector {
    lateinit var nats: NatsManager;
    lateinit var text: TextView
    var responses: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //this.getWindow().setStatusBarColor(android.graphics.Color.TRANSPARENT);
        val hide = supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.response)
        text.text = responses
        nats = NatsManager(this)
        var btn : MaterialButton = findViewById(R.id.connect)
        btn.setOnClickListener { nats.connect() }

        var btn2 : MaterialButton = findViewById(R.id.pub)
        btn2.setOnClickListener { nats.pub("test", "THIS IS A TEST MSG") }
    }



    override fun setConnect(connect: Boolean) {
        val natsGreenBar: LinearProgressIndicator = findViewById(R.id.connectIndicator)
        if(connect == true){
            natsGreenBar.setIndicatorColor(Color.parseColor("#23B221"))
            natsGreenBar.visibility= View.VISIBLE
        }
    }

    override fun setResponse(response: String) {
        responses = responses+response+"\n"
        println(responses)
        text.text= responses
    }


}

