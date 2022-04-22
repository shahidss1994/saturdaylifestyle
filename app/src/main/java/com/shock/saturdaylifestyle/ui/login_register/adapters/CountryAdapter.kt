package com.shock.saturdaylifestyle.ui.login_register.adapters

/*import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.ui.login_register.models.CountryDM
import com.shock.saturdaylifestyle.ui.login_register.view.LoginRegisterActivity
import com.shock.saturdaylifestyle.databinding.ItemCountryBinding


class CountryAdapter(
    var context: Context,
    var mList: ArrayList<CountryDM>,
    var mDialogGet: Dialog
):
    RecyclerView.Adapter<CountryAdapter.MyViewHolder>(), View.OnClickListener
     {

    private lateinit var mBinding: ItemCountryBinding
    private var list: ArrayList<CountryDM> = ArrayList()
    private lateinit var dialogGet: Dialog


         init {
        this.list = mList
        this.dialogGet = mDialogGet
    }

         fun updateList( mList: ArrayList<CountryDM>)
         {
             this.list = mList
             notifyDataSetChanged()
         }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        mBinding = ItemCountryBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(mBinding)
    }


    inner class MyViewHolder(var binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var data = list[position]

        holder.binding.tvCode.text = data.code
        holder.binding.tvCountryName.text = data.countryName

        if (data.isSelected!!) {
            holder.binding.ivTick.visibility = View.VISIBLE
            data.code?.let { (context as LoginRegisterActivity).setSelectedCountry(it) }
        }
        else
            holder.binding.ivTick.visibility=View.INVISIBLE

        holder.itemView.setOnClickListener {

            for (i in list)
            { i.isSelected = false }

            data.isSelected = true
            notifyDataSetChanged()

            Handler().postDelayed( { dialogGet.dismiss() }, 200)


        }



    }

    override fun onClick(p0: View?) {}


}*/
