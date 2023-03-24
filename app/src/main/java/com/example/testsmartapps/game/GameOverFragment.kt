package com.example.testsmartapps.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testsmartapps.R
import com.example.testsmartapps.databinding.GameOverFragmentBinding

class GameOverFragment: Fragment() {
    private lateinit var binding: GameOverFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GameOverFragmentBinding.inflate(layoutInflater)

        val bundle = this.arguments

        if (bundle != null) {
            binding.finalScore.text = bundle.getInt("score").toString()
        }

        binding.tryAgainBtn.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, GameProcessFragment())
                ?.commit()
        }

        return binding.root
    }

}