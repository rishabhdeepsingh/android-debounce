package com.rishabh.testapp.blankFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.rishabh.testapp.R
import com.rishabh.testapp.databinding.FragmentBlankBinding
import com.rishabh.testapp.setDebounceListener
import java.util.*
import java.util.logging.Logger

class BlankFragment : Fragment() {

    private lateinit var mBinding: FragmentBlankBinding
    private lateinit var viewModel: BlankFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewTreeLifecycleOwner.set(mBinding.root, viewLifecycleOwner)
        mBinding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this).get(BlankFragmentViewModel::class.java)
        mBinding.viewModel = viewModel

        var last = Calendar.getInstance().timeInMillis
        setDebounceListener(mBinding.btnFrag) {
            viewModel.plus()
            val curr = Calendar.getInstance().timeInMillis
            val diff = curr - last
            Logger.getGlobal().info("!!!!!!!!!!    " + (diff).toString())
            last = curr
        }
    }

    companion object {
        fun getInstance() = BlankFragment()
    }
}