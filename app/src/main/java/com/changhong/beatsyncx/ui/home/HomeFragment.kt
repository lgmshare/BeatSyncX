package com.changhong.beatsyncx.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.changhong.beatsyncx.R
import com.changhong.beatsyncx.databinding.FragmentHomeBinding
import com.changhong.beatsyncx.ui.EditActivity
import com.changhong.beatsyncx.ui.PrivacyActivity
import com.changhong.beatsyncx.ui.adapter.RecordAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()

    private val adapter by lazy { RecordAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.listLiveData.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                binding.emptyLayout.isVisible = true
                binding.listLayout.isVisible = false
                binding.textHome.setTextColor(resources.getColor(R.color.color_333333))
            } else {
                var totalMax = 0
                var totalMin = 0
                var totalAve = 0
                list.forEach {
                    totalMax += it.max
                    totalMin += it.min
                    totalAve += it.ave
                }

                adapter.dataList.clear()
                adapter.dataList.addAll(list)
                adapter.notifyDataSetChanged()

                binding.tvMax.text = (totalMax / list.size).toInt().toString()
                binding.tvMin.text = (totalMin / list.size).toInt().toString()
                binding.tvAve.text = (totalAve / list.size).toInt().toString()

                binding.emptyLayout.isVisible = false
                binding.listLayout.isVisible = true
                binding.textHome.setTextColor(resources.getColor(R.color.white))
            }
        }

        binding.emptyBtn.setOnClickListener {
            startActivity(Intent(requireActivity(), EditActivity::class.java))
        }

        binding.btnRecord.setOnClickListener {
            startActivity(Intent(requireActivity(), EditActivity::class.java))
        }
        adapter.itemClickCallback = {
            startActivity(Intent(requireActivity(), EditActivity::class.java).apply {
                putExtra("heartbeat", it)
            })
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}