package com.example.roomcrudoperation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.roomcrudoperation.Database.Contact
import com.google.android.material.button.MaterialButton
import kotlin.random.Random

class AddContactFragmentDialog:DialogFragment() {

    lateinit var model:MainViewModel
    lateinit var edit_name:EditText
    lateinit var edit_phone:EditText
    lateinit var edit_email:EditText
    lateinit var btn_add_contact:MaterialButton
    lateinit var close_dialog:ImageView

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_contact_dialog, container, false)

        model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        edit_name = view.findViewById(R.id.edit_name)
        edit_phone = view.findViewById(R.id.edit_phone)
        edit_email = view.findViewById(R.id.edit_email)
        btn_add_contact = view.findViewById(R.id.btn_add_contact)
        close_dialog = view.findViewById(R.id.close_dialog)

        close_dialog.setOnClickListener {
            dialog?.dismiss()
        }


        btn_add_contact.setOnClickListener {
            var name = edit_name.text.toString().trim()
            var phone = edit_phone.text.toString().trim()
            var email = edit_email.text.toString().trim()

            if (TextUtils.isEmpty(name)){
                edit_name.setError("Enter Name")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(phone)){
                edit_phone.setError("Enter Phone")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(email)){
                edit_email.setError("Enter Email")
                return@setOnClickListener
            }

            var contact:Contact = Contact(Random.nextInt(),name,phone, email)

            model.addContact(contact)
            dialog!!.dismiss()
        }



        return view
    }

    companion object {
        fun newInstance(title: String?): AddContactFragmentDialog? {
            val frag =
                AddContactFragmentDialog()
            val args = Bundle()
            args.putString("title", title)
            frag.setArguments(args)
            return frag
        }
    }
}