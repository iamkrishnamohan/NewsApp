package `in`.newtel.newsapp.ui

import DragAndDropAdapter
import MyItemTouchHelperCallback
import OnStartDragListener
import `in`.newtel.newsapp.databinding.FragmentDragAndDropBinding
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class DragAndDropFragment : Fragment() {

    private lateinit var binding: FragmentDragAndDropBinding
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var pickMultipleMediaLauncher: ActivityResultLauncher<Intent>
    private val picsList: MutableList<Uri?> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDragAndDropBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)

        initializeMultiPicker()

        binding.buttonMultiPick.setOnClickListener {
            pickMultipleMediaLauncher.launch(Intent(MediaStore.ACTION_PICK_IMAGES).apply {
                type = "image/*"
                putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 20)
            })
        }

    }

    private fun initializeMultiPicker() {
        // Initialize multiple media picker launcher
        pickMultipleMediaLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode != Activity.RESULT_OK) {
                    Toast.makeText(requireContext(), "Failed picking media.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    picsList.clear()
                    val uris = it.data?.clipData ?: return@registerForActivityResult
                    var uriPaths = ""
                    for (index in 0 until uris.itemCount) {
                        uriPaths += uris.getItemAt(index).uri.path
                        picsList.add(uris.getItemAt(index).uri)
                        uriPaths += "\n"
                    }
                    picsList?.let {
                        generateItems(picsList)
                    }
                    Log.e(
                        "TAG",
                        "initializeMultiPicker: SUCCESS: $uriPaths pickList size is ${picsList.size}"
                    )
                }
            }
    }

    private fun generateItems(picsList: MutableList<Uri?>) {
        val adapter = DragAndDropAdapter(requireContext(), picsList, object : OnStartDragListener {
            override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
                itemTouchHelper.startDrag(viewHolder!!)
            }
        })
        binding.recyclerView.adapter = adapter
        val callback = MyItemTouchHelperCallback(adapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

}