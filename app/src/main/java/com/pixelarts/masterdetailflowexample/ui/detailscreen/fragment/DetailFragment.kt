package com.pixelarts.masterdetailflowexample.ui.detailscreen.fragment

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.pixelarts.masterdetailflowexample.R
import com.pixelarts.masterdetailflowexample.common.GlideApp
import com.pixelarts.masterdetailflowexample.di.ApplicationModule
import com.pixelarts.masterdetailflowexample.di.DaggerApplicationComponent
import com.pixelarts.masterdetailflowexample.di.fragment.FragmentModule
import com.pixelarts.masterdetailflowexample.model.Collection
import com.pixelarts.masterdetailflowexample.ui.detailscreen.DetailActivity
import com.pixelarts.masterdetailflowexample.ui.homescreen.HomeActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import javax.inject.Inject

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [HomeActivity]
 * in two-pane mode (on tablets) or a [DetailActivity]
 * on handsets.
 */
class DetailFragment : Fragment(), DetailFragmentContract.View {

    @Inject lateinit var presenter: DetailFragmentContract.Presenter

    private lateinit var collection: Collection
    private var twoPane: Boolean? = null

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_COLLECTION_ID)) {

                collection = it.getParcelable(ARG_COLLECTION_ID)!!
                twoPane = it.getBoolean("twoPane")
                activity?.toolbar_layout?.title = showTitle(collection)
            }
        }

        injectDependencies()
        presenter.getDetails(collection)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_detail, container, false)

        collection.let {
            if (twoPane!!){
                rootView.ivArticleImage.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                rootView.ivArticleImage.layoutParams.height = 850
                rootView.ivArticleImage.scaleType = ImageView.ScaleType.FIT_XY

                GlideApp.with(this).load(it.pictureUrl)
                    .into(rootView.ivArticleImage)
            }
            else{
                GlideApp.with(this).load(it.pictureUrl)
                    .centerCrop()
                    .into(rootView.ivArticleImage)
            }

            rootView.tvArticleHeadline.text = it.headline
            rootView.tvArticleDescription.text = "${it.description}: ${it.synopsis}"
            rootView.tvActors.text = it.actors.joinToString(", ", "Actors: ", "",-1, "")
            rootView.tvDirector.text = "Director: ${it?.director}"
            rootView.tvGenre.text = it.genre.joinToString(", ", "Genres: ", "", -1, "")
            rootView.tvReleaseDate_Duration.text = "Release date: ${it.releaseDate} \nDuration: ${it.duration} \n\nPublished date: ${it.publishedDate}\n\nPublished by: "
            rootView.ratingBar.rating = it.ratings.toFloat()

            GlideApp.with(this).load(it.author.headshot)
                .circleCrop()
                .override(400)
                .into(rootView.ivAuthorImage)

            rootView.tvAuthor.text = "${it.author.name}\nTwitter: ${it.author.twitter}"
        }

        return rootView
    }

    private fun injectDependencies(){

        if(twoPane!!){
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(activity as HomeActivity))
                .build()
                .newFragmentComponent(FragmentModule(this))
                .injectDetailFragment(this)
        }
        else{
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(activity as DetailActivity))
                .build()
                .newFragmentComponent(FragmentModule(this))
                .injectDetailFragment(this)
        }

    }

    override fun showTitle(collection: Collection): String = collection.headline

    override fun showMessage(message: String) {

    }

    override fun showError(error: String) {

    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_COLLECTION_ID = "collection"
    }
}
