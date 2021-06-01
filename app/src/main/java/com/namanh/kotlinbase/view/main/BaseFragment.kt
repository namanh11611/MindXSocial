package com.namanh.kotlinbase.view.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.namanh.kotlinbase.viewmodel.SharedViewModel

open class BaseFragment<BD: ViewBinding> : Fragment() {

    val mSharedViewModel: SharedViewModel by activityViewModels()
    var _binding: BD? = null
    val binding get() = _binding!!

}