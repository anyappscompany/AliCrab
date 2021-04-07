package ua.com.anyapps.alicrab.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.databinding.DialogChartBinding
import ua.com.anyapps.alicrab.viewmodel.ChartDialogViewModel
import ua.com.anyapps.alicrab.viewmodel.SharedViewModel

class ChartDialog: DialogFragment() {
    private lateinit var sharedVM: SharedViewModel
    private lateinit var chartDialogViewModel: ChartDialogViewModel
    private var binding: DialogChartBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dialog_chart, container, false)
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return dialog
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val tmpSharedVM: SharedViewModel by activityViewModels<SharedViewModel>()
        sharedVM = tmpSharedVM
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DialogChartBinding.bind(view)

        setupViewModel()
        initObservers()
        initListeners()
    }

    private fun setupViewModel(){
        chartDialogViewModel = ChartDialogViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun initObservers(){
        sharedVM.getUrl().observe(requireActivity()) {
            chartDialogViewModel.getChartDataBtnClick(it)
        }

        chartDialogViewModel.getChartData().observe(requireActivity()) {
            Log.d("debapp", "New chart data is: ${it.product_title}")
            Toast.makeText(requireContext(), it.product_title, Toast.LENGTH_SHORT).show()
        }
    }

    fun initListeners(){
        //
    }
}