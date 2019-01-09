package com.pixelarts.masterdetailflowexample.ui.detailscreen

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.pixelarts.masterdetailflowexample.R
import com.pixelarts.masterdetailflowexample.base.BaseActivity
import com.pixelarts.masterdetailflowexample.di.ApplicationComponent
import com.pixelarts.masterdetailflowexample.di.ApplicationModule
import com.pixelarts.masterdetailflowexample.di.DaggerApplicationComponent
import com.pixelarts.masterdetailflowexample.model.Collection
import com.pixelarts.masterdetailflowexample.ui.detailscreen.fragment.DetailFragment
import com.pixelarts.masterdetailflowexample.ui.homescreen.HomeActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [HomeActivity].
 */
class DetailActivity : BaseActivity<DetailContract.Presenter>(), DetailContract.View {
    @Inject lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {

            val fragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    val collection: Collection = intent.getParcelableExtra(DetailFragment.ARG_COLLECTION_ID)
                    putParcelable(DetailFragment.ARG_COLLECTION_ID, collection)
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
            .injectDetailScreen(this)
    }

    override fun getPresenterView(): DetailContract.Presenter = presenter

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                navigateUpTo(Intent(this, HomeActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
