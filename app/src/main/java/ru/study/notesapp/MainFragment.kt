package ru.study.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.study.notesapp.databinding.FragmentMainBinding

class MainFragment : Fragment(), Contract.MainView {

    private lateinit var bindingMain: FragmentMainBinding
    private lateinit var adapter: CustomRecyclerAdapter
    private val dataModel: DataModel by activityViewModels()
    private var presenter: MainPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bindingMain = FragmentMainBinding.inflate(inflater)
        Log.v("MainFragment", "Main fragment on create")
        return bindingMain.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter = MainPresenter(requireContext(),this)
    }

    private fun initViews() {
        initRecyclerView()
        initButtons()
    }

    private fun initRecyclerView() {
        adapter = CustomRecyclerAdapter { note ->
            openFragment(DetailsNoteFragment.newInstance(), R.id.fragmentContainerView2)
            dataModel.noteId.value = note.id
        }
        val swapHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                presenter?.deleteNote(adapter.getItemByPosition(viewHolder.position).id)
                adapter.removeItem(viewHolder.position)
            }
        })
        swapHelper.attachToRecyclerView(bindingMain.recyclerView)
        bindingMain.recyclerView.adapter = adapter
    }

    private fun initButtons() {
        bindingMain.noteButton.let {
            it.setOnClickListener {
                openFragment(CreateNoteFragment.newInstance(), R.id.fragmentContainerView2)
            }
        }
    }

    override fun showNotes(noteList: MutableList<Note>) {
        adapter.updateAdapter(noteList)
    }

    private fun openFragment(fragment: Fragment, idHolder: Int) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("MainFragment")
            .replace(idHolder, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
