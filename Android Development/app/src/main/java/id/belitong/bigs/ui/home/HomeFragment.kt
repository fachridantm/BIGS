package id.belitong.bigs.ui.home

import android.app.SearchManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.R
import id.belitong.bigs.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        TODO("Not yet implemented")
    }

    override fun initIntent() {
        TODO("Not yet implemented")
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initAction() {
        TODO("Not yet implemented")
    }

    override fun initProcess() {
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }

    override fun initMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_home, menu)
                val searchManager = getSystemService(
                    requireContext(),
                    SearchManager::class.java
                ) as SearchManager
                val searchView = menu.findItem(R.id.search_view).actionView as SearchView
                searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Validate and handle the selected menu item
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}