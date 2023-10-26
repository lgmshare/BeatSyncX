package com.changhong.beatsyncx.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.changhong.beatsyncx.databinding.FragmentNotificationsBinding
import com.changhong.beatsyncx.ui.PrivacyActivity

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        val notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        val root: View = binding.root
        binding.btnMore.setOnClickListener {
            startActivity(Intent(requireActivity(), PrivacyActivity::class.java))
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}