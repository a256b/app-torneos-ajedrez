package com.example.apptorneosajedrez.ui.movimientos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apptorneosajedrez.databinding.ItemMoveBinding
import com.example.apptorneosajedrez.model.Move
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MovesAdapter(private var moves: List<Move>)
    : RecyclerView.Adapter<MovesAdapter.MoveVH>() {

    inner class MoveVH(val binding: ItemMoveBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(m: Move) {
            binding.tvNotation.text = m.notation
            binding.tvTimestamp.text =
                SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                    .format(Date(m.timestamp))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoveVH(ItemMoveBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MoveVH, position: Int) =
        holder.bind(moves[position])

    override fun getItemCount() = moves.size

    fun update(newList: List<Move>) {
        moves = newList
        notifyDataSetChanged()
    }
}
