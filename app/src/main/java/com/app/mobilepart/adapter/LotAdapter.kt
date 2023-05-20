package com.app.mobilepart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobilepart.R
import com.app.mobilepart.databinding.LotItemBinding.*
import com.app.mobilepart.model.LotModel

class LotAdapter: RecyclerView.Adapter<LotAdapter.LotHolder>() {

    private val lotik = LotModel(33111,"Подкрадули", 7999.9f,  "ссылка", "размер", "цвет")
    private val lotList: ArrayList<LotModel> = arrayListOf(lotik, lotik, lotik, lotik, lotik, lotik)

    class LotHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = bind(item)
        fun bind(lot: LotModel) = with(binding){
            lotPhoto.setImageResource(R.drawable.nike_tiffany) //картинки позже будут подгружаться
            val id = lot.id.toString()
            lotId.text = "ID: $id"
            lotName.text = lot.name
            lotCost.text = lot.cost.toString()
        }
    }

    //создание шаблона
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lot_item, parent, false)
        return LotHolder(view)
    }

    //заполнение view из массива
    override fun onBindViewHolder(holder: LotHolder, position: Int) {
        holder.bind(lotList[position])
    }

    //
    override fun getItemCount(): Int {
        return lotList.size
    }

    //Сюда отправить лист с данными
    fun refresh(list: List<LotModel>) {
        lotList.clear()
        lotList.addAll(list)
        notifyDataSetChanged()
    }

    fun addLot(lot: LotModel) {
        lotList.add(lot)
        notifyDataSetChanged()
    }
}
