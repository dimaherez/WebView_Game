package com.example.testsmartapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testsmartapps.databinding.GameLayoutBinding
import com.example.testsmartapps.game.GameProcessFragment

class GameActivity: AppCompatActivity() {
    lateinit var binding: GameLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GameLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.container, GameProcessFragment()).commit()
    }
}