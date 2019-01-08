package com.pixelarts.masterdetailflowexample.ui.detailscreen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import com.pixelarts.masterdetailflowexample.R
import com.pixelarts.masterdetailflowexample.common.GlideApp
import com.pixelarts.masterdetailflowexample.dummy.DummyContent
import com.pixelarts.masterdetailflowexample.model.Collection
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [HomeActivity]
 * in two-pane mode (on tablets) or a [DetailActivity]
 * on handsets.
 */
class DetailFragment : Fragment() {

    private var collection: Collection? = null
    private var twoPane: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_COLLECTION_ID)) {

                collection = it.getParcelable(ARG_COLLECTION_ID)
                twoPane = it.getBoolean("twoPane")
                activity?.toolbar_layout?.title = collection?.headline
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)

        collection.let {
            if (twoPane!!){
                rootView.ivArticleImage.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                rootView.ivArticleImage.layoutParams.height = 850
                rootView.ivArticleImage.scaleType = ImageView.ScaleType.FIT_XY

                GlideApp.with(this).load(it?.pictureUrl)
                    .into(rootView.ivArticleImage)
            }
            else{
                GlideApp.with(this).load(it?.pictureUrl)
                    .centerCrop()
                    .into(rootView.ivArticleImage)
            }

            rootView.tvArticleHeadline.text = it?.headline
            rootView.tvArticleDescription.text = "${it?.description}: ${it?.synopsis}"
            rootView.tvActors.text = it?.actors?.joinToString(", ", "Actors: ", "",-1, "")
            rootView.tvDirector.text = "Director: ${it?.director}"
            rootView.tvGenre.text = it?.genre?.joinToString(", ", "Genres: ", "", -1, "")
            rootView.tvReleaseDate_Duration.text = "Release date: ${it?.releaseDate} \nDuration: ${it?.duration} \n\nPublished date: ${it?.publishedDate}\n\nPublished by: "
            rootView.ratingBar.rating = it?.ratings!!.toFloat()

            GlideApp.with(this).load(it.author.headshot)
                .circleCrop()
                .override(400)
                .into(rootView.ivAuthorImage)

            rootView.tvAuthor.text = "${it.author.name}\nTwitter: ${it.author.twitter}"
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_COLLECTION_ID = "collection"
    }
}
