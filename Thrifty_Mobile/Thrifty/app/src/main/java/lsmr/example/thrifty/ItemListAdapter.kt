package lsmr.example.thrifty

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemListAdapter (private val context: Context, private val queueList: ArrayList<itemData>):
    RecyclerView.Adapter<ItemListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.items_list, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ItemListAdapter.MyViewHolder, position: Int) {

        val queue: itemData = queueList[position]
        holder.item.text = queue.item
        holder.price.text = "₱"+queue.price
//        holder.cancelBtn.text ="X"
//
//        holder.cancelBtn.setOnClickListener {
//            when {
//
//                    //deleteItem(queue.id!!, position)
//                    // finish()
//                }
//            }

        }

   private fun deleteItem(id: String, position: Int){

    }


    override fun getItemCount(): Int {
        return queueList.size
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: TextView = itemView.findViewById(R.id.itemName)!!
        val price: TextView = itemView.findViewById(R.id.itemPrice)!!

//        val cancelBtn: Button = itemView.findViewById(R.id.cancelBtn)
    }

}