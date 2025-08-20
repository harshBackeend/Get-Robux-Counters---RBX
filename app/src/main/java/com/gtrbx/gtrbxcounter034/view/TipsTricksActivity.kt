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
import com.gtrbx.gtrbxcounter034.databinding.ActivityTipsTricksBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.view.adapter.ViewAdapter
import com.gtrbx.gtrbxcounter034.workData.AppDataHolder
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder
import kotlin.jvm.java

class TipsTricksActivity : AppCompatActivity(), ViewAdapter.ClickEvent {

    private lateinit var binding: ActivityTipsTricksBinding
    private lateinit var viewAdapter: ViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipsTricksBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        // Show the system bars.
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            true

        binding.layoutActionBar.textDisplayTitle.text = getString(R.string.tips_amp_tricks)

        viewAdapter = ViewAdapter(this)

        StartApplication.spinRowResponse.observe(this) {
            val listView = addTipsTricks()
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1" && it.first?.spin_big != null && it.first?.spin_big?.size!! > 0) {
                for (i in listView.size downTo 0) {
                    if (i % 4 == 0) {
                        listView.add(
                            i, SpinRowDataHolder.ListModel(bigLayout = it.first?.spin_big?.random())
                        )
                    }
                }
            }
            viewAdapter.viewAdd(listView)
            setPushEvent(it?.first)
            backEvent(it?.first)
        }

        binding.listOfTipsAndTricks.adapter = viewAdapter
    }

    private fun setPushEvent(first: SpinRowDataHolder?) {
        binding.layoutActionBar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun addTipsTricks(): ArrayList<SpinRowDataHolder.ListModel> {
        return arrayListOf(
            SpinRowDataHolder.ListModel(
                viewType = 2,
                titleHolder = getString(R.string.navigating_the_robx_app),
                subTitleHolder = getString(R.string.the_robx_app_is_designed_with_simplicity_and_user_friendliness_in_mind_upon_opening_the_app_youll_be_greeted_with_a_clean_interface_that_showcases_the_core_functionalities_the_main_menu_will_present_you_with_options_for_purchasing_robx_managing_your_existing_robx_balance_and_exploring_the_world_of_in_game_items_and_experiences_you_can_easily_access_the_apps_settings_for_customizing_your_experience_and_managing_your_account),
                bigLayout = null
            ), SpinRowDataHolder.ListModel(
                viewType = 2,
                titleHolder = getString(R.string.master_the_marketplace),
                subTitleHolder = getString(R.string.the_marketplace_is_your_treasure_chest_of_in_game_goodies_dont_just_browse_randomly_use_filters_to_narrow_down_your_search_look_for_items_that_align_with_your_current_game_interests_want_new_clothes_for_your_favorite_avatar_filter_by_category_price_and_popularity_always_check_out_the_featured_section_for_trending_items),
                bigLayout = null
            ), SpinRowDataHolder.ListModel(
                viewType = 2,
                titleHolder = getString(R.string.utilizing_robx_in_game),
                subTitleHolder = getString(R.string.once_you_have_acquired_robx_you_can_use_them_to_purchase_a_variety_of_in_game_items_and_experiences_these_include_cosmetic_items_like_avatars_and_accessories_access_to_premium_game_features_and_exclusive_experiences_within_various_roblox_games),
                bigLayout = null
            ), SpinRowDataHolder.ListModel(
                viewType = 2,
                titleHolder = getString(R.string.managing_your_robx_balance),
                subTitleHolder = getString(R.string.the_app_provides_a_clear_overview_of_your_current_robx_balance_you_can_easily_track_your_spending_and_monitor_your_robx_balance_to_make_informed_decisions_the_app_also_shows_your_recent_purchase_history_allowing_you_to_review_your_transactions),
                bigLayout = null
            ), SpinRowDataHolder.ListModel(
                viewType = 2,
                titleHolder = getString(R.string.redeeming_robx_codes),
                subTitleHolder = getString(R.string.if_you_have_received_a_robx_code_from_a_friend_or_a_promotional_campaign_you_can_redeem_it_directly_within_the_app_navigate_to_the_redeem_codes_section_and_enter_the_code_accurately_your_robx_will_be_instantly_added_to_your_account_ready_to_be_used),
                bigLayout = null
            ), SpinRowDataHolder.ListModel(
                viewType = 2,
                titleHolder = getString(R.string.exploring_the_robx_marketplace),
                subTitleHolder = getString(R.string.the_robx_marketplace_is_where_you_can_discover_a_wide_selection_of_digital_items_and_experiences_to_enhance_your_roblox_gameplay_explore_categories_like_clothing_accessories_emotes_and_game_passes_you_can_filter_your_search_by_price_popularity_and_other_criteria_to_quickly_find_what_youre_looking_for),
                bigLayout = null
            ), SpinRowDataHolder.ListModel(
                viewType = 2,
                titleHolder = getString(R.string.staying_informed_with_app_updates),
                subTitleHolder = getString(R.string.the_robx_app_is_regularly_updated_with_new_features_improvements_and_bug_fixes_make_sure_to_check_for_updates_and_install_them_regularly_to_ensure_youre_always_enjoying_the_latest_features_and_a_seamless_experience),
                bigLayout = null
            ), SpinRowDataHolder.ListModel(
                viewType = 2,
                titleHolder = getString(R.string.engaging_with_the_community),
                subTitleHolder = getString(R.string.the_app_also_serves_as_a_gateway_to_connect_with_the_vibrant_roblox_community_you_can_join_forums_participate_in_discussions_and_share_your_experiences_with_other_gamers_engaging_with_fellow_players_can_provide_valuable_insights_into_the_latest_trends_popular_games_and_tipsfor_maximizing_your_robx_usage_building_connections_within_the_community_can_enhance_your_overall_gaming_experience),
                bigLayout = null
            ), SpinRowDataHolder.ListModel(
                viewType = 2,
                titleHolder = getString(R.string.become_a_robx_savvy_buyer),
                subTitleHolder = getString(R.string.dont_just_impulsively_buy_everything_that_catches_your_eye_plan_your_purchases_look_for_bundles_which_often_offer_a_better_value_for_your_robx_compare_prices_for_similar_items_across_different_sellers_and_remember_robx_can_be_used_for_more_than_just_cosmetic_items_game_passes_and_other_in_game_experiences_can_be_incredible_investments),
                bigLayout = null
            ), SpinRowDataHolder.ListModel(
                viewType = 2,
                titleHolder = getString(R.string.hunt_for_hidden_deals),
                subTitleHolder = getString(R.string.the_marketplace_is_a_dynamic_environment_so_prices_can_fluctuate_keep_checking_back_on_items_you_want_some_items_might_be_marked_down_or_offered_as_part_of_limited_time_promotions_be_patient_and_you_might_find_yourself_lucky_enough_to_snag_a_coveted_item_at_a_bargain_price),
                bigLayout = null
            )
        )


    }

    override fun smallViewClick(model: SpinRowDataHolder.ListModel) {

    }

    override fun mainViewClick(model: SpinRowDataHolder.ListModel) {
        if (!UseMe.isSpinRowEmptyString(model.subTitleHolder) &&
            !UseMe.isSpinRowEmptyString(model.titleHolder)
        ) {
            startActivity(
                Intent(
                    this@TipsTricksActivity,
                    TipsTricksDetailActivity::class.java
                ).apply {
                    putExtra(AppDataHolder.AppDataKey.DISPLAY_NAME, model.titleHolder)
                    putExtra(AppDataHolder.AppDataKey.DISPLAY_SUB_NAME, model.subTitleHolder)
                })
        }
    }

    override fun mainViewShareClick(model: SpinRowDataHolder.ListModel) {

    }

    private fun backEvent(holder: SpinRowDataHolder?) {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (holder != null && holder.spin_small_2 != null && !UseMe.isSpinRowEmptyString(
                        holder.spin_status
                    ) && holder.spin_status == "1" && holder.spin_small_2.isNotEmpty()
                ) {
                    StartApplication.showSpinRowTab(
                        this@TipsTricksActivity,
                        holder.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}