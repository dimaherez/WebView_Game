package com.example.testsmartapps.game

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testsmartapps.R
import com.example.testsmartapps.databinding.GameProcessFragmentBinding
import java.util.*

class GameProcessFragment : Fragment() {

    private lateinit var binding: GameProcessFragmentBinding
    private val colors: Map<String, String> = mapOf(
        "yellow" to "#FFFF00",
        "red" to "#FF0000",
        "blue" to "#0000FF",
        "purple" to "#A020F0",
        "orange" to "#FFA500",
        "pink" to "#ffc0cb",
        "green" to "#00FF00",
        "brown" to "#964B00",
        "white" to "#FFFFFF",
        "cyan" to "#00FFFF"
    )
    private var score: Int = 0
    private lateinit var correctBox: TextView
    private lateinit var boxes: List<TextView>
    private var timer: Timer = Timer()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GameProcessFragmentBinding.inflate(layoutInflater)

        binding.box1.setOnClickListener { box1Click() }
        binding.box2.setOnClickListener { box2Click() }
        binding.box3.setOnClickListener { box3Click() }
        binding.box4.setOnClickListener { box4Click() }

        boxes = listOf(binding.box1, binding.box2, binding.box3, binding.box4)

        processGame()

        return binding.root
    }

    private fun processGame() {
        setTimer()

        binding.score.text = score.toString()

        val colorsCopy: MutableMap<String, String> = colors.toMutableMap()

        val correct = getRandomColor(colorsCopy)

        binding.colorText.text = getRandomColor(colorsCopy).key;
        binding.colorText.setTextColor(Color.parseColor(correct.value))


        val twoRandomBoxes = boxes.asSequence().shuffled().take(2).toList()
        correctBox = twoRandomBoxes.first()
        correctBox.text = correct.key

        twoRandomBoxes.last().text = binding.colorText.text

        boxes.forEach {
            if (it != correctBox && it != twoRandomBoxes.last())
                it.text = getRandomColor(colorsCopy).key
        }
    }

    private fun setTimer() {
        timer.cancel()
        timer.purge()
        timer = Timer()
        var timerCount = binding.timerProgressBar.max
        val period: Long = binding.timerProgressBar.max.toLong()

        timer.schedule(object : TimerTask() {
            override fun run() {
                timerCount--
                binding.timerProgressBar.progress = timerCount

                if (timerCount == 0) {
                    gameOver()
                }
            }
        }, 0, period)
    }

    private fun getRandomColor(map: MutableMap<String, String>): Map.Entry<String, String> {
        val element = map.map { it.key to it.value }.shuffled().toMap().entries.elementAt(0);
        map.remove(element.key)
        return element;
    }

    private fun gameOver() {
        timer.cancel()
        timer.purge()

        val bundle = Bundle()
        bundle.putInt("score", score)

        val fragment = GameOverFragment()
        fragment.arguments = bundle

        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, fragment)
            ?.commit()
    }

    private fun box1Click() {
        if (correctBox == binding.box1) {
            score++
            processGame()
        } else {
            gameOver()
        }
    }

    private fun box2Click() {
        if (correctBox == binding.box2) {
            score++;
            processGame()
        } else {
            gameOver()
        }
    }

    private fun box3Click() {
        if (correctBox == binding.box3) {
            score++;
            processGame()
        } else {
            gameOver()
        }
    }

    private fun box4Click() {
        if (correctBox == binding.box4) {
            score++;
            processGame()
        } else {
            gameOver()
        }
    }

}