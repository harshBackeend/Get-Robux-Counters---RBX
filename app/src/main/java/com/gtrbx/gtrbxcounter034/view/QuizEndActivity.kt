package com.gtrbx.gtrbxcounter034.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.gtrbx.gtrbxcounter034.R
import com.gtrbx.gtrbxcounter034.databinding.ActivityQuizEndBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.view.pop.ScratchDialog
import com.gtrbx.gtrbxcounter034.workData.AppDataHolder
import com.gtrbx.gtrbxcounter034.workData.QuizListModel
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder

class QuizEndActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizEndBinding
    private val quizList: ArrayList<QuizListModel> = arrayListOf()
    private lateinit var selectedQuiz: QuizListModel
    private var selectedAns: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizEndBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addQuizInList()

        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        // Show the system bars.
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        binding.layoutActionBar.textDisplayTitle.text = getString(R.string.robox_quiz)
        setDataRandom()

        StartApplication.spinRowResponse.observe(this) {
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1") {
                binding.layoutBottomHolder.visibility = View.VISIBLE
                showData(it.first!!)
            } else {
                binding.layoutBottomHolder.visibility = View.GONE
            }
            setPushEvent(it?.first)
            backEvent(it?.first)
        }

        binding.layoutBottom.textSub.isSelected = true
    }

    private fun setDataRandom() {
        selectedQuiz = quizList.random()

        if (!UseMe.isSpinRowEmptyString(selectedQuiz.quiz)) {
            binding.textQuiz.text = getString(R.string.quiz_ans, selectedQuiz.quiz)
        }

        if (selectedQuiz.quizAnsList.isNotEmpty() && selectedQuiz.quizAnsList.size == 4) {
            binding.textAns1.text = selectedQuiz.quizAnsList[0]
            binding.textAns2.text = selectedQuiz.quizAnsList[1]
            binding.textAns3.text = selectedQuiz.quizAnsList[2]
            binding.textAns4.text = selectedQuiz.quizAnsList[3]
        }
        selectedAns = null

        setButtonEvent(false, false, false, false)
    }

    private fun addQuizInList() {
        quizList.apply {
            add(
                QuizListModel(
                    quiz = getString(R.string._25_25),
                    quizAns = getString(R.string._625),
                    quizAnsList = arrayListOf(
                        getString(R.string._50),
                        getString(R.string._100), getString(R.string._125), getString(R.string._625)
                    )
                )
            )
            add(
                QuizListModel(
                    quiz = getString(R.string._100_25),
                    quizAns = getString(R.string._4),
                    quizAnsList = arrayListOf(
                        getString(R.string._4), getString(R.string._50),
                        getString(
                            R.string._75
                        ), getString(R.string._625)
                    )
                )
            )
            add(
                QuizListModel(
                    quiz = getString(R.string._85_56),
                    quizAns = getString(R.string._29),
                    quizAnsList = arrayListOf(getString(R.string._40),
                        getString(R.string._30), getString(R.string._29), getString(R.string._141))
                )
            )
            add(
                QuizListModel(
                    quiz = getString(R.string._25_23),
                    quizAns = getString(R.string._2),
                    quizAnsList = arrayListOf(getString(R.string._2),
                        getString(R.string._3), getString(R.string._0), getString(R.string._1))
                )
            )
            add(
                QuizListModel(
                    quiz = getString(R.string._6_3),
                    quizAns = getString(R.string._3),
                    quizAnsList = arrayListOf(getString(R.string._4), getString(R.string._2), getString(R.string._3),
                        getString(
                            R.string._8
                        ))
                )
            )
            add(
                QuizListModel(
                    quiz = getString(R.string._28_4),
                    quizAns = getString(R.string._112),
                    quizAnsList = arrayListOf(getString(R.string._0),
                        getString(R.string._200), getString(R.string._100), getString(R.string._112))
                )
            )
            add(
                QuizListModel(
                    quiz = getString(R.string._13_13),
                    quizAns = getString(R.string._169),
                    quizAnsList = arrayListOf(
                        getString(R.string._123), getString(R.string._112), getString(R.string._169),
                        getString(R.string._111))
                )
            )
        }
    }

    private fun setPushEvent(first: SpinRowDataHolder?) {
        binding.layoutActionBar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.textAns1.setOnClickListener {
            if (!UseMe.isSpinRowEmptyString(selectedAns) && selectedAns == binding.textAns1.text) {
                selectedAns = null
                setButtonEvent(false, false, false, false)
            } else {
                selectedAns = binding.textAns1.text.toString()
                setButtonEvent(true, false, false, false)
            }
        }

        binding.textAns2.setOnClickListener {
            if (!UseMe.isSpinRowEmptyString(selectedAns) && selectedAns == binding.textAns2.text) {
                selectedAns = null
                setButtonEvent(false, false, false, false)
            } else {
                selectedAns = binding.textAns2.text.toString()
                setButtonEvent(false, true, false, false)
            }
        }

        binding.textAns3.setOnClickListener {
            if (!UseMe.isSpinRowEmptyString(selectedAns) && selectedAns == binding.textAns3.text) {
                selectedAns = null
                setButtonEvent(false, false, false, false)
            } else {
                selectedAns = binding.textAns3.text.toString()
                setButtonEvent(false, false, true, false)
            }
        }

        binding.textAns4.setOnClickListener {
            if (!UseMe.isSpinRowEmptyString(selectedAns) && selectedAns == binding.textAns4.text.toString()) {
                selectedAns = null
                setButtonEvent(false, false, false, false)
            } else {
                selectedAns = binding.textAns4.text.toString()
                setButtonEvent(false, false, false, true)
            }
        }

        binding.buttonSubmitAnswer.setOnClickListener {
            if (!UseMe.isSpinRowEmptyString(selectedAns)) {
                if (selectedAns == selectedQuiz.quizAns) {
                    ScratchDialog.viewScratchDialog(
                        this@QuizEndActivity,
                        "50",
                        object :
                            ScratchDialog.CloseEvent {
                            override fun close() {
                                var rank = UseMe.getDataFromLocal(
                                    this@QuizEndActivity,
                                    AppDataHolder.AppDataKey.rank
                                )
                                if (!UseMe.isSpinRowEmptyString(rank)) {
                                    var rankU: Int = rank!!.toInt()
                                    rank = "${rankU + 50}"
                                }

                                UseMe.setDataHolder(
                                    this@QuizEndActivity,
                                    AppDataHolder.AppDataKey.rank,
                                    rank
                                )
                                updateRank()
                                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(
                                        first.spin_status
                                    ) && first.spin_status == "1"
                                ) {
                                    StartApplication.showSpinRowTab(
                                        this@QuizEndActivity,
                                        first.spin_small_2.random().spin_main_image?.toUri()
                                    )
                                }
                                setDataRandom()
                            }
                        })
                } else {
                    Toast.makeText(
                        this@QuizEndActivity,
                        getString(R.string.please_select_right_answer), Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@QuizEndActivity,
                    getString(R.string.please_select_answers), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setButtonEvent(b1: Boolean, b2: Boolean, b3: Boolean, b4: Boolean) {
        if (b1) {
            binding.textAns1.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bg_anser_r_10_stock_2
                )
            )
        } else {
            binding.textAns1.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bg_anser_r_10
                )
            )
        }

        if (b2) {
            binding.textAns2.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bg_anser_r_10_stock_2
                )
            )
        } else {
            binding.textAns2.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bg_anser_r_10
                )
            )
        }

        if (b3) {
            binding.textAns3.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bg_anser_r_10_stock_2
                )
            )
        } else {
            binding.textAns3.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bg_anser_r_10
                )
            )
        }

        if (b4) {
            binding.textAns4.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bg_anser_r_10_stock_2
                )
            )
        } else {
            binding.textAns4.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bg_anser_r_10
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        binding.layoutActionBar.layoutCoinHolder.visibility = View.VISIBLE
        updateRank()
    }

    private fun updateRank() {
        val rank = UseMe.getDataFromLocal(
            this@QuizEndActivity,
            AppDataHolder.AppDataKey.rank
        )
        if (!UseMe.isSpinRowEmptyString(rank)) {
            binding.layoutActionBar.textCoinHolder.text = rank
        }
    }

    private fun showData(model: SpinRowDataHolder) {

        if (model.spin_big != null) {
            SmallView2.smallViewFunctionality(
                this@QuizEndActivity,
                model,
                binding.layoutBottom
            )
        } else {
            binding.layoutBottomHolder.visibility = View.GONE
        }
    }

    private fun backEvent(holder: SpinRowDataHolder?) {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (holder != null && holder.spin_small_2 != null && !UseMe.isSpinRowEmptyString(
                        holder.spin_status
                    ) && holder.spin_status == "1" && holder.spin_small_2.isNotEmpty()
                ) {
                    StartApplication.showSpinRowTab(
                        this@QuizEndActivity,
                        holder.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}