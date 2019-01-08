package com.pixelarts.masterdetailflowexample.ui.homescreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixelarts.masterdetailflowexample.ui.detailscreen.DetailActivity
import com.pixelarts.masterdetailflowexample.R
import com.pixelarts.masterdetailflowexample.adapter.ListItemAdapter
import com.pixelarts.masterdetailflowexample.base.BaseActivity
import com.pixelarts.masterdetailflowexample.di.ApplicationModule
import com.pixelarts.masterdetailflowexample.di.DaggerApplicationComponent

import com.pixelarts.masterdetailflowexample.dummy.DummyContent
import com.pixelarts.masterdetailflowexample.model.Collection
import com.pixelarts.masterdetailflowexample.ui.detailscreen.fragment.DetailFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import kotlinx.android.synthetic.main.item_list.*
import javax.inject.Inject

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [DetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class HomeActivity : BaseActivity<HomeContract.Presenter>(), HomeContract.View, ListItemAdapter.OnItemClickedListener {

    @Inject lateinit var presenter: HomeContract.Presenter

    private lateinit var adapter: ListItemAdapter
    private lateinit var collection: List<Collection>

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        toolbar.title = title

        presenter.getArticles()

        if (item_detail_container != null) {
            twoPane = true
        }

        adapter = ListItemAdapter(this)
        setupRecyclerView(item_list)
    }

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build().injectHomeScreen(this)
    }

    override fun getPresenterView(): HomeContract.Presenter = presenter

    override fun showArticles(collection: List<Collection>) {
        adapter.submitList(collection)

        this.collection = collection
    }

    override fun itemClickedListener(position: Int) {
        if (twoPane) {
            val fragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DetailFragment.ARG_COLLECTION_ID, collection[position])
                    putBoolean("twoPane", twoPane)
                }
            }
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailFragment.ARG_COLLECTION_ID, collection[position])
            }
            startActivity(intent)
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = adapter
    }
}
