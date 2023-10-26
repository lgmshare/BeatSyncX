package com.changhong.beatsyncx.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.changhong.beatsyncx.databinding.FragmentDashboardBinding
import com.changhong.beatsyncx.ui.ArticleDetailActivity

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        dashboardViewModel.listLiveData.observe(viewLifecycleOwner) {
        }

        binding.articleItem1.setOnClickListener {
            startActivity(Intent(requireActivity(), ArticleDetailActivity::class.java).putExtra("index", 1))
        }
        binding.articleItem2.setOnClickListener {
            startActivity(Intent(requireActivity(), ArticleDetailActivity::class.java).putExtra("index", 2))
        }
        binding.articleItem3.setOnClickListener {
            startActivity(Intent(requireActivity(), ArticleDetailActivity::class.java).putExtra("index", 3))
        }
        binding.articleItem4.setOnClickListener {
            startActivity(Intent(requireActivity(), ArticleDetailActivity::class.java).putExtra("index", 4))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}