package edu.temple.activity4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var textSizeSelector: RecyclerView
    lateinit var textSizeDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //

        textSizeSelector = findViewById(R.id.textSizeSelectorRecyclerView)
        textSizeDisplay = findViewById(R.id.textSizeDisplayTextView)

     //   val someFunction : (Int, Int) -> Unit = {x: Int, y: Int -> textSizeDisplay.textSize = (x + y).toFloat()}

        // Trying to create array of integers that are multiples of 5
        // Verify correctness by examining array values.
        val textSizes = Array(20) { (it + 1) * 5 }

        for (i in 0 until textSizes.size) {
            Log.d("Array values", textSizes[i].toString())
        }

        with(findViewById<RecyclerView>(R.id.textSizeSelectorRecyclerView)){
            adapter = TextSizeAdapter(textSizes){   //if a lambda is the last item in a function it can be placed outside the ()
                textSizeDisplay.textSize = it
            }
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        /*textSizeSelector.adapter = TextSizeAdapter(textSizes)
        textSizeSelector.layoutManager = LinearLayoutManager(this) */
    }


    /* Convert to RecyclerView.Adapter */
    class TextSizeAdapter(_textSizes: Array<Int>, _callback : (Float) -> Unit) :
        RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {
         val callback = _callback
         val textSizes = _textSizes

       inner class TextSizeViewHolder(view: TextView) : RecyclerView.ViewHolder(view) { //inner class can't exist without Adapter
            val textView = view
            //Only at the time a view is assigned, a new callback is created
            init{
                textView.setOnClickListener{callback(textSizes[adapterPosition].toFloat())}
        }

    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
            return TextSizeViewHolder(TextView(parent.context).apply { setPadding(5, 20, 0, 20) })
        }

        override fun getItemCount(): Int {
            return textSizes.size
        }

        override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
            holder.textView.apply{
                text = textSizes[position].toString()
                textSize = textSizes[position].toFloat()
            }
            /* holder.textview.text = textSizes[position].toString()
             holder.textview.textSize = textSizes[position].toFloat() */
        }
        }
    }