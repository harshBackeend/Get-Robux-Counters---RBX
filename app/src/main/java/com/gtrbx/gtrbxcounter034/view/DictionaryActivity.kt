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
import com.gtrbx.gtrbxcounter034.databinding.ActivityDictionaryBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.view.adapter.ViewAdapter
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder

class DictionaryActivity : AppCompatActivity(), ViewAdapter.ClickEvent {

    private lateinit var binding: ActivityDictionaryBinding
    private lateinit var viewAdapter: ViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDictionaryBinding.inflate(layoutInflater)
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

        binding.layoutActionBar.textDisplayTitle.text = getString(R.string.dictionary)

        viewAdapter = ViewAdapter(this)

        StartApplication.spinRowResponse.observe(this) {
            val listView = addDictionary()
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

        binding.listOfDictionary.adapter = viewAdapter
    }

    private fun setPushEvent() {
        binding.layoutActionBar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun addDictionary(): ArrayList<SpinRowDataHolder.ListModel> {
        return arrayListOf<SpinRowDataHolder.ListModel>(

            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.aa),
                subTitleHolder = getString(R.string.aa_is_an_acronym_for_admin_abuse_which_is_when_an_administrator_misuses_their_administrative_powers),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string._13),
                subTitleHolder = getString(R.string._13_means_that_something_is_only_accessible_or_recommended_to_players_who_are_aged_13_or_over),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string._1337),
                subTitleHolder = getString(R.string._1337_is_another_way_to_type_the_word_leet_as_1_looks_like_l_3_looks_like_e_and_7_looks_like_t_when_used_in_roblox_1337_usually_means_elite_which_is_the_word_that_leet_comes_from_1337_can_also_refer),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.abc),
                subTitleHolder = getString(R.string.abc_isn_t_an_acronym_and_doesn_t_mean_anything_specific_it_is_a_word_that_people_use_to_respond_to_a_request_from_another_roblox_player_for_example_one_player_might_say_abc_for_free_gear),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.afk),
                subTitleHolder = getString(R.string.afk_is_an_acronym_for_the_phrase_away_from_keyboard_away_from_keyboard_means_quite_literally_that_a_player_is_going_to_be_away_from_their_computer_and_not_available_for_a_period_of),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.alias),
                subTitleHolder = getString(R.string.in_roblox_alias_refers_to_aliases_set_through_the_alias_feature_the_alias_feature_allows_players_to_set_an_alias_meaning_a_nickname_for_their_friends_aliases_are_only_visible_to_those_who),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.alt),
                subTitleHolder = getString(R.string.in_roblox_alt_is_a_shortened_form_of_the_word_alternate_account_an_alternate_account_is_another_roblox_account_that_a_player_uses_that_isn_t_a_their_main_account_for_example_if_a_player_had_two),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.annually),
                subTitleHolder = getString(R.string.in_roblox_annually_means_the_same_as_it_does_in_the_real_word_that_something_happens_once_a_year_if_a_subscription_to_roblox_premium_is_charged_annually_that_means_you_pay_once_a),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.beta),
                subTitleHolder = getString(R.string.in_roblox_beta_refers_to_a_version_of_a_game_or_other_object_like_an_asset_that_is_available_to_play_or_use_as_a_trial_because_it_isn_t_yet_fully_finished_or_tested_games_and_assets_in_a_beta_phase_are),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.brb),
                subTitleHolder = getString(R.string.brb_is_short_for_be_right_back_which_means_quite_literally_that_a_player_will_be_unavailable_for_a_short_period_of_time_e_g_30_seconds_but_they_will_be_right_back_for_example_if_a_player_is_leaving),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.chile),
                subTitleHolder = getString(R.string.in_roblox_chile_can_mean_one_of_two_things_it_can_mean_chill_as_in_chilled_out_and_relaxed_or_it_can_mean_child_as_in_a_young_person),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.dec),
                subTitleHolder = getString(R.string.in_roblox_the_word_dec_is_used_before_numbers_and_words_in_an_attempt_to_bypass_the_chat_filter_because_roblox_picks_up_dec_as_december_it_may_mistakenly_allow_certain_banned_phrases_or),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.disc),
                subTitleHolder = getString(R.string.disc_is_a_shortened_form_of_the_word_discord_which_refers_to_the_chat_application_discord_is_a_banned_word_on_roblox_to_help_protect_members_of_the_community_from_scams_and_inappropriate),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.e_e),
                subTitleHolder = getString(R.string.in_roblox_e_e_or_e_e_is_a_smilie_i_e_it_s_meant_to_look_like_a_face_that_signifies_boredom_or_exhaustion),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.fe),
                subTitleHolder = getString(R.string.in_roblox_fe_is_an_acronym_for_filtering_enabled_when_filtering_is_enabled_for_a_game_it_means_that_changes_made_on_the_client_aren_t_replicated_on_the_server_this_prevents_cheaters_from_inserting),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.ffs),
                subTitleHolder = getString(R.string.in_roblox_ffs_is_short_for_the_phrase_for_fudge_sake_this_is_a_figure_of_speech_that_is_used_to_express_frustration_or_annoyance_sometimes_fudge_is_replaced_with_a_much_more_profane_word),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.lel),
                subTitleHolder = getString(R.string.in_roblox_lel_is_a_more_playful_and_sarcastic_way_to_say_lol_which_means_laughing_out_loud_this_means_lel_is_often_used_to_when_something_is_funny_or_amusing),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.limited_u),
                subTitleHolder = getString(R.string.in_roblox_limited_u_meaning_limited_unique_items_are_items_that_only_have_a_limited_quantity_available_before_they_are_discontinued_for_example_a_limited_u_hat_may_only_be_sold_5_000),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.gfx),
                subTitleHolder = getString(R.string.in_roblox_gfx_has_two_meanings_its_first_and_most_simple_is_a_shortened_form_of_the_word_graphics_its_second_meaning_refers_to_high_quality_rendered_images_of_roblox),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.gg),
                subTitleHolder = getString(R.string.in_roblox_gg_is_short_for_the_phrase_good_game_which_is_the_gaming_equivalent_of_the_phrase_good_job_or_nice_it_is_often_used_at_the_end_of_games_to_congratulate_the_other_team_it_may_also_be_used),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.hr),
                subTitleHolder = getString(R.string.in_roblox_hr_is_an_acronym_for_the_phrase_high_rank_which_refers_to_players_who_have_high_ranks_on_a_server_i_e_they_have_more_power_and_access_to_more_features_or_group),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.limited_u),
                subTitleHolder = getString(R.string.in_roblox_limited_u_meaning_limited_unique_items_are_items_that_only_have_a_limited_quantity_available_before_they_are_discontinued_for_example_a_limited_u_hat_may_only_be_sold_5_000),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.lmad),
                subTitleHolder = getString(R.string.in_roblox_lmad_is_an_acronym_for_let_s_make_a_deal_this_acronym_is_used_when_two_or_more_players_want_to_make_an_agreement_for_example_to_trade_a_certain_item_it_was_also_the_name_of),
                bigLayout = null
            ),


            SpinRowDataHolder.ListModel(
                viewType = 0,
                titleHolder = getString(R.string.lmao),
                subTitleHolder = getString(R.string.in_roblox_lmao_means_laughing_my_a_off_which_is_an_expression_used_to_indicate_that_someone_found_something_really_funny),
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
                this@DictionaryActivity,
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
                        this@DictionaryActivity,
                        holder.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }


}