package com.app.mobilepart.adapter

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobilepart.R
import com.app.mobilepart.databinding.LotItemBinding.*
import com.app.mobilepart.model.LotModel

class LotAdapter: RecyclerView.Adapter<LotAdapter.LotHolder>() {

    private var lotList: MutableList<Pair<LotModel, Boolean>> = mutableListOf()

    class LotHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = bind(item)
        fun bind(lot: LotModel, listener: OnClickListener) = with(binding){
            lotPhoto.setImageResource(R.drawable.nike_tiffany) //картинки позже будут подгружаться
            val id = lot.id.toString()
            lotId.text = "ID: $id"
            lotName.text = lot.name + "??"
            lotSize.text = lot.size
            lotCost.text = lot.cost.toString()
            select.setOnClickListener(listener)
        }
    }


    //создание шаблона
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lot_item, parent, false)
        return LotHolder(view)
    }

    //заполнение view из массива
    override fun onBindViewHolder(holder: LotHolder, position: Int) {
        holder.bind(lotList[position].first) { setLot(position) }
    }

    //
    override fun getItemCount(): Int {
        return lotList.size
    }

    //Сюда отправить лист с данными
    fun refresh(list: List<LotModel>) {
        lotList = list.map{ Pair(it, false)}.toMutableList()
        notifyDataSetChanged()
    }

    fun getSelectedLots(): List<LotModel> {
        return lotList.filter { i -> i.second }.map { i -> i.first }
    }

    fun setLot(index: Int) {
        lotList[index] = Pair(lotList[index].first, !lotList[index].second)
    }
}
