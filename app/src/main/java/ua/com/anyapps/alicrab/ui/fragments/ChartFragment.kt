package ua.com.anyapps.alicrab.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import com.google.android.material.appbar.MaterialToolbar
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.viewmodel.SharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var topAppBar: MaterialToolbar? = null

    lateinit var sharedVM: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val tmpSharedVM: SharedViewModel by activityViewModels<SharedViewModel>()
        sharedVM = tmpSharedVM

        sharedVM.getUrl().observe(requireActivity()) {
            Log.d("debapp", "URL-007: ${it}")
        }
    }

    private fun menuListener(): Toolbar.OnMenuItemClickListener {
        return Toolbar.OnMenuItemClickListener{
            true
        }
    }

    fun setupMenu(){
        topAppBar?.menu?.clear()
        topAppBar?.inflateMenu(R.menu.default_menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chart, container, false)
        topAppBar = requireActivity().findViewById(R.id.topAppBar)

        setupMenu()
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}