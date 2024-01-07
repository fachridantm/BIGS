package id.belitong.bigs.ui.home

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.belitong.bigs.BaseFragment
import id.belitong.bigs.R
import id.belitong.bigs.core.data.Resource
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.ui.CardHomeAdapter
import id.belitong.bigs.core.ui.CarouselHomeAdapter
import id.belitong.bigs.core.utils.ZoomOutPageTransformer
import id.belitong.bigs.core.utils.getFirstName
import id.belitong.bigs.core.utils.showSnackbar
import id.belitong.bigs.core.utils.showToast
import id.belitong.bigs.databinding.FragmentHomeBinding
import id.belitong.bigs.ui.details.geoprogramme.GeoprogrammeActivity
import id.belitong.bigs.ui.details.geosites.GeositesActivity
import id.belitong.bigs.ui.main.MainViewModel
import id.belitong.bigs.ui.profile.ProfileActivity
import id.belitong.bigs.ui.search.SearchResultsActivity

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val carouselHomeAdapter: CarouselHomeAdapter by lazy { CarouselHomeAdapter(::carouselItemClicked) }
    private val cardHomeAdapter: CardHomeAdapter by lazy { CardHomeAdapter(::cardItemClicked) }

    private val mainViewModel: MainViewModel by viewModels()

    private var biodiversities: List<Biodiversity> = listOf()
    private var geosites: List<Geosite> = listOf()

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initData() {
        mainViewModel.getName().observe(viewLifecycleOwner) {
            val name = it.getFirstName()
            binding?.textView2?.text = getString(R.string.geopark_belitong_user, name)
        }

        mainViewModel.biodiversities.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading(true)

                is Resource.Success -> {
                    showLoading(false)
                    cardHomeAdapter.submitList(it.data)
                    biodiversities = it.data

                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message.showSnackbar(requireView())
                }

                else -> {}
            }
        }

        mainViewModel.geosites.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading(true)

                is Resource.Success -> {
                    showLoading(false)
                    carouselHomeAdapter.submitList(it.data)
                    geosites = it.data
                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message.showSnackbar(requireView())
                }

                else -> {}
            }
        }
    }

    override fun initView() {
        with(binding) {
            this?.carouselPager?.apply {
                adapter = carouselHomeAdapter

                val zoomOutPageTransformer = ZoomOutPageTransformer()
                setPageTransformer { page, position ->
                    zoomOutPageTransformer.transformPage(page, position)
                }

                dotsIndicator.attachTo(this)
            }
            this?.rvGeosites?.apply {
                adapter = cardHomeAdapter
                setHasFixedSize(true)
            }

            this?.toolbarHome?.apply {
                val searchView = menu.findItem(R.id.search_view).actionView as SearchView

                searchView.apply {
                    inputType = InputType.TYPE_NULL
                    isIconifiedByDefault = false
                    queryHint = getString(R.string.search_hint)
                    background = getDrawable(requireContext(), R.drawable.bg_transparent)

                    setOnQueryTextFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            SearchResultsActivity.start(requireContext())
                        }
                    }

                    setOnClickListener {
                        SearchResultsActivity.start(requireContext())
                    }

                }
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.btn_profile -> {
                            ProfileActivity.start(requireContext())
                            true
                        }

                        else -> false
                    }
                }
            }
        }
    }

    override fun initAction() {
        with(binding) {
            this?.chipAllHome?.setOnClickListener {
                cardHomeAdapter.submitList(biodiversities)
            }

            this?.chipGeositesHome?.setOnClickListener {
                val geosites = biodiversities.filter {
                    it.type != getString(R.string.plant) && it.type != getString(R.string.animal)
                }
                cardHomeAdapter.submitList(geosites)
            }

            this?.chipPlantsHome?.setOnClickListener {
                val plants = biodiversities.filter { it.type == getString(R.string.plant) }
                cardHomeAdapter.submitList(plants)
            }

            this?.chipAnimalsHome?.setOnClickListener {
                val animals = biodiversities.filter { it.type == getString(R.string.animal) }
                cardHomeAdapter.submitList(animals)
            }

            this?.btnGeosites?.setOnClickListener {
                GeositesActivity.start(requireContext())
            }

            this?.btnGeoprogramme?.setOnClickListener {
                GeoprogrammeActivity.start(requireContext())
            }

            this?.btnMaps?.setOnClickListener {
                getString(R.string.on_click_handler).showToast(requireContext())
            }
        }
    }

    override fun initObservers() {}

    override fun onResume() {
        super.onResume()
        binding?.apply {
            toolbarHome.menu?.findItem(R.id.search_view)?.actionView?.clearFocus()
            chipAllHome.isChecked = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.apply {
            toolbarHome.menu?.findItem(R.id.search_view)?.actionView?.clearFocus()
            chipAllHome.isChecked = true
        }

    }

    private fun carouselItemClicked(geosite: Geosite) {
        getString(R.string.on_click_handler).showSnackbar(requireView())
    }

    private fun cardItemClicked(biodiversity: Biodiversity) {
        getString(R.string.on_click_handler).showSnackbar(requireView())
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.apply {
            if (isLoading) {
                pbHome.visibility = android.view.View.VISIBLE
            } else {
                pbHome.visibility = android.view.View.GONE
            }
        }
    }
}