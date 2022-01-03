package com.namanh.mindx.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.namanh.mindx.R
import com.namanh.mindx.databinding.FragmentLoginBinding
import com.namanh.mindx.utils.AppUtils
import com.namanh.mindx.view.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(), View.OnClickListener {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnConfirm.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_confirm -> {
                val username = AppUtils.getPrefString(context, AppUtils.PREF_USER_NAME)
                val password = AppUtils.getPrefString(context, AppUtils.PREF_PASSWORD)
                if (binding.edtUserName.text.toString().isBlank()) {
                    showErrorMessage("User name empty")
                } else if (binding.edtPassword.text.toString().isBlank()) {
                    showErrorMessage("Password empty")
                } else if (binding.edtUserName.text.toString() != username || binding.edtPassword.text.toString() != password) {
                    showErrorMessage("User Name or Password invalid")
                } else {
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToNewsListFragment())
                }
            }
        }
    }

    private fun showErrorMessage(mess: String) {
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show()
    }

}