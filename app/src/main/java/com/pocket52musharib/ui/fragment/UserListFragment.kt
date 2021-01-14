package com.pocket52musharib.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.pocket52musharib.R
import com.pocket52musharib.api.model.UserInfo
import com.pocket52musharib.databinding.ItemUserBinding
import com.pocket52musharib.databinding.UserListFragmentBinding
import com.pocket52musharib.ui.AppNavigatorInterface
import com.pocket52musharib.ui.Command
import com.pocket52musharib.ui.MainActivity
import com.pocket52musharib.ui.adapter.BaseAdapterBinding
import com.pocket52musharib.ui.viewModule.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment() ,BaseAdapterBinding.BindAdapterListener{
    @Inject
    lateinit var appNavigatorInterface: AppNavigatorInterface
    private val viewModel:UserListViewModel by viewModels()
    private var binding:UserListFragmentBinding? = null
    private val adapter: BaseAdapterBinding<UserInfo> by lazy {
        BaseAdapterBinding(this.requireActivity(), arrayListOf(),this, R.layout.item_user)
    }

    companion object {
        fun newInstance() = UserListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserListFragmentBinding.inflate(inflater,container,false).also {
            it.lifecycleOwner = this
            it.viewModule = viewModel
            it.adapter = adapter
            init()
        }
        return binding?.root
    }

    private fun init(){
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.user_list)
        viewModel.loadData(viewLifecycleOwner)
        viewModel.data.observe(viewLifecycleOwner,{
            it?.let { list-> adapter.setData(list) }
        })

    }

    override fun onBind(holder: BaseAdapterBinding.DataBindingViewHolder, position: Int) {
       if(holder.binding is ItemUserBinding){
           with(holder.binding) {
               item = adapter.item(position)
              fragment = this@UserListFragment
           }
       }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.appSearchBar)
        val searchView = SearchView(this.context)
        getString(R.string.search).also { searchView.queryHint = it }
         search.actionView = searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.search(viewLifecycleOwner, it) }
                return true
            }
        })
    }
    fun moveNext(userInfo: UserInfo){
        userInfo.let {
            val userId = "${it.uId}"
            appNavigatorInterface.navigator(Command.POST, bundleOf("userId" to userId))
        }
    }


}