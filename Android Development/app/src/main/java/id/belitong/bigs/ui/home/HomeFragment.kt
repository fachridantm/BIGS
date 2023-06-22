package id.belitong.bigs.ui.home

import android.content.Intent
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
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.ui.CardHomeAdapter
import id.belitong.bigs.core.ui.CarouselHomeAdapter
import id.belitong.bigs.core.utils.DummyData
import id.belitong.bigs.core.utils.ZoomOutPageTransformer
import id.belitong.bigs.core.utils.getFirstName
import id.belitong.bigs.core.utils.showMessage
import id.belitong.bigs.databinding.FragmentHomeBinding
import id.belitong.bigs.ui.details.geoprogramme.GeoprogrammeActivity
import id.belitong.bigs.ui.details.geosites.GeositesActivity
import id.belitong.bigs.ui.profile.ProfileActivity
import id.belitong.bigs.ui.search.SearchResultsActivity

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val carouselHomeAdapter: CarouselHomeAdapter by lazy { CarouselHomeAdapter(::carouselItemClicked) }
    private val cardHomeAdapter: CardHomeAdapter by lazy { CardHomeAdapter(::cardItemClicked) }

    private val homeViewModel: HomeViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initData() {
        homeViewModel.getName().observe(viewLifecycleOwner) {
            val name = it.getFirstName()
            binding?.textView2?.text = getString(R.string.geopark_belitong_user, name)
        }

        carouselHomeAdapter.submitList(DummyData.getAllGeosites())

        cardHomeAdapter.submitList(DummyData.getAllBiodiversity())
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
                            startActivity(
                                Intent(
                                    requireContext(), SearchResultsActivity::class.java
                                )
                            )
                        }
                    }

                    setOnClickListener {
                        startActivity(Intent(requireContext(), SearchResultsActivity::class.java))
                    }

                }
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.btn_profile -> {
                            startActivity(Intent(requireContext(), ProfileActivity::class.java))
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
            val data = DummyData.getAllBiodiversity()

            this?.chipAllHome?.setOnClickListener {
                cardHomeAdapter.submitList(data)
            }

            this?.chipGeositesHome?.setOnClickListener {
                val geosites = data.filter {
                    it.type != getString(R.string.plant) && it.type != getString(R.string.animal)
                }
                cardHomeAdapter.submitList(geosites)
            }

            this?.chipPlantsHome?.setOnClickListener {
                val plants = data.filter { it.type == getString(R.string.plant) }
                cardHomeAdapter.submitList(plants)
            }

            this?.chipAnimalsHome?.setOnClickListener {
                val animals = data.filter { it.type == getString(R.string.animal) }
                cardHomeAdapter.submitList(animals)
            }

            this?.btnGeosites?.setOnClickListener {
                startActivity(Intent(requireContext(), GeositesActivity::class.java))
            }

            this?.btnGeoprogramme?.setOnClickListener {
                startActivity(Intent(requireContext(), GeoprogrammeActivity::class.java))
            }

            this?.btnMaps?.setOnClickListener {
                getString(R.string.onClickHandler).showMessage(requireContext())
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
        // TODO 6: Implement carouselItemClicked
    }

    private fun cardItemClicked(biodiversity: Biodiversity) {
        // TODO 7: Implement cardItemClicked
    }

}