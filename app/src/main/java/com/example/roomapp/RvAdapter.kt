package com.example.roomapp

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.Entity.User
import androidx.lifecycle.ViewModelProvider as LifecycleViewModelProvider

class RvAdapter(val ctx:Context): RecyclerView.Adapter<RvAdapter.vholder>() {
    var lsUsers= emptyList<User>()
    lateinit var mUserViewModel: UserViewModel
   inner class vholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       var nametxt=itemView.findViewById<TextView>(R.id.txtnameID)
       var emailtxt=itemView.findViewById<TextView>(R.id.txtemailID)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vholder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.itemuser,parent,false)
        return vholder(view)
    }

    override fun onBindViewHolder(holder: vholder, position: Int) {
       //get Item from holder
        val txtname=lsUsers[position].name
        val txtemail=lsUsers[position].email
        holder.nametxt.text=txtname
        holder.emailtxt.text=txtemail

        holder.itemView.setOnClickListener{
            val v=LayoutInflater.from(ctx).inflate(R.layout.item_updat_delet,null)
            val builder = AlertDialog.Builder(ctx)
                .create()
            builder.setView(v)

            var btndele=v.findViewById<TextView>(R.id.DeletId)
            var btnupdat=v.findViewById<TextView>(R.id.updateId)
            var edtname=v.findViewById<EditText>(R.id.edtName)
            var edtemail=v.findViewById<EditText>(R.id.edtEmail)

            val btnok=v.findViewById<TextView>(R.id.okID)
            val btncancel=v.findViewById<TextView>(R.id.canelID)

            btncancel.setOnClickListener {
                builder.dismiss()
            }
            // set textes
                edtname.setText(txtname)
                edtemail.setText(txtemail)

            btndele.setOnClickListener {
                if(checkFields(txtname.toString(),txtemail.toString())){
                    var updatUser=User(lsUsers[position].Id,lsUsers[position].name,lsUsers[position].email)
                    var context=ctx as ViewModelStoreOwner

                    mUserViewModel = androidx.lifecycle.ViewModelProvider(context)[UserViewModel::class.java]
                    mUserViewModel.deletUser(updatUser)

                    builder.dismiss()

                }
            }
            btnupdat.setOnClickListener {
                btndele.visibility=View.INVISIBLE
                edtname.isEnabled=true
                edtemail.isEnabled=true

            }

            btnok.setOnClickListener {
                if(checkFields(txtname.toString(),txtemail.toString())){
                    var updatUser=User(lsUsers[position].Id,edtname.text.toString(),edtemail.text.toString())
                    var context=ctx as ViewModelStoreOwner
                    mUserViewModel = androidx.lifecycle.ViewModelProvider(context)[UserViewModel::class.java]
                    mUserViewModel.UpdatUser(updatUser)
                    builder.dismiss()

                }
            }
                builder.show()
        }
    }

    override fun getItemCount(): Int {
       return lsUsers.size
    }
    fun setData(lsU:List<User>){
      lsUsers=lsU
        notifyDataSetChanged()
    }
    fun checkFields( name:String,email:String):Boolean{
        return  !(TextUtils.isEmpty(name)&& TextUtils.isEmpty(email))
    }
}