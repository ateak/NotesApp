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

class MainFragment : Fragment(), Contract.View {

    private lateinit var bindingMain: FragmentMainBinding
    private lateinit var adapter: CustomRecyclerAdapter
    private val dataModel: DataModel by activityViewModels()
    private var presenter: MainPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        presenter  = MainPresenter(this, Model())
        bindingMain = FragmentMainBinding.inflate(inflater)
        Log.v("MainFragment", "Main fragment on create")
        return bindingMain.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        context?.let { presenter?.showNotes(it) }
    }

    override fun initViews() {
        initRecyclerView()
        initButtons()
    }

    private fun initRecyclerView() {
        adapter = CustomRecyclerAdapter { note ->
            openFragment(DetailsNoteFragment.newInstance(), R.id.fragmentContainerView2)
            dataModel.hash.value = note.hashCode()
        }
        val swapHelper = deleteItemBySwipe()
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

    override fun updateViews() {
        presenter?.let { adapter.updateAdapter(it.getNotes()) }
    }

    override fun deleteNote(viewHolder: RecyclerView.ViewHolder) {
        adapter.removeItem(viewHolder.bindingAdapterPosition)
    }

    override fun openFragment(fragment: Fragment, idHolder: Int) {
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("MainFragment")
            .replace(idHolder, fragment)
            .commit()
    }

    /**
     * Функция для удаления заметки по свайпу вправо
     */
    private fun deleteItemBySwipe(): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                presenter?.deleteItem(viewHolder)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter = null
    }
}