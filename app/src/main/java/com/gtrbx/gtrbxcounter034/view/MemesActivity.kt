package com.gtrbx.gtrbxcounter034.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.gtrbx.gtrbxcounter034.R
import com.gtrbx.gtrbxcounter034.databinding.ActivityMemesBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.view.adapter.ViewAdapter
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder

class MemesActivity : AppCompatActivity(), ViewAdapter.ClickEvent {

    private lateinit var binding: ActivityMemesBinding
    private lateinit var viewAdapter: ViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemesBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        // Show the system bars.
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        binding.layoutActionBar.textDisplayTitle.text = getString(R.string.memes)

        viewAdapter = ViewAdapter(this)

        StartApplication.spinRowResponse.observe(this) {
            val listView = addMemes()
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1" && it.first?.spin_big != null && it.first?.spin_big?.size!! > 0) {
                for (i in listView.size downTo 0) {
                    if (i % 4 == 0) {
                        listView.add(
                            i,
                            SpinRowDataHolder.ListModel(bigLayout = it.first?.spin_big?.random())
                        )
                    }
                }
            }
            viewAdapter.viewAdd(listView)
            setPushEvent()
            backEvent(it?.first)
        }
        binding.listOfMemes.adapter = viewAdapter
    }

    private fun setPushEvent() {
        binding.layoutActionBar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun addMemes(): ArrayList<SpinRowDataHolder.ListModel> {
        return arrayListOf(

            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = "Robx Budgeting",
                subTitleHolder = "Me: I'll save my Robx for something special. Also me: buys a hat for 5 Robx",
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.robx_vs_reality),
                subTitleHolder = getString(R.string.expectation_my_avatar_looks_amazing_reality_wears_a_free_shirt_and_pants),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.when_you_get_robx),
                subTitleHolder = getString(R.string.me_after_getting_robx_spends_it_all_on_useless_items),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.robx_scams),
                subTitleHolder = getString(R.string.when_you_realize_that_free_robx_was_just_a_scam_facepalm),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.game_updates),
                subTitleHolder = getString(R.string.when_the_game_updates_and_you_have_to_relearn_everything_why_roblox_why),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.robx_for_game_passes),
                subTitleHolder = getString(R.string.me_i_ll_never_buy_a_game_pass_also_me_spends_800_robx_on_a_double_jump),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_fashion),
                subTitleHolder = getString(R.string.when_you_see_someone_with_a_limited_edition_item_i_m_not_worthy),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.robx_inflation),
                subTitleHolder = getString(R.string.when_you_realize_that_100_robx_doesn_t_buy_what_it_used_to_inflation_is_real),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_friends),
                subTitleHolder = getString(R.string.when_your_friend_says_they_ll_help_you_in_a_game_but_just_stands_there_thanks_for_nothing),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_obby),
                subTitleHolder = getString(R.string.me_this_obby_looks_easy_also_me_falls_on_the_first_jump),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.robx_trade_offers),
                subTitleHolder = getString(R.string.when_someone_offers_you_a_trade_that_s_clearly_not_fair_nice_try_buddy),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_roleplay),
                subTitleHolder = getString(R.string.when_you_join_a_roleplay_server_and_everyone_is_super_serious_am_i_in_the_right_place),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_events),
                subTitleHolder = getString(R.string.when_you_miss_a_limited_time_event_crying_in_robx),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_updates_1),
                subTitleHolder = getString(R.string.when_the_game_updates_and_you_have_to_relearn_the_controls_why_do_they_keep_changing_things),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.robx_goals),
                subTitleHolder = getString(R.string.me_i_ll_save_up_for_that_cool_item_also_me_spends_it_all_on_random_stuff),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_friends),
                subTitleHolder = getString(R.string.when_your_friend_says_they_ll_join_you_but_takes_forever_i_m_waiting),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_game_genres),
                subTitleHolder = getString(R.string.when_you_try_a_new_game_genre_and_it_s_nothing_like_you_expected_what_is_this),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_builders),
                subTitleHolder = getString(R.string.when_you_see_a_beautifully_built_game_i_can_barely_build_a_box),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_pets),
                subTitleHolder = getString(R.string.when_you_finally_get_that_rare_pet_i_m_never_letting_you_go),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_rage),
                subTitleHolder = getString(R.string.when_you_die_for_the_10th_time_in_a_row_i_m_done_with_this_game),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_friends_1),
                subTitleHolder = getString(R.string.when_your_friend_gets_a_rare_item_and_you_don_t_i_m_happy_for_you_i_guess),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_game_passes),
                subTitleHolder = getString(R.string.when_you_buy_a_game_pass_and_it_doesn_t_work_what_a_scam),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_updates),
                subTitleHolder = getString(R.string.when_the_game_updates_and_you_have_to_start_from_scratch_why_roblox_why),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_fashion_1),
                subTitleHolder = getString(R.string.when_you_see_someone_with_a_cool_outfit_i_need_that_in_my_life),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roblox_community),
                subTitleHolder = getString(R.string.when_you_find_a_group_of_friends_who_love_roblox_as_much_as_you_do_finally_my_people),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.relatable_gamer_meme_1),
                subTitleHolder = getString(R.string.check_out_this_meme_when_your_wifi_disconnects_mid_game),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.fun_fact_about_robx),
                subTitleHolder = getString(R.string.did_you_know_robx_has_over_40_million_games_created_by_users),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.gaming_joke),
                subTitleHolder = getString(R.string.why_did_the_gamer_bring_a_ladder_to_reach_the_next_level),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.relatable_gamer_meme),
                subTitleHolder = getString(R.string.pov_you_re_in_the_middle_of_a_boss_fight_and_the_wifi_says_goodbye),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.friend_joins_after_game_ends),
                subTitleHolder = getString(R.string.when_your_friend_joins_the_server_right_after_you_rage_quit),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.lag_kills_you),
                subTitleHolder = getString(R.string.robx_it_s_not_you_it_s_the_lag_but_you_still_died),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.spawn_trapping),
                subTitleHolder = getString(R.string.spawning_in_the_middle_of_chaos_like_guess_i_ll_die),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.afk_teammates),
                subTitleHolder = getString(R.string.afk_teammates_legends_who_do_nothing_but_still_win),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.accidental_leave),
                subTitleHolder = getString(R.string.spent_an_hour_building_accidentally_hits_the_leave_button),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.pet_simulator_flex),
                subTitleHolder = getString(R.string.friend_look_at_my_rare_pet_me_that_s_great_but_i_have_two_cats),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.tower_of_hell),
                subTitleHolder = getString(R.string.tower_of_hell_be_like_you_fell_start_over_loser),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.adopt_me_trades),
                subTitleHolder = getString(R.string.friend_fair_trade_only_proceeds_to_scam_me_for_my_legendary_pet),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.simulator_grinding),
                subTitleHolder = getString(R.string.spends_5_hours_grinding_for_one_extra_level),
                bigLayout = null
            ),
            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.roleplay_troll),
                subTitleHolder = getString(R.string.when_the_whole_server_is_roleplaying_but_you_re_just_there_to_troll),
                bigLayout = null
            )
        )


    }

    override fun smallViewClick(model: SpinRowDataHolder.ListModel) {
        val model = StartApplication.spinRowResponse.value
        if (model?.first != null &&
            model.first?.spin_small_2 != null &&
            !UseMe.isSpinRowEmptyString(model.first?.spin_status) &&
            model.first?.spin_status == "1" &&
            model.first?.spin_small_2 != null &&
            model.first?.spin_small_2!!.isNotEmpty()
        ) {
            StartApplication.showSpinRowTab(
                this@MemesActivity,
                model.first?.spin_small_2?.random()?.spin_main_image?.toUri()
            )
        }
    }

    override fun mainViewClick(model: SpinRowDataHolder.ListModel) {

    }

    override fun mainViewShareClick(model: SpinRowDataHolder.ListModel) {
        if (!UseMe.isSpinRowEmptyString(model.titleHolder) && !UseMe.isSpinRowEmptyString(model.subTitleHolder)) {
            val intent: Intent = Intent().setAction(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, "${model.titleHolder}\n${model.subTitleHolder}")
            startActivity(Intent.createChooser(intent, "Share via"))
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
                        this@MemesActivity,
                        holder.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}