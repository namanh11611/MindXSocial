package com.namanh.kotlinbase.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.namanh.kotlinbase.R
import com.namanh.kotlinbase.databinding.FragmentRegisterBinding
import com.namanh.kotlinbase.utils.AppUtils
import com.namanh.kotlinbase.view.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), View.OnClickListener {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private val viewModel: RegisterViewModel by viewModels()

    override fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnConfirm.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_confirm -> {
                if (binding.edtUserName.text.toString().isBlank()) {
                    showErrorMessage("User name empty")
                } else if (binding.edtFullName.text.toString().isBlank()) {
                    showErrorMessage("Full name empty")
                } else if (binding.edtEmail.text.toString().isBlank()) {
                    showErrorMessage("Email empty")
                } else if (!binding.edtEmail.text.toString().contains("@")) {
                    showErrorMessage("Email invalid")
                } else if (binding.edtPassword.text.toString().isBlank()) {
                    showErrorMessage("Password empty")
                } else if (binding.edtConfirmPassword.text.toString().isBlank()) {
                    showErrorMessage("Confirm password empty")
                } else if (binding.edtPassword.text.toString() != binding.edtConfirmPassword.text.toString()) {
                    showErrorMessage("Confirm password invalid")
                } else {
                    AppUtils.putPrefString(context, AppUtils.PREF_USER_NAME, binding.edtUserName.text.toString())
                    AppUtils.putPrefString(context, AppUtils.PREF_PASSWORD, binding.edtPassword.text.toString())
                    findNavController().navigate(RegisterFragmentDirections
                        .actionRegisterFragmentToLoginFragment())
                }
            }
        }
    }

    private fun showErrorMessage(mess: String) {
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()
    }

}