package com.pocket52musharib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pocket52musharib.R
import com.pocket52musharib.api.model.UserPost
import com.pocket52musharib.databinding.ItemUserPostBinding
import com.pocket52musharib.databinding.UserListFragmentBinding
import com.pocket52musharib.ui.MainActivity
import com.pocket52musharib.ui.adapter.BaseAdapterBinding
import com.pocket52musharib.ui.viewModule.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserPostFragment : Fragment() ,BaseAdapterBinding.BindAdapterListener{
    private val viewModel:UserListViewModel by viewModels()
    private var binding:UserListFragmentBinding? = null
    private val adapter: BaseAdapterBinding<UserPost> by lazy {
        BaseAdapterBinding(this.requireActivity(), arrayListOf(), this, R.layout.item_user_post)
    }

    companion object {
        fun newInstance(b: Bundle?):UserPostFragment {
            val fragment = UserPostFragment()
              fragment.arguments = b
            return fragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = UserListFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModule = viewModel
            it.adapter = adapter
            init()
        }
        return binding?.root
    }

    private fun init(){
        ( activity as MainActivity).supportActionBar?.title = getString(R.string.user_post_list)
        arguments?.getString("userId")?.let { viewModel.loadUserPost(viewLifecycleOwner, it) }
        viewModel.dataPost.observe(viewLifecycleOwner, {
            it?.let { list -> adapter.setData(list) }
        })

    }

    override fun onBind(holder: BaseAdapterBinding.DataBindingViewHolder, position: Int) {
       if(holder.binding is ItemUserPostBinding){
           holder.binding.item = adapter.item(position)
       }
    }



}