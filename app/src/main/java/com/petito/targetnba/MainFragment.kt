package com.petito.targetnba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.petito.targetnba.databinding.FragmentMainBinding

class MainFragment : Fragment(), View.OnClickListener {

    private var layoutBinding: FragmentMainBinding? = null
    private val binding get() = layoutBinding

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = super.onCreateView(inflater, container, savedInstanceState)
        layoutBinding = FragmentMainBinding.inflate(inflater, container, false)
        view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding?.signInButton?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.sign_in_button -> navController.navigate(R.id.action_mainFragment_to_allTeamsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        layoutBinding = null
    }
}