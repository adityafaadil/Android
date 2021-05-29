package com.dicoding.capspro.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capspro.CustomOnItemClickListener
import com.dicoding.capspro.FormAddUpdateActivity
import com.dicoding.capspro.R
import com.dicoding.capspro.data.DataForm
import com.dicoding.capspro.databinding.ItemFormBinding

class FormAdapter(private val activity: Activity) : RecyclerView.Adapter<FormAdapter.FormViewHolder>() {

    var listForms = ArrayList<DataForm>()
        set(listForms) {
            if (listForms.size > 0) {
                this.listForms.clear()
            }
            this.listForms.addAll(listForms)
            notifyDataSetChanged()
        }

    fun addItem(dataForm: DataForm) {
        this.listForms.add(dataForm)
        notifyItemInserted(this.listForms.size - 1)
    }

    fun updateItem(position: Int, dataForm: DataForm) {
        this.listForms[position] = dataForm
        notifyItemChanged(position, dataForm)
    }

    fun removeItem(position: Int) {
        this.listForms.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listForms.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_form, parent, false)
        return FormViewHolder(view)
    }

    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        holder.bind(listForms[position])
    }

    override fun getItemCount(): Int = this.listForms.size

    inner class FormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        private val binding = ItemFormBinding.bind(itemView)
        fun bind(dataForm: DataForm) {
            binding.tvItemName.text = dataForm.name
            binding.tvItemDate.text = dataForm.date
            binding.tvItemDescription.text = dataForm.description
            binding.cvItemForm.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                override fun onItemClicked(view: View, position: Int) {
                    val intent = Intent(activity, FormAddUpdateActivity::class.java)
                    intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION, position)
                    intent.putExtra(FormAddUpdateActivity.EXTRA_FORM, dataForm)
                    activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE)
                }
            }))
        }
    }
}