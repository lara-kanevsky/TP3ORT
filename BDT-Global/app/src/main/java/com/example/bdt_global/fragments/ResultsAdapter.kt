package com.example.bdt_global.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bdt_global.R
import com.example.bdt_global.entities.responseEntities.ResultsResponse

class ResultsAdapter(
    var resultsList: MutableList<ResultsResponse>
) : RecyclerView.Adapter<ResultsAdapter.ResultHolder>() {

    class ResultHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var v: View

        init {
            this.v = view
        }

        fun setResultDate(date: String) {
            val tvResultDate: TextView = v.findViewById(R.id.tvResultDate)
            tvResultDate.text = date
        }

        fun setResultCO2(resultCO2: String) {
            val tvResultCO2: TextView = v.findViewById(R.id.tvResultCO2)
            tvResultCO2.text = resultCO2
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return (ResultHolder(view))
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        holder.setResultDate(resultsList[position].date)
        holder.setResultCO2(resultsList[position].tonsCO2.toString())
    }

    override fun getItemCount(): Int {
        return resultsList.size
    }

}
